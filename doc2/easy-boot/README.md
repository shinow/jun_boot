# eboot

2019-09

    > 整体依赖升级，
        springboot  --> 2.1.7.RELEASE
        springcloud --> 2.1.3.RELEASE
        druid       --> 1.1.18
        feign       --> openfeign

---
    基于springboot开发的脚手架，旨在迅速搭建开发平台。采用分布式架构，
    集群部署方式。适用于中小型项目开发，具备快速集成，快速上手的特点。

# 使用技术

---
    1、基础框架：springboot + mybatis + springcloud
    2、权限框架：shiro
    3、缓存：redis
    4、数据库：mysql
    5、UI：LayUI
    6、session共享：spring-session-redis
# 包含模块

---
    1、系统管理
        1.1 用户管理
        1.2 角色管理
        1.3 权限分配
        1.4 字典管理
    2、报表模块
        2.1 Echarts集成
    3、导入导出
        3.1 Excel的导入导出
        3.2 简单word的导出
# 模块说明

---
    0.eboot-actuator：服务监控
    1.eboot-admin：后台管理系统
    2.eboot-api：给移动端提供接口服务，集成jwt认证
    3.eboot-entity：实体类
    4.eboot-file：文件服务
    5.eboot-provider：服务注册中心
    6.eboot-service：业务处理服务接口
    7.eboot-tools：工具包
    8.eboot-web：PC/Wap服务
    9.eboot-consumer：消息队列消费者
# 运行步骤

---
    1、创建数据库：eboot，导入eboot-admin/src/main/resources/sql/v{x}.sql
    2、导入项目，下载maven依赖
    3、配置eboot-service中的数据库信息
    4、配置eboot-admin/eboot-api中的redis信息
    5、配置eboot-file中的文件上传路径
    6、依次启动eboot-provider、eboot-file、eboot-service、eboot-admin
    7、访问 [首页](http://localhost:8882/index),默认用户名：superadmin，密码：123456

# 下一步计划
---
    1. 添加工作流组件
    2. 添加token防重复提交校验机制

# 其他
    欢迎各位有什么需要的可以Issue提出来，我会视情况而定集成进来，尽量简化大家的工作
    项目肯定有很多不足之处，大家多提宝贵意见，多谢^_^
# 部分页面截图
![登录](https://gitee.com/uploads/images/2018/0514/173817_401f4989_660787.png "login.png")
![首页](https://gitee.com/uploads/images/2018/0514/173051_16baa875_660787.png "index.png")
![登录统计](https://gitee.com/uploads/images/2018/0514/173130_d65ff447_660787.png "login_report.png")
![菜单添加](https://gitee.com/uploads/images/2018/0514/173141_4a33e5f2_660787.png "menu_add.png")
![角色详情](https://gitee.com/uploads/images/2018/0514/173154_8bb32a20_660787.png "role_detail.png")
![角色授权](https://gitee.com/uploads/images/2018/0514/173204_68287e03_660787.png "role_permit.png")
![用户列表](https://gitee.com/uploads/images/2018/0514/173213_e00fc2d5_660787.png "user_index.png")
![欢迎关注公众号](https://images.gitee.com/uploads/images/2018/0726/081557_aa7756c9_660787.jpeg "陌与尘埃")


**欢迎关注以上公众号，项目涉及问题、功能点，我都会在公众号中做详细说明**