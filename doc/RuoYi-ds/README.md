## 平台简介

一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适的。于是利用空闲休息时间开始自己写了一套后台系统。如此有了若依。她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。

本项目在若依管理系统上进行了改造,添加了动态数据源(从数据中查询并动态创建数据源),非常适合有多个数据源但表结构都一致的场景,例如游戏工程中的日志库和游戏库,每开一个服务器都会配置一个日志数据源和游戏数据源,但他们表结构都一致。除了动态数据源该项目还实现了动态表单查询功能,只需简单配置一下表结构即可查询数据

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 在线构建器：拖动表单元素生成相应的HTML代码。
16. 连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
17. 动态数据源：根据数据库中的数据源配置动态的创建以及切换数据源
18. 万能查询功能：只需要将数据库字段按照规则映射到配置文件即可实现对单表的多条件查询功能
## 在线体验
> admin/admin123

演示地址：http://ruoyi.vip  

文档地址：http://doc.ruoyi.vip

## 演示图

<table>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/25b5e333768d013d45a990c152dbe4d9d6e.jpg"/></td>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194326_GBIJ_1438828.png"/></td>
    </tr>
    <tr>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194443_Qyuq_1438828.png"/></td>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194501_U7gT_1438828.png"/></td>
    </tr>
    <tr>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194525_PApp_1438828.png"/></td>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194535_3EM0_1438828.png"/></td>
    </tr>
    <tr>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194612_kJ4F_1438828.png"/></td>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194623_YEXO_1438828.png"/></td>
    </tr>
	<tr>
        <td><img src="https://static.oschina.net/uploads/space/2018/0902/132548_ne4U_1438828.png"/></td>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194643_MsxF_1438828.png"/></td>
    </tr>
	<tr>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194658_40L4_1438828.png"/></td>
        <td><img src="https://static.oschina.net/uploads/space/2018/1005/194712_2ma3_1438828.png"/></td>
    </tr>
   </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2018/1205/173905_15e669b8_421615.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2018/1205/173925_e176b528_421615.png"/></td>
    </tr>

</table>
