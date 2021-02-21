## 基本介绍

Button 组件, 为按钮元素提供加载效果


## 使用方式

#### 加载指定时长

```javascript
layui.use(['button'],function(){
	const button = layui.button;
	
	button.load({
	    elem:'[load]',
	    time: 600,
	    done: function(){
	        popup.success("加载完成");
	    }
	})
})
```
- elem 元素 兼容 $ 选择器
- time 加载时长
- done 回调函数 (完成时调用)


#### 手动停止

当你触发加载时，它并不会停止，你需要用指定的 API 来停止

```javascript
layui.use(['button'],function(){
	
	const button = layui.button;
	
	var dom = button.load({
	    elem:'[load]',
	})
	
	dom.stop(function() {
	    popup.failure("已停止");
	});
	
})
```
- elem 监听元素 [load]
- dom 加载按钮实例
- dom.stop(callback) 停止加载效果，并执行函数
