## RuoYi-fast-Oracle
#### 若依 Maven 单模块 Oracle 版本
基于 [RuoYi-fast MySQL](https://gitee.com/y_project/RuoYi-fast) ，参考 [RuoYi-Oracle](https://github.com/yangzongzhuan/RuoYi-Oracle)

> 若依多模块 MySQL 版本 [RuoYi](https://gitee.com/y_project/RuoYi)  
若依单模块 MySQL 版本 [RuoYi-fast MySQL](https://gitee.com/y_project/RuoYi-fast)  
若依多模块 Oracle 版本 [RuoYi-Oracle](https://github.com/yangzongzhuan/RuoYi-Oracle)  
如需其他版本，请移步 [项目扩展](http://doc.ruoyi.vip/ruoyi/document/xmkz.html)  `(不定时更新)`

#### 在线体验
> admin/admin123  

**演示地址**：http://ruoyi.vip  
**文档地址**：http://doc.ruoyi.vip  
**语雀地址**：https://www.yuque.com/ry  
**Issues地址**：https://gitee.com/y_project/RuoYi/issues?state=closed  
**Issues地址**：https://gitee.com/racsu/RuoYi-Oracle/issues?state=closed 


#### 交流及反馈
`bug`及其它问题请提`Issues`或者进群讨论

    该问题是怎么引起的？
    重现步骤
    报错信息
    
    相关的Issue
    原因/目的/解决的问题等
    描述（做了什么，变更了什么）
    测试用例

>   Issues 地址  
    https://gitee.com/y_project/RuoYi/issues  
    https://gitee.com/racsu/RuoYi-Oracle/issues  
    https://gitee.com/baha/RuoYi-fast-Oracle/issues
 
#### 若依交流群

QQ群： [![加入QQ群](https://img.shields.io/badge/已满-1389287-blue.svg)](https://jq.qq.com/?_wv=1027&k=5HBAaYN)  [![加入QQ群](https://img.shields.io/badge/已满-1679294-blue.svg)](https://jq.qq.com/?_wv=1027&k=5cHeRVW)  [![加入QQ群](https://img.shields.io/badge/已满-1529866-blue.svg)](https://jq.qq.com/?_wv=1027&k=53R0L5Z)  [![加入QQ群](https://img.shields.io/badge/已满-1772718-blue.svg)](https://jq.qq.com/?_wv=1027&k=5g75dCU)  [![加入QQ群](https://img.shields.io/badge/已满-1366522-blue.svg)](https://jq.qq.com/?_wv=1027&k=58cPoHA)  [![加入QQ群](https://img.shields.io/badge/已满-1382251-blue.svg)](https://jq.qq.com/?_wv=1027&k=5Ofd4Pb)  [![加入QQ群](https://img.shields.io/badge/已满-1145125-blue.svg)](https://jq.qq.com/?_wv=1027&k=5yugASz)  [![加入QQ群](https://img.shields.io/badge/已满-86752435-blue.svg)](https://jq.qq.com/?_wv=1027&k=5Rf3d2P)  [![加入QQ群](https://img.shields.io/badge/已满-134072510-blue.svg)](https://jq.qq.com/?_wv=1027&k=5ZIjaeP)  [![加入QQ群](https://img.shields.io/badge/已满-210336300-blue.svg)](https://jq.qq.com/?_wv=1027&k=5CJw1jY)  [![加入QQ群](https://img.shields.io/badge/已满-339522636-blue.svg)](https://jq.qq.com/?_wv=1027&k=5omzbKc)  [![加入QQ群](https://img.shields.io/badge/130035985-blue.svg)](https://jq.qq.com/?_wv=1027&k=qPIKBb7s)
若依 Oracle 版本交流群： [![oracle版本交流群](https://img.shields.io/badge/22271299-blue.svg)](https://shang.qq.com/wpa/qunwpa?idkey=e1ea16365440a9fa97ff72b0c73803e49a55dc68ae4c4181f3fb1da74928885e)  点击按钮入群。


> 解决同一Tomcat下两个使用druid的springboot项目冲突问题

	# Spring配置
    spring:
      # 解决同一Tomcat下两个使用druid的springboot项目冲突问题（同一个domain里面的MBean要求name唯一）
      jmx:
        default-domain: ruoyi-fast-oracle

**_改动内容_**

> application.yml
    
    # PageHelper分页插件
    pagehelper:
      helperDialect: oracle

> application-druid.yml
    
    driverClassName: oracle.jdbc.OracleDriver
    url: jdbc:oracle:thin:@127.0.0.1:1521/orcl
    username: ry
    password: ry
    
> pom.xml

        <oracle.version>11.2.0.4</oracle.version>
	    <!-- Oracle 驱动 -->
	    <!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc6 -->
	    <dependency>
	        <groupId>com.oracle.database.jdbc</groupId>
	        <artifactId>ojdbc6</artifactId>
	        <version>${oracle.version}</version>
	    </dependency>
    
> gen 模块

    参考 ruoyi-oracle，添加序列
        #if($pkColumn.increment)
        -- ${tableName}主键序列
        create sequence seq_${tableName}
        increment by 1
        start with 10
        nomaxvalue
        nominvalue
        cache 20;
        #end
    
    GenTable
        /** 菜单id **/
        private Long menuId;
        
    GenTableServiceImpl
        generatorCode()
        // 获取菜单id序列，用于生成菜单sql语句
        long menuId = genTableMapper.selectMenuId();
        table.setMenuId(menuId);
        
    参考 RuoYi-Oracle，修改为 Oracle 可用的格式，调整路径相关

> sql 文件和 xml 文件

    参考 RuoYi-Oracle，修改为 Oracle 可用的格式
    `ry_yyyymmdd(更新日期).sql`，此文件中的 function find_in_set，请在 sql 窗口单独执行

> 处理 null  (User.java | Menu.java | GenTableColumn.java)
    
    return StringUtils.isEmpty(avatar) ? StringUtils.EMPTY : avatar;
    return StringUtils.isEmpty(perms) ? StringUtils.EMPTY : perms;
    return StringUtils.isEmpty(dictType) ? StringUtils.EMPTY : dictType;
    处理数据库对应的列默认值 NULL
    
> ScheduleConfig.java

    prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "false");
    
> 代码生成测试用 Oracle 数据，请参考 [若依文档-代码生成](http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90)  
    (表对应的序列 seq_tableName，在生成的 Xxx.sql 文件中有生成)
    
    新建数据库表结构（单表-Oracle）
    create table sys_student (
      student_id           number(11),
      student_name         varchar2(30)             default '',
      student_age          number(3)      	        default null,
      student_sex          varchar2(1)    	        default '0',
      student_status       varchar2(1)    	        default '0',
      student_birthday     date,
      remark               varchar2(500)            default null 
    );
    alter table sys_student add constraint pk_sys_student primary key (student_id);
    comment on table  sys_student                   is '学生名称';
    comment on column sys_student.student_id      	is '学生主键seq_sys_student.nextval';
    comment on column sys_student.student_name    	is '学生名称';
    comment on column sys_student.student_age    	is '年龄';
    comment on column sys_student.student_sex    	is '性别（0男 1女 2未知）';
    comment on column sys_student.student_status    is '状态（0正常 1停用）';
    comment on column sys_student.student_birthday  is '生日';
    comment on column sys_student.remark            is '备注';
    
    新建数据库表结构（树表-Oracle）
    create table sys_product (
      product_id        number(20)                  not null,
      parent_id         number(20)                  default 0,
      product_name      varchar2(30)                default '',
      order_num         number(4)                   default 0,
      status            varchar2(1)                 default '0'
    );
    alter table sys_product add constraint pk_sys_product primary key (product_id);
    comment on table  sys_product                   is '产品表';
    comment on column sys_product.product_id        is '产品id主键seq_sys_product.nextval';
    comment on column sys_product.parent_id         is '父产品id';
    comment on column sys_product.product_name      is '产品名称';
    comment on column sys_product.order_num         is '显示顺序';
    comment on column sys_product.status            is '产品状态（0正常 1停用）';
    
    
    新建数据库表结构（主子表）
    -- 客户表
    create table sys_customer (
      customer_id           number(20)              not null,
      customer_name         varchar(30)             default '',
      phonenumber           varchar(11)             default '' ,
      sex                   varchar(20)             default null,
      birthday              varchar(20)             default null ,
      remark                varchar(500)            default null
    );
    alter table sys_customer add constraint pk_sys_customer primary key (customer_id);
    comment on table  sys_customer                  is '客户表';
    comment on column sys_customer.customer_id      is '客户id seq_sys_customer.nextval';
    comment on column sys_customer.customer_name    is '客户姓名';
    comment on column sys_customer.phonenumber      is '手机号码';
    comment on column sys_customer.sex              is '客户性别';
    comment on column sys_customer.birthday         is '客户生日';
    comment on column sys_customer.remark           is '客户描述';
    
    -- 商品表
    create table sys_goods (
      goods_id           number(20)                 not null ,
      customer_id        number(20)                 not null ,
      name               varchar(30)                default '',
      weight             number(5)                  default null,
      price              decimal(6,2)               default null,
      add_date           varchar(20)                default null,
      type               char(1)                    default null
    );
    alter table sys_goods add constraint pk_sys_goods primary key (customer_id);
    comment on table  sys_goods                     is '商品表';
    comment on column sys_goods.goods_id            is '商品id seq_sys_goods.nextval';
    comment on column sys_goods.customer_id         is '客户id';
    comment on column sys_goods.name                is '商品名称';
    comment on column sys_goods.weight              is '商品重量';
    comment on column sys_goods.price               is '商品价格';
    comment on column sys_goods.add_date            is '商品时间';
    comment on column sys_goods.type                is '商品种类';

> 杂项

    添加 PermissionUtils.getPrincipalProperty(property)
    GenTableServiceImpl 
	    //  String operName = ShiroUtils.getLoginName();
	    String operName = (String) PermissionUtils.getPrincipalProperty("loginName");

