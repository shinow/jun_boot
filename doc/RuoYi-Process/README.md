## 平台简介

[前后端分离版：http://106.12.122.249/](http://106.12.122.249/)

>  闲鹿工作流是一款基于 RuoYi 4.x + Activiti 6.x + Spring Boot 2.x + Thymeleaf 3.x 的开源工作流管理系统~
>
>  作为技术小菜鸟的我，一直对学习 Activiti 工作流框架求之不得，断断续续入门入了三次。这次能够写出这个项目主要归功于 ☕🐇 的[《Activiti 实战》](https://github.com/henryyan/activiti-in-action-codes)。这本书给予了我很大的帮助。最后但仍然重要的，我要感谢[若依框架](http://www.ruoyi.vip/)，她让我实现快速集成工作流 WEB 应用。—— 一只闲鹿
>
>  参考资料👇
>
>  1. 若依框架: [http://www.ruoyi.vip](http://www.ruoyi.vip/)
>  2. 咖啡兔：[《Activiti 实战》](https://github.com/henryyan/activiti-in-action-codes)
>  3. Activiti User Guide: <https://www.activiti.org/userguide/index.html#springSpringBoot>
>  4. XBoot: [http://xboot.exrick.cn](http://xboot.exrick.cn/)

## 内置功能

v1.6

1. Activiti Modeler 完全汉化。
2. 流程定义支持在线预览流程定义 bpmn 和流程图 png 文件。
3. 流程定义支持挂起和激活、转成流程模型。
4. 流程用户和组直接关联系统用户和角色。
5. 流程实例支持挂起和激活、撤销、委托。
6. 审批历史界面优化。
7. 进度查看高亮区分已完成和进行中的任务。
8. 首页待办界面优化。
9. 待办事项我的待办支持委托、查看申请详情、审批历史、进度查看。
10. 待办事项我的已办支持查看申请详情、审批历史、进度查看。

v1.5

1. Activiti Modeler 在线设计去除右上角关闭按钮。
2. Activiti Modeler 在线设计去除保存并关闭 Editor 按钮。
3. Activiti Modeler 提供请假流程模型图（请运行最新版数据库脚本文件）。
4. 删除冗余的 SQL 文件。
5. 修复已办结流程查看进度图报错。
6. 修复销假监听器修改请假表实际开始和结束日期无效的问题。

v1.4

1. 修复大文件(10kb左右)模型导出失效。
2. 修复模型空流程导出后台报错。
3. 流程定义上传空文件提示。
4. 去除上传 bpmn20.xml 格式流程定义文件。
5. 修复同 key 多版本流程进度图查看错误。
6. 同个任务多个办理人首页待办事项优化。

v1.3

1. 新增模型管理功能：支持模型列表、编辑、部署、导出和删除。
2. 移除 bpmn-js 在线绘图；集成 Activiti Modeler 在线绘图。
3. 不允许非待办人办理待办事项。
4. 不允许非创建人编辑、删除、提交相应表单。
5. 请假列表查询，分页问题修复。

v1.2

1. 新增首页待办事项列表、办理。
2. 新增待办已办模块：待办列表、导出；已办列表、导出。
3. 日期选择控制：只能选今天的；结束日期不能大于开始日期。
4. 提交申请刷新父页面请假列表。
5. 修复编辑查看请假类型反显出错。
6. 修复选择会签参与人渲染闪屏。
7. 流程用户不允许关联管理员。
8. 管理员不允许提交流程申请。

v1.1

1. 请假表单必填项控制。
2. 请假列表显示创建人、高亮申请人、分页修复。
3. 流程定义导出问题修复、不允许删除存在流程实例的流程定义。
4. 在线绘图部分界面汉化。
5. 首页信息修改。

v1.0

1.  新增流程 demo2：请假会签，支持请假列表、新增暂存、编辑、删除；提交申请 (含选择会签参与人)、表单数据、我的待办 (申请详情、审批、调整申请、销假)、我的已办。
2.  新增流程 demo1：请假业务，支持请假列表、新增暂存、编辑、删除；提交申请、表单数据、我的待办 (申请详情、审批、调整申请、销假)、我的已办。
3.  新增流程通用接口：审批历史和进度查看。
4.  新增流程用户组功能，支持流程用户组列表、新增、编辑、删除和导出。
5.  新增流程用户功能，支持流程用户列表、新增、编辑、删除和导出。
6.  新增流程定义功能，支持流程定义部署、列表、删除和导出。
7.  新增在线绘图功能，支持拖拽预览、在线绘图、下载 BPMN 文件和 SVG 文件。

## 在线体验

> 流程管理账号：admin / admin123
>
> 请假流程测试账号
>
> 普通员工：chengxy / 123456
>
> 部门领导：axianlu / 123456
>
> 人事：rensm / 123456

演示地址：http://proc.iloveu4ever.vip (服务器已过期)

## 演示图

<table>
    <tr>
        <td><img src="screenshot/main.png"/></td>
        <td><img src="screenshot/online.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/define.png"/></td>
        <td><img src="screenshot/user.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/useradd.png"/></td>
        <td><img src="screenshot/group.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/groupadd.png"/></td>
        <td><img src="screenshot/list.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/add.png"/></td>
        <td><img src="screenshot/detail.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/history.png"/></td>
        <td><img src="screenshot/process.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/todo.png"/></td>
        <td><img src="screenshot/done.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/process2.png"/></td>
        <td><img src="screenshot/select.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/my-todo.png"/></td>
        <td><img src="screenshot/handle.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/todoitem.png"/></td>
        <td><img src="screenshot/doneitem.png"/></td>
    </tr>
    <tr>
        <td><img src="screenshot/modellist.png"/></td>
        <td><img src="screenshot/modeler.png"/></td>
    </tr>
</table>

## 闲鹿工作流交流群

QQ群：794711759(满)、813539310（满）、1137171616。

![闲鹿工作流交流群](screenshot/qqgroup.png)
![闲鹿工作流交流群2](screenshot/qqgroup2.png)

## 视频教程

[【闲鹿课堂】2020最新 Activiti6整合Spring Boot2快速入门教程](<https://www.bilibili.com/video/BV1Fp4y19729>)


