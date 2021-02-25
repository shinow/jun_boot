package org.springrain.rpc.grpcimpl;

import com.google.protobuf.ByteString;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import io.seata.tm.api.TransactionalExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.rpc.sessionuser.UserVO;
import org.springrain.rpc.util.FstSerializeUtils;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 集成自动产生的java类,自定义自己的实现.总体思路是
 * 请求的class,方法,和参数做成二进制,通过grpc传递,实际是二次序列化,对性能有损耗,但是方便......
 * <p>
 * 使用seata实现数据库分布式事务
 *
 * @author caomei
 */
public class CommonGrpcService extends GrpcCommonServiceGrpc.GrpcCommonServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(CommonGrpcService.class);
    // 事务匹配的规则
    String pattern = ".*Service.(save|update|delete)(.*)";
    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);
    private ApplicationContext applicationContext = null;

    public CommonGrpcService(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    @Override
    public void commonHandle(org.springrain.rpc.grpcauto.CommonRequest commonRequest,
                             io.grpc.stub.StreamObserver<org.springrain.rpc.grpcauto.CommonResponse> responseObserver) {

        // 把请求反序列化成正常对象,GrpcRequest
        GrpcCommonRequest grpcRequest = FstSerializeUtils.deserialize(commonRequest);

        // String beanName = grpcRequest.getBeanName();
        // 需要调用的类
        String className = grpcRequest.getClazz();
        // 获取获取参数
        Object[] args = grpcRequest.getArgs();
        // spring bean name
        String beanName = grpcRequest.getBeanName();
        // 当前登录用户对象信息
        UserVO userVO = grpcRequest.getUserVO();

        // 方法的全路径
        String methodPath = className + "." + grpcRequest.getMethod();

        Object bean = null;

        try {
            if ((beanName != null) && (!"".equals(beanName))) {// 先按照beanName查找
                bean = getBeanByName(beanName);
            }

            if (bean == null) {// 按照类型找到springbean
                bean = getBean(Class.forName(className));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }


        // 需要考虑是调用链入口还是中间节点
        // 先判断是否有参数传递进来
        String txGroupId = grpcRequest.getTxGroupId();
        boolean bind = false;
        // 是否是全局事务入口
        GlobalTransaction tx = null;

        try {

            String xid = RootContext.getXID();

            if (StringUtils.isBlank(xid) && StringUtils.isNotBlank(txGroupId)) {
                RootContext.bind(txGroupId);
                bind = true;
            }

            // 调用的方法
            Method method = bean.getClass().getMethod(grpcRequest.getMethod(), grpcRequest.getArgTypes());

            boolean isSpringTxMethod = isSpringTxMethod(methodPath);

            // 如果启用seata-spring注解@GlobalTransactional方法,和grpcserver的切面存在冲突,会重复提交,grpc就不再负责seata事务管理了.
            // 代码先注释了,不想引入seata-spring的jar,用到了再解开.

            /**
             * if (GlobalStatic.seataSpringEnable &&
             * method.isAnnotationPresent(GlobalTransactional.class)) { if
             * (isSpringTxMethod) {// 如果有spring本地事务,设置为false,由seata-spring管理事务.
             * isSpringTxMethod = false; } else { throw new
             * Exception("有@GlobalTransactional注解,却没有Spring本地事务,认为异常"); } }
             **/

            if (StringUtils.isBlank(xid) && StringUtils.isBlank(txGroupId) && isSpringTxMethod) {// 需要产生事务,创建分布式事务.
                // 1. 获取当前全局事务实例或创建新的实例
                tx = GlobalTransactionContext.getCurrentOrCreate();

                // 2. 开启全局事务
                try {
                    tx.begin(grpcRequest.getTimeout(), methodPath);
                    txGroupId = tx.getXid();
                    // 如果是入口开启,就做TM使用了,不再依赖事务管理器
                    // GlobalStatic.seataTransactionBegin.set(true);
                    bind = true;
                } catch (TransactionException txe) {
                    // 2.1 开启失败
                    throw new TransactionalExecutor.ExecutionException(tx, txe,
                            TransactionalExecutor.Code.BeginFailure);
                }
            }

            if (userVO != null) {
                SessionUser.sessionUserLocal.set(userVO);
            }

            // 执行service的方法
            GrpcCommonResponse grpcResponse = invokeMethod(bean, method, args, userVO);


            // 序列化需要返回的结果
            ByteString bytes = FstSerializeUtils.serialize(grpcResponse);
            // 封装成grpc传递的对象
            CommonResponse commonResponse = CommonResponse.newBuilder().setResponse(bytes).build();
            // grpc下一步处理
            responseObserver.onNext(commonResponse);
            // 完成传输
            responseObserver.onCompleted();


            // 提交事务
            if (tx != null) {
                tx.commit();
            }

            // 异常,回滚事务
            if (grpcResponse.getStatus() >= 400) {
                // 全局回滚
                // 业务调用本身的异常
                try {
                    // 全局回滚
                    if (tx != null) { // 3.1 全局回滚成功：抛出原始业务异常
                        tx.rollback();
                    }

                } catch (TransactionException txe) {// 3.2 全局回滚失败：
                    // 3.2 全局回滚失败：
                    logger.error(txe.getMessage(), txe);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // String message = e.getClass().getName() + ": " + e.getMessage();
            // grpcResponse.error(message, e, e.getStackTrace());


            // 全局回滚
            // 业务调用本身的异常
            try {
                // 全局回滚
                if (tx != null) { // 3.1 全局回滚成功：抛出原始业务异常
                    tx.rollback();
                }

            } catch (TransactionException txe) {// 3.2 全局回滚失败：
                // 3.2 全局回滚失败：
                logger.error(txe.getMessage(), txe);
            }

        } finally {

            SessionUser.sessionUserLocal.remove();

            if (bind) {
                String unbindXid = RootContext.unbind();

                if (!txGroupId.equalsIgnoreCase(unbindXid)) {
                    if (unbindXid != null) {
                        RootContext.bind(unbindXid);
                    }
                }

            }

        }

    }

    /**
     * 获取 Service Bean
     */
    private Object getBean(Class clazz) {

        try {
            Object bean = applicationContext.getBean(clazz);
            return bean;
        } catch (BeansException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private Object getBeanByName(String beanName) {

        try {
            Object bean = applicationContext.getBean(beanName);
            return bean;
        } catch (BeansException e) {
            return null;
        }
    }

    /**
     * 反射调用方法,也可以使用MethodUtils, 直接反射会造成形参和实参不对应的时候找不到方法,例如形参Object,参数Entity
     *
     * @param bean
     * @param method
     * @param args
     * @return
     * @throws Exception
     */
    private GrpcCommonResponse invokeMethod(Object bean, Method method, Object[] args,
                                            UserVO userVO) {
        // 初始化需要返回的对象
        GrpcCommonResponse grpcResponse = new GrpcCommonResponse();

        try {
            // Method method = bean.getClass().getMethod(methodName, parameterTypes);
            // Method method = MethodUtils.getMatchingMethod(bean.getClass(), methodName,
            // parameterTypes);
            FastClass serviceFastClass = FastClass.create(bean.getClass());
            FastMethod serviceFastMethod = serviceFastClass.getMethod(method);
            Object result = serviceFastMethod.invoke(bean, args);
            grpcResponse.success(result);
        } catch (Exception e) {
            grpcResponse.error(e.getMessage(), e, e.getStackTrace());
        }
        return grpcResponse;

    }

    /**
     * 如果已经存在spring事务,说明是中间节点,需要在rpc客户端产生全局事务,和当前事务绑定.如果没有事务,在服务端创建分布式事务.判断方法是否会产生spring事务.
     *
     * @param methodPath
     * @return
     */
    private boolean isSpringTxMethod(String methodPath) {

        if (methodPath == null) {
            return false;
        }

        boolean matches = r.matcher(methodPath).matches();
        return matches;
    }

}
