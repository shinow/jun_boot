(function ($) {
    $.extend({
        myOperate:{
            jsonPost: function (url, data, f, b) {
                console.log("data:" + JSON.stringify(data))
                var config = {
                    url: url,
                    type: "post",
                    contentType : 'application/json',
                    data:JSON.stringify(data),
                    beforeSend: function () {
                        if (typeof b === "function") {
                            b();
                        } else {

                        }
                    },
                    success: function (res) {
                        console.log("请求成功")
                        if(typeof f === "function"){
                            f(res);
                        }else{
                            console.log("入参不是函数")
                        }
                    },
                    error: function (res) {
                        console.log("post请求错误:" + url + ":" + JSON.stringify(res))
                    }
                };
                $.ajax(config)
            },
            formPost: function (url, data, f, b) {
                console.log("data:" + JSON.stringify(data))
                var config = {
                    url: url,
                    type: "post",
                    data: data,
                    beforeSend: function () {
                        if (typeof b === "function") {
                            b();
                        } else {

                        }
                    },
                    success: function (res) {
                        console.log("请求成功")
                        if(typeof f === "function"){
                            f(res);
                        }else{
                            console.log("入参不是函数")
                        }
                    },
                    error: function (res) {
                        console.log("post请求错误:" + url + ":" + JSON.stringify(res))
                    }
                };
                $.ajax(config)
            },
            formPostSynchronous: function (url, data, f, b) {
                console.log("data:" + JSON.stringify(data))
                var config = {
                    url: url,
                    async:false,
                    type: "post",
                    data: data,
                    beforeSend: function () {
                        if (typeof b === "function") {
                            b();
                        } else {

                        }
                    },
                    success: function (res) {
                        console.log("请求成功")
                        if(typeof f === "function"){
                            f(res);
                        }else{
                            console.log("入参不是函数")
                        }
                    },
                    error: function (res) {
                        console.log("post请求错误:" + url + ":" + JSON.stringify(res))
                    }
                };
                $.ajax(config)
            },
            jsonGet: function (url, f) {
                var config = {
                    url: url,
                    type: "get",
                    contentType : 'application/json',
                    beforeSend: function () {
                    },
                    success: function (res) {
                        console.log("请求成功")
                        if(typeof f === "function"){
                            f(res);
                        }else{
                            console.log("入参不是函数")
                        }
                    },
                    error: function (res) {
                        console.log("get请求错误:" + url + ":" + JSON.stringify(res))
                    }
                };
                $.ajax(config)
            },
            jsonPostUploadIMG: function (url, file, f) {
                var xhr = new XMLHttpRequest()
                // new一个FormData实例
                var formData = new FormData()
                xhr.responseType="json"
                // 将file对象添加到FormData实例
                formData.append('file', file)
                xhr.open('POST', url, true)
                xhr.send(formData)
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            var obj = xhr.response
                            if(typeof f === "function"){
                                f(obj);
                            }else{
                                console.log("入参不是函数")
                            }

                        } else {
                            console.log("post上传图片请求错误:" + url)
                        }
                    }
                }
            }
        },
        myLayui: {
            closeLayerAndRfresh: function () {
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
                parent.location.reload();
            },
            errorMsg: function (msg) {
                var msgLayer = layer.msg(msg, {icon: 5, time: false});
                setTimeout(function () {
                    layer.close(msgLayer)
                }, 900)

            },
            successMsg: function (msg) {
                var msgLayer = layer.msg(msg, {icon: 6, time: false});
                setTimeout(function () {
                    layer.close(msgLayer)
                }, 900)
            },
            openType2: function (url, title) {
                layer.open({
                    type: 2,
                    fixed: false,
                    maxmin: true,
                    title: title,
                    closeBtn: 1, //不显示关闭按钮
                    area: ['700px', '700px'],
                    content: [url, 'yes'], //iframe的url，no代表不显示滚动条
                    end: function(){
                        location.reload();
                    }
                });
            },
            openFullScreen: function (url) {
                var w = document.documentElement.clientWidth || document.body.clientWidth;
                w = w - 50;
                var h = document.documentElement.clientHeight || document.body.clientHeight;
                h = h - 50;
                console.log(w + ":" + h)
                layer.open({
                    type: 2,
                    title: false,
                    closeBtn: 1, //不显示关闭按钮
                    area: [w + "px", h + "px"],
                    content: [url, 'no'], //iframe的url，no代表不显示滚动条
                    end: function(){
                    }
                });
            },
            confirm: function (msg, f) {
                layer.confirm(msg, {
                    btn: ['取消', '确定'] //按钮
                }, function(){
                    layer.msg("取消", {time: 1000})
                }, function(){
                   f()

                });
            }
        },
        opData: {
            getFormJson: function (id) {
                var arr = $(id).serializeArray();                                //serializeArray() 方法通过序列化表单值来创建对象数组（名称和值）。
                console.log("arr:" + JSON.stringify(arr))
                var param = {};
                $.each(arr,function(i,obj){ //将form表单数据封装成json对象
                    if (param[obj.name] != null) {
                        param[obj.name] = param[obj.name] + "," + obj.value;
                    } else {
                        param[obj.name] = obj.value;
                    }

                })
                return param;
            }
        }
    })

})(jQuery);