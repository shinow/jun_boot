//从cookie中获得语种
var locale    =  getLanguage();
var messages  =  {};
var i18n      =  new VueI18n({ locale: locale, messages }) ;
var year      =  pTime.getYear();
var vm        =  new Vue({
    el:'#okr',
    i18n ,
    data:{
        locale:locale,
        username: '',
        password: '',
        error: false,
        copyright:year+' All Rights Reserved. xiaoshuai2233@sina.com',
        version:'V2.0.0'
    },
    methods: {
        switchLanguage: function (language,branch) {
            let self = this;
            $.ajax({
                type: "get",
                url: ctx + "i18n/switchLanguage",
                data: {
                    "language": language,
                    "branch": branch,
                },
                success: function(r) {
                    for (key in r) {
                        if(r[key]){
                            self.$i18n.mergeLocaleMessage(key,r[key]);
                            self.$i18n.locale = key ;
                        }
                    }
                }
            });
        }
    },
    created:function () {
        //请求语言包
        let self = this;
        $.ajax({
            type: "get",
            url: ctx + "i18n/getDefaultLanguage",
            data: {branch:"login"},
            success: function(r) {
                for (key in r) {
                    if(r[key]){
                        self.$i18n.mergeLocaleMessage(key,r[key]);
                        self.$i18n.locale = key ;
                    }
                }
            }
        });
    },
    mounted:function () {
        var self = this;
    },
});

$(function() {
    validateRule();
    $('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
    	register();
    }
});

function register() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $.common.trim($("input[name='username']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    $.ajax({
        type: "post",
        url: ctx + "register",
        data: {
            "loginName": username,
            "password": password,
            "validateCode": validateCode
        },
        success: function(r) {
            if (r.code == 0) {
            	layer.alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", {
        	        icon: 1,
        	        title: "系统提示"
        	    },
        	    function(index) {
        	        //关闭弹窗
        	        layer.close(index);
        	        location.href = ctx + 'login';
        	    });
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
    $("#registerForm").validate({
        rules: {
            username: {
                required: true,
                minlength: 2
            },
            password: {
                required: true,
                minlength: 5
            },
            confirmPassword: {
                required: true,
                equalTo: "[name='password']"
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名不能小于2个字符"
            },
            password: {
            	required: icon + "请输入您的密码",
                minlength: icon + "密码不能小于5个字符",
            },
            confirmPassword: {
                required: icon + "请再次输入您的密码",
                equalTo: icon + "两次密码输入不一致"
            }
        }
    })
}
