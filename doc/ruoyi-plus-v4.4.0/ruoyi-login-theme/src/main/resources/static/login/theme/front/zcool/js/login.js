var cookieName=getCookie('front_username');
var cookieNamePhone=getCookie('front_username_phone');
var cookiePass=getCookie('front_password');
var cookieRememberMe=getCookie('front_rememberMe');

//手机号
function isTelePhone(v){
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(v)) {
        return false;
    } else {
        return true;
    }
}

function getCookie(name){
    //获取cookie字符串
    var strCookie=document.cookie;
    //将多cookie切割为多个名/值对
    var arrCookie=strCookie.split("; ");
    var value="";
    //遍历cookie数组，处理每个cookie对
    for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("=");
        if(name==arr[0]){
            value=arr[1];
            break;
        }
    }
    return value;
}

// 手机验证码倒计时；
var wait = 60;
function smsWaitTime(o, waitP) {
    if (wait == 0) {
        $(o).removeAttr('disabled');
        $(o).removeClass("btn-disabled").addClass('btn-default-main').removeAttr('disabled');
        $(o).val("发送验证码");
        wait = waitP;
        $(o).attr('time', '1')
        //$('.js-no-ver-code').show();
    } else {
        $(o).prop('disabled', true);
        $(o).addClass("btn-disabled").removeClass('btn-default-main').attr('disabled',true);
        $(o).attr('disabled', 'disabled');
        $(o).val(wait + 's' + "后重新发送");
        $(o).attr('time', '0')
        wait--;
        setTimeout(function () { smsWaitTime(o, waitP) }, 1000);
    }
}

var login = {
    showTip: function(a, b) {
        $(".static-module-covers").css("margin-top","67px");
        $("#pass-error-tips").show();
        b.html(a),
        setTimeout(function() {
            b.html ("");
            $("#pass-error-tips").hide();
            $(".static-module-covers").css("margin-top","45px");
        },6000);
    },
    showTip_sms: function(a, b) {
        $(".static-module-covers").css("margin-top","67px");
        $("#sms-error-tips").show();
        b.html(a),
            setTimeout(function() {
                b.html ("");
                $("#sms-error-tips").hide();
                $(".static-module-covers").css("margin-top","45px");
            },6000);
    },
    /* 密码登陆 */
    login: function() {
        var a = document,
            b = a.getElementById("username"),
            c = a.getElementById("password");
        var errorObj=$("#pass-error-tips .js-err-l");
        var rememberMe = $("input[name='rememberMe']").is(':checked');
        if ($.common.isEmpty(b.value.trim())) return b.focus(),
        void login.showTip("手机号/邮箱不能为空", errorObj);
        if (!/^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/.test(b.value.trim())&& b.value.trim().indexOf("@")>0) return b.focus(),
        void login.showTip("邮箱格式不正确", errorObj);
        if ($.common.isEmpty(c.value.trim())) return c.focus(),
        void login.showTip("密码不能为空", errorObj);

         errorObj.html ("");
         $("#pass-error-tips").hide();
        $("#loginbtn").hide().next().show();
        var okFlag=false;
        $.ajax({
            async: false,
            type: "post",
            url: ctx + "checkAccount",//检测账户是否注册
            xhrFields: {
                withCredentials: true
            },
            data: {
                "username": b.value
            },
            success: function(r) {
                if (r.code == 0) {
                    //已经注册则继续向下走
                    okFlag=true;
                } else {
                    $("#loginbtn").show().next().hide();
                    if(r.msg.indexOf("未注册")>0){
                        var msg='帐号未注册，你可以<a href="/register" target="_blank">去注册</a>';
                        login.showTip(msg, errorObj);
                    }else{
                        login.showTip(r.msg, errorObj);
                    }
                    return;
                }
            }
        });
        if(!okFlag){
            return;
        }
        //执行登录
        $.ajax({
            type: "post",
            url: ctx + "login/front",
            xhrFields: {
                withCredentials: true
            },
            data: {
                "username": b.value,
                "password": c.value,
                "validateCode" : "",
                "rememberMe": rememberMe
            },
            success: function(r) {
                $("#loginbtn").show().next().hide();
                if (r.code == 0) {
                    window.top.location.href = ctx + 'front/index';
                } else {
                    //$.modal.closeLoading();
                    login.showTip(r.msg, errorObj);
                    //$.modal.msg(r.msg);
                }
            }
        });
    },
    /* 短信登陆 */
    login_sms: function() {
        var a = document,
            b = a.getElementById("sms-phone"),//手机号
            c = a.getElementById("sms-cd");//验证码
        var errorObj=$("#sms-error-tips .js-err-l");
        var rememberMe = $("input[name='rememberMe_sms']").is(':checked');
        if ($.common.isEmpty(b.value.trim())) return b.focus(),
            void login.showTip_sms("手机号不能为空", errorObj);
        if (!isTelePhone(b.value.trim())) return b.focus(),
            void login.showTip_sms("手机号格式不正确", errorObj);
        if ($.common.isEmpty(c.value.trim())) return c.focus(),
            void login.showTip_sms("验证码不能为空", errorObj);

        errorObj.html ("");
        $("#pass-error-tips").hide();
        $("#sms-login-sub").hide().next().show();
        $.ajax({
            type: "post",
            url: ctx + "login/sms",//手机短信登录
            xhrFields: {
                withCredentials: true
            },
            data: {
                "phone": b.value,
                "code": c.value,
                "rememberMe": rememberMe
            },
            success: function(r) {
                $("#sms-login-sub").show().next().hide();
                if (r.code == 0) {
                    window.top.location.href = ctx + 'front/index';
                } else {
                    login.showTip_sms(r.msg, errorObj);
                }
            }
        });
    },
    //发送手机验证码
    send_sms:function(){
        var v=$("#sms-phone").val();
        var errorObj=$("#sms-error-tips .js-err-l");
        if($.common.isEmpty(v)){
            login.showTip_sms("手机号不能为空!", errorObj);
            return;
        }
        if(!isTelePhone(v)){
            login.showTip_sms("手机号格式不正确!", errorObj);
            return;
        }
        var okFlag=false;

        //检测手机是否注册
        $.ajax({
            async: false,
            type: "post",
            url: ctx + "checkPhone",//检测手机是否注册
            xhrFields: {
                withCredentials: true
            },
            data: {
                "phone": v
            },
            success: function(r) {
                if (r.code == 0) {
                   //已经注册则继续向下走
                    okFlag=true;
                } else {
                    if(r.msg.indexOf("未注册")>0){
                        var msg='该手机号尚未注册，你可以<a href="/register" target="_blank">去注册</a>';
                        login.showTip_sms(msg, errorObj);
                    }else{
                        login.showTip_sms(r.msg, errorObj);
                    }
                    return;
                }
            }
        });

        if(!okFlag){
            return;
        }

        $.ajax({
            type: "get",
            url: ctx + "login/sms/send",//发送手机验证码
            xhrFields: {
                withCredentials: true
            },
            data: {
                "phone": v
            },
            success: function(r) {

                if (r.code == 0) {
                    $.modal.msgSuccess(r.msg);
                    smsWaitTime($('#sms-send-cd-btn'), 60);
                } else {
                    login.showTip_sms(r.msg, errorObj);
                }
            }
        });
    }
};

