# RuoYi-ES

#### 介绍
RuoYi-ES 是基于不分离应用若依框架拓展出来的一款搜索引擎， ruoyi + elasticSearch 
然后不知道怎么搞的，我上传es的资源提示我报错了，上传不了，然后你们要是觉得官网上面下载慢的话，可以加 Q Q 裙：881386783    **群里面有视频教学和对应的资源文件** 


![QQ裙：](https://images.gitee.com/uploads/images/2020/0511/105436_b132776d_1884241.png "屏幕截图.png")


![es用到的资源](https://images.gitee.com/uploads/images/2020/0423/123350_b76034cc_1884241.png "屏幕截图.png")

#### 软件架构

  感谢若依框架   [若依官网](http://www.ruoyi.vip)

#### ES效果图

![列表](https://images.gitee.com/uploads/images/2020/0423/105032_3f06c1ec_1884241.png "屏幕截图.png")

![添加到mysql的同时也添加到es里面的数据，实时同步](https://images.gitee.com/uploads/images/2020/0423/105136_eccd2d79_1884241.png "屏幕截图.png")

![修改到mysql的同时也修改到es里面的数据，实时同步](https://images.gitee.com/uploads/images/2020/0423/105244_bb0b4480_1884241.png "屏幕截图.png")

![删除到mysql的同时也删除到es里面的数据，实时同步](https://images.gitee.com/uploads/images/2020/0423/105332_d4ac2ce6_1884241.png "屏幕截图.png")

![mysql搜索的内容](https://images.gitee.com/uploads/images/2020/0423/105526_4ff5dec0_1884241.png "屏幕截图.png")

![es搜索的内容](https://images.gitee.com/uploads/images/2020/0423/105550_e110e4fb_1884241.png "屏幕截图.png")




#### 使用教程
### windows 安装 ES 

克隆项目之后，下载 elasticSearch ![运行es](https://images.gitee.com/uploads/images/2020/0423/105828_325401d8_1884241.png "屏幕截图.png") 运行bin 下面的 elasticsearch.bat  


 **=====================================这里是分割线=====================================** 


### linux 安装 ES

 **容器安装 ，宿主机安装** 

 **[看我博客](https://blog.csdn.net/Japhet_jiu/article/details/107905026)** 

 **https://blog.csdn.net/Japhet_jiu/article/details/107905026** 



 **=====================================这里是分割线=====================================** 


### linux 安装 elasticsearch-head



 **[看我博客](https://blog.csdn.net/Japhet_jiu/article/details/107912533)** 

 **https://blog.csdn.net/Japhet_jiu/article/details/107912533** 



 **=====================================这里是分割线=====================================** 


 **  如果不想运行mq 注释项目中这两个地方_** 
![不运行mq,注释这两个个地方](https://images.gitee.com/uploads/images/2020/0722/100354_3dfe48d2_1884241.jpeg "800 (1).jpg")
![不运行mq,注释这两个个地方](https://images.gitee.com/uploads/images/2020/0722/100405_94822dbd_1884241.jpeg "800.jpg")


 **=====================================这里是分割线=====================================** 


### RabbitMQ效果图

![mq实现30秒后插入数据，（发送短信通知用户）](https://images.gitee.com/uploads/images/2020/0604/155001_b876c5a6_1884241.png "屏幕截图.png")

![登录日志查询](https://images.gitee.com/uploads/images/2020/0604/155158_b7122c4f_1884241.png "屏幕截图.png")

![mq测试](https://images.gitee.com/uploads/images/2020/0604/155240_8cfaad85_1884241.png "屏幕截图.png")


 **=====================================这里是分割线=====================================** 


### windows 安装 RabbitMQ 
![下载这两个文件](https://images.gitee.com/uploads/images/2020/0728/143037_2ebd25c8_1884241.png "0.png")

1.安装Erlang，下载地址：http://erlang.org/download/otp_win64_21.3.exe 

![安装Erlang步骤1](https://images.gitee.com/uploads/images/2020/0728/142638_178c38af_1884241.png "1.png")
![安装Erlang步骤2](https://images.gitee.com/uploads/images/2020/0728/142653_46d83794_1884241.png "2.png")
![安装Erlang步骤3](https://images.gitee.com/uploads/images/2020/0728/142701_a7d84d20_1884241.png "3.png")
![安装Erlang步骤4](https://images.gitee.com/uploads/images/2020/0728/142708_c2b0634d_1884241.png "4.png")

2.安装RabbitMQ，下载地址：https://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.14/rabbitmq-server-3.7.14.exe

![安装RabbitMQ步骤1](https://images.gitee.com/uploads/images/2020/0728/143136_d1f513da_1884241.png "1.png")
![安装RabbitMQ步骤2](https://images.gitee.com/uploads/images/2020/0728/143143_8fbc4781_1884241.png "2.png")
![安装RabbitMQ步骤3](https://images.gitee.com/uploads/images/2020/0728/143149_8adeebb4_1884241.png "3.png")
![安装RabbitMQ步骤4](https://images.gitee.com/uploads/images/2020/0728/143155_79fe3664_1884241.png "4.png")


3.启动，进入RabbitMQ安装目录下的sbin目录，在地址栏输入cmd并回车启动命令行，然后输入以下命令启动管理功能：

rabbitmq-plugins enable rabbitmq_management

![输入图片说明](https://images.gitee.com/uploads/images/2020/0728/143656_48656591_1884241.png "1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0728/143753_f1800ed1_1884241.png "2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0728/143833_55995f86_1884241.png "3.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0728/143909_c69b2e98_1884241.png "4.png")

4.访问地址查看是否安装成功：http://localhost:15672/  ，输入账号密码并登录：guest guest

![输入图片说明](https://images.gitee.com/uploads/images/2020/0728/144037_614f50d1_1884241.png "5.png")


5.创建帐号并设置其角色为管理员：


登录进去后 创建用户名和密码并分配权限
点击 admin-->users--> 创建一个用户 

![创建一个用户](https://images.gitee.com/uploads/images/2020/0604/161229_ec1018ea_1884241.png "~R@R_)CX@T(E@[4`ED2%4MS.png")

设置virtual hosts  选择刚刚创建的用户，

项目修改配置，对应ip,对应名字密码，对应的virtual hosts name

![项目配置](https://images.gitee.com/uploads/images/2020/0604/161423_d6db3cf3_1884241.png "屏幕截图.png")


 **=====================================这里是分割线=====================================** 

### linux  安装 RabbitMQ  


在linux中安装容器（docker）1.安装docker yum install -y docker  2.启动docker systemctl start docker

下载镜像 docker pull rabbitmq:management，

安装 rabbitMq 
docker run -di --name=japhet -p 5671:5617 -p 5672:5672 -p4369:4369 -p 15671:15671 -p 15672:15672 -p 25672:25672 rabbitmq:management


启动容器， 
浏览器访问 http://虚拟机ip :15672 ,

![登录截图](https://images.gitee.com/uploads/images/2020/0604/155535_a8369daa_1884241.png "屏幕截图.png")

默认用户名密码：  guest   guest 

登录进去后 创建用户名和密码并分配权限
点击 admin-->users--> 创建一个用户 

![创建一个用户](https://images.gitee.com/uploads/images/2020/0604/161229_ec1018ea_1884241.png "~R@R_)CX@T(E@[4`ED2%4MS.png")

设置virtual hosts  选择刚刚创建的用户，

项目修改配置，对应ip,对应名字密码，对应的virtual hosts name

![项目配置](https://images.gitee.com/uploads/images/2020/0604/161423_d6db3cf3_1884241.png "屏幕截图.png")


#### 参与贡献
  感谢若依框架   [若依官网](http://www.ruoyi.vip)
