## 更新日志   :id=log

> 当前版本：`layui 3.6.0.release`，更新于：`2021-01-25`，查看 [在线演示](http://layui.pearadmin.com)。


#### 2021-02-05 （ 3.6.0.release ）   :id=log_315

- [新增] 新增全局主题，IFRAME 内部页面主页色跟随
- [新增] 新增选项卡记忆，可通过配置文件开启关闭
- [新增] 新增顶部栏主题跟随，可通过配置文件 autoHead 属性开启
- [优化] 将主题代码独立于 admin.js, 命名为 theme.js 模块，保证单一职责
- [优化] 配置文件兼容 yml / json 两种格式，通过 admin.setConfigType 切换解析方式
- [修复] 修复 iusses 部分提交问题
- [修复] 修复 area 组件多实例隔离问题

> 升级替换 component / pear 目录即可

#### 2020-12-06 （ 3.3.0.release ）   :id=log_315

- [增加] 新增 yaml.js 解析组件，提供 yml 文件解析支持
- [增加] 新增 http.js 异步请求模块，扩展原生 jquery.ajax 异常捕捉
- [优化] 替换 json 配置文件为 yaml, 增加可读性
- [优化] 调整 admin.css 部分样式
- [优化] 升级 Layui 依赖至 2.5.7 版本
- [修复] 修复 PE 登陆页面，验证码错位问题

> 升级替换 component / pear 目录即可

#### 2020-11-11 （ 3.2.5.release ）   :id=log_316

- [新增] 新增 context 组件，用于获取上下文全局变量
- [新增] 新增 convert cropper 类型转换 与 图片裁剪 组件
- [新增] 新增头像上传示例，uploadProfile 页面
- [新增] 新增 admin.setConfigPath() 允许开发者自定义 pear.config.json 的读取路径
- [新增] 菜单同时支持 async 异步接口方式 与 静态 JSON 数据模式

> 升级替换 component / pear 目录即可

#### 2020-10-01 （ 3.1.2.release ）   :id=log_317

- [优化] 解决 树状表格 层级缩进
- [优化] 优化 light-theme 菜单主题的阴影大小
- [优化] 兼容 多系统菜单 移动端下显示效果
- [优化]  解决 Ajax 同步与浏览器 UI 渲染冲突
- [新增] 新增 基础信息 与 空白布局 页面

> 升级替换 component / pear 目录即可