$(function() {

    if(cookieRememberMe=="1"){
        $("#autolog1").attr("checked","checked");
        $("#autolog1").parent().removeClass("auto-login-cd").removeClass("auto-login-c").addClass("auto-login-cd");
    }else{
        $("#autolog1").removeAttr("checked");
        $("#autolog1").parent().removeClass("auto-login-cd").removeClass("auto-login-c").addClass("auto-login-c");
    }
    if($.common.isNotEmpty(cookieName)){
        $("#username").val(cookieName);
    }
    if($.common.isNotEmpty(cookieNamePhone)){
        $("#sms-phone").val(cookieNamePhone);
    }
    if($.common.isNotEmpty(cookiePass)){
        $("#password").val(cookiePass);
    }

    //发送手机验证码
    $("#sms-send-cd-btn").click(function(){
        login.send_sms();
    });

    //微信和电脑登录的切换
    $(".login-switch .icon-l").click(function(){
        $(this).css("display","none");
        $(this).siblings().css("display","block");
        if($(this).hasClass("static-l")){
            $("#login-box").removeClass("module-l-static").addClass("module-l-quick");
        }else{
            $("#login-box").removeClass("module-l-quick").addClass("module-l-static");
        }
    });
    //记住登录
    $("#autolog1,#autolog2").click(function(){
        var b=$(this).prop("checked");

        if(b){
            $(this).attr("checked","checked");
            $(this).parent().removeClass("auto-login-cd").removeClass("auto-login-c").addClass("auto-login-cd");
        }else{
            $(this).removeAttr("checked");
            $(this).parent().removeClass("auto-login-cd").removeClass("auto-login-c").addClass("auto-login-c");
        }
    });
    //密码登录和短信登录切换
    $(".l-tab-covers .l-tab-list").click(function(){
        var index=$(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        if(index==0){//密码登录
            $(".sms-login-covers").addClass("hide");
            $(".pass-login-covers").removeClass("hide");
        }
        if(index==1){//短信登录
            $(".pass-login-covers").addClass("hide");
            $(".sms-login-covers").removeClass("hide");
        }
    });


});