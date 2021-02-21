//从cookie中获得语种
var locale    =  getLanguage();
var messages  =  {};
var i18n      =  new VueI18n({ locale: locale, messages:messages }) ;
var year      =  pTime.getYear();
var vm        =  new Vue({
    el:'#okr',
    i18n:i18n,
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
                init();
            }
        });
    },
    mounted:function () {
        var self = this;
    },
});

function init() {
    validateKickout();
    validateRule();
    $('.imgcode').click(function() {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });
}

function getVueI18nKey(key) {
    return  vm.$i18n.t(key);
}

$.validator.setDefaults({
    submitHandler: function() { login(); }
});

function login() {
    $.modal.loading($("#btnSubmit").data("loading"));
    var username     = $.common.trim($("input[name='username']").val());
    var password     = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe   = $("input[name='rememberme']").is(':checked');
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "validateCode": validateCode,
            "rememberMe": rememberMe
        },
        success: function(r) {
            if (r.code == 0) {
                location.href = ctx + 'index';
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
    $("#signupForm").validate({
        rules: {
            username: { required: true },
            password: { required: true }
        },
        messages: {
            username: {
                required: icon + getVueI18nKey("login.entername"),
            },
            password: {
                required: icon + getVueI18nKey("login.entercode"),
            }
        }
    })
}

function validateKickout() {
    if (getParam("kickout") == 1) {
        layer.alert("<font color='red'>"+getVueI18nKey("login.entercode")+"</font>", {
                icon: 0,
                title: getVueI18nKey("login.systip")
            },
            function(index) {
                //关闭弹窗
                layer.close(index);
                if (top != self) {
                    top.location = self.location;
                } else {
                    var url  =  location.search;
                    if (url) {
                        var oldUrl  = window.location.href;
                        var newUrl  = oldUrl.substring(0,  oldUrl.indexOf('?'));
                        self.location  = newUrl;
                    }
                }
            });
    }
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}