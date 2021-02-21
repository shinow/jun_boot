
## happy-panda/myRuoYi  <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=350012167&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:350012167:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>



```
fork自若依/RuoYi ,兼具RuoYi,不定期更新源项目代码保持同步(即日起不再同步),在此基础上**兼容新增**个人功能;  
```


### 重要说明
采用id_worker 策略,有个**问题**:数字太长有js安全值问题损失精度,比如1082835020109975553 变成 1082835020109975600  
**建议**:数据库类型依然用bigint,保障时序性与索引性能,实体类对应字段用String接收便无妨.所以当使用Map<String,Object>接收的时候,要注意这个问题,按实际情况处理(MP代码生成支持并已配置bigint转string,这就很nice了)

#### 为什么要有myRuoyi?
```$xslt
麻雀虽小,五脏俱全.若依/ruoyi 短小精悍, 用于中小型项目已十分优秀全面. 
但是只适用于单机应用,故而有myRuoyi. 
myRuoyi继承Ruoyi良好的思想: 不技术侵入,清晰,好改,易用,让开发人员一目了然.
myRuoyi基于Ruoyi扩展适用于互联网应用的基建工作, 以及对其进行优化. 
myRuoyi可插拔式扩展主流的可集群应用解决方案,即你若不需要,不开启对应的配置即可;亦可参考相关构建自由选择相关云服务供应商实现解决方案,省事省力.
由于基于springboot,所以很容易采用springcloud解决方案改造实现微服务架构. (本项目不做微服务改造实现. ps:合适才是最好的; 如果你的项目必须采用微服务架构, 那么你的公司肯定很有钱,自己按需改造吧.)
```
#### myRuoyi新增内容(排名不分先后)
- 整合 redis 以及支持 redis-shiro 配置文件设置参数**可插拔**redis (本项目已启用redis,请修改配置为自己的redis服务或关闭)
- 区分开shiro-redis 与 spring-redis 配置,服务独立
- 主题皮肤支持用户自由选择,优先于系统参数配置  
- 采用mybaties-plus,通用mapper减少项目体量;<br>
    含代码生成工具类,位于ruoyi-generator 模块下的 GenMPcodeUtil;<br>
    id生成策略改造完成,系统表采用id_worker生成id. com.ruoyi.common.utils.IdUtils;<br>
    数据库脚本 ry-panda-***.sql (修改了主键类型以及新增表)<br>
- 新增模块 ruoyi-business **业务模块** 改造mybaties配置支持无xml**注解**方式开发
- 优化 **AsyncFactory.syncSessionToDb(...);** 减少不必要的资源消耗,顺便达到优化页面**响应效率**的效果.user.onlineAsyTime 参数配置缓冲时间
- 提供各类openApi接口例子
- 两级缓存工具类 Cache2LevelUtils (EHcache + redis) 
- 百度对象存储服务 , 文件上传下载基础建设改造(**可插拔**)
- 新增JWT体系接口
- 菜单提供搜索定位功能,快速定位菜单(开发测试等全权限人员必备功能,实用)
- 上传文件命名为文件唯一标识(相同的文件同一目录只会存一份,节省空间)

---


## 若依/RuoYi 简介
## 平台简介

一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适的。于是利用空闲休息时间开始自己写了一套后台系统。如此有了若依。她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。

性别男，若依是给还没有出生女儿取的名字（寓意：你若不离不弃，我必生死相依）

若依基于hplus和inspinia两套后台系统模板开发。有需要可自行到群内下载。

> RuoYi从3.0开始，进行模块拆分，将原先的单应用转变为多模块，如需单应用，请移步 [RuoYi-fast](https://gitee.com/y_project/RuoYi-fast)  

> 推荐使用阿里云部署，通用云产品代金券 ：[点我领取](https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=brki8iof)  

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
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 在线构建器：拖动表单元素生成相应的HTML代码。
17. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
## 在线体验
> admin/admin123  
> 陆陆续续收到一些打赏，为了更好的体验已用于演示服务器升级。谢谢各位小伙伴。

演示地址：http://ruoyi.vip  

文档地址：http://doc.ruoyi.vip

## 演示图

<table>
    <tr>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162645_696a856d_1406033.jpeg"/></td>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162643_4c8db2ad_1406033.jpeg"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162645_70665adb_1406033.jpeg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/91bef110740ba9e36ff00804f8748a787fb.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162648_d825ac1e_1406033.jpeg"/></td>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162648_9ea28c7c_1406033.jpeg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/787b3b06430a403655b48b9bcd1fa829555.jpg"/></td>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162645_0fb321ac_1406033.jpeg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/5fb138478adeda6825e206d21f67ecd0625.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/fa2f027a10707a4eb4fc47d5ea1c3d2b772.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://gitee.com/uploads/images/2019/0409/162648_8c3a3d0b_1406033.jpeg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/ab4b5797dfb2bc68c4974ad5458bd5f5bcf.jpg"/></td>
    </tr>
</table>


## 若依交流群

QQ群： [![加入QQ群](https://img.shields.io/badge/已满-1389287-blue.svg)](https://jq.qq.com/?_wv=1027&k=5HBAaYN)  [![加入QQ群](https://img.shields.io/badge/已满-1679294-blue.svg)](https://jq.qq.com/?_wv=1027&k=5cHeRVW)  [![加入QQ群](https://img.shields.io/badge/QQ群-1529866-blue.svg)](https://jq.qq.com/?_wv=1027&k=53R0L5Z)  点击按钮入群。