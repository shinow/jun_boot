package org.springrain.rpc.springbind;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import io.seata.tm.api.TransactionalExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.rpc.annotation.RpcServiceMethodAnnotation;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc.GrpcCommonServiceBlockingStub;
import org.springrain.rpc.grpcimpl.GrpcClient;
import org.springrain.rpc.grpcimpl.GrpcCommonException;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.rpc.sessionuser.UserVO;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 代理grpc的service服务
 * <p>
 * seata处理分布式事务,假设调用链是 A-->B-->C-->D
 * <p>
 * 需要测试的事务场景 1.单体项目事务测试 2.远程调用,事务测试
 * 3.远程调用,spring本地事务混合调用.例如服务A内update方法,方法内有远程调用了B服务的update方法.
 * 4.远程调用,多次修改造成的数据冲突,回滚时会不会有问题
 *
 * @param <T>
 * @author caomei
 */
public class GrpcServiceProxy<T> implements InvocationHandler {

    // 事务匹配的规则
    String pattern = ".*Service.(save|update|delete)(.*)";
    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);
    private GrpcCommonRequest grpcCommonRequest;
    private String rpcHost;
    private Integer rpcPort;

    public GrpcServiceProxy(String rpcHost, Integer rpcPort, GrpcCommonRequest grpcRequest) {
        this.rpcHost = rpcHost;
        this.rpcPort = rpcPort;
        this.grpcCommonRequest = grpcRequest;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 初始化请求数据
        GrpcCommonRequest grpcRequest = new GrpcCommonRequest();
        grpcRequest.setArgs(args);
        grpcRequest.setMethod(method.getName());
        grpcRequest.setBeanName(grpcCommonRequest.getBeanName());
        grpcRequest.setClazz(grpcCommonRequest.getClazz());
        grpcRequest.setTimeout(grpcCommonRequest.getTimeout());
        grpcRequest.setVersionCode(grpcCommonRequest.getVersionCode());
        grpcRequest.setAutocommit(grpcCommonRequest.getAutocommit());
        grpcRequest.setArgTypes(method.getParameterTypes());
        // 方法的全路径
        String methodPath = grpcRequest.getClazz() + "." + grpcRequest.getMethod();

        boolean istx = false;

        // 获取方法上的RpcServiceMethodAnnotation注解内容
        RpcServiceMethodAnnotation rpcServiceMethodAnnotation = method.getAnnotation(RpcServiceMethodAnnotation.class);
        if (rpcServiceMethodAnnotation != null) {
            grpcRequest.setTimeout(rpcServiceMethodAnnotation.timeout());
            grpcRequest.setAutocommit(rpcServiceMethodAnnotation.autocommit());

        }
        // 传递UserVO对象
        UserVO userVO = SessionUser.getUserVO();
        if (userVO != null) {
            userVO.setPassword(null);
            grpcRequest.setUserVO(userVO);
        }

        // 需要考虑是调用链入口还是中间节点

        // 获取全局的xid
        String txGroupId = RootContext.getXID();

        if (StringUtils.isNotBlank(txGroupId)) {// 如果有全局事务
            istx = true;
            // 设置xid
            grpcRequest.setTxGroupId(txGroupId);
        } else {// 如果没有全局事务,判断此方法是否具有spring事务.如果客户端没有事务,开启事务的权限就在服务端.
            istx = hasSpringTx();
            // istx = isSpringTxMethod(methodPath);
        }
        // 1. 获取当前全局事务实例或创建新的实例
        GlobalTransaction tx = null;
        // 如果没有全局事务
        if (StringUtils.isBlank(txGroupId) && istx) {
            // 1. 获取当前全局事务实例或创建新的实例
            tx = GlobalTransactionContext.getCurrentOrCreate();

            // 2. 开启全局事务
            try {
                tx.begin(grpcRequest.getTimeout(), methodPath);
                txGroupId = tx.getXid();
                // 创建线程
                GlobalStatic.seataTransactionBegin.set(true);
            } catch (TransactionException txe) {
                // 2.1 开启失败
                throw new TransactionalExecutor.ExecutionException(tx, txe, TransactionalExecutor.Code.BeginFailure);
            }
            // 设置xid
            grpcRequest.setTxGroupId(txGroupId);
        }

        GrpcCommonServiceBlockingStub blockingStub = GrpcClient.getCommonServiceBlockingStub(rpcHost, rpcPort);

        // grpc客户端.发起请求
        GrpcCommonResponse grpcResponse = GrpcClient.commonHandle(blockingStub, grpcRequest);

        // 处理异常,异常是在服务端抛出的.
        if (grpcResponse.getStatus() >= 400) {
            Throwable throwable = grpcResponse.getException();
            // 业务调用本身的异常


            GrpcCommonException exception = new GrpcCommonException(
                    throwable.getClass().getName() + ": " + throwable.getMessage());
            StackTraceElement[] exceptionStackTrace = exception.getStackTrace();
            StackTraceElement[] responseStackTrace = grpcResponse.getStackTrace();
            StackTraceElement[] allStackTrace = Arrays.copyOf(exceptionStackTrace,
                    exceptionStackTrace.length + responseStackTrace.length);
            System.arraycopy(responseStackTrace, 0, allStackTrace, exceptionStackTrace.length,
                    responseStackTrace.length);
            exception.setStackTrace(allStackTrace);
            throw exception;

        }


        // 返回结果
        return grpcResponse.getResult();
    }

    /**
     * 如果已经存在spring事务,说明是中间节点,需要在rpc客户端产生全局事务,和当前事务绑定.如果没有事务,在服务端创建分布式事务.
     *
     * @return
     */
    private boolean hasSpringTx() {

        try {
            TransactionInterceptor.currentTransactionStatus();
            return true;
        } catch (NoTransactionException e) {
            return false;
        }
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
