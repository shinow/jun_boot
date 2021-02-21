/* 返回顶部插件 */
(function ($) {
    $.fn.toTop = function (opt) {
        //variables
        var elem = this;
        var win = $(window);
        var doc = $('html, body');
        var options = opt;
        //如果没有配置自定义的参数，则使用默认
        if (!options) {
            options = $.extend({
                autohide: true,
                offset: 100,
                speed: 500,
                right: 15,
                bottom: 50
            }, opt);
        }
        elem.css({
            'position': 'fixed',
            'right': options.right,
            'bottom': options.bottom,
            'cursor': 'pointer'
        });
        if (options.autohide) {
            elem.css('display', 'none');
        }
        elem.click(function () {
            doc.animate({scrollTop: 0}, options.speed);
        });
        win.scroll(function () {
            var scrolling = win.scrollTop();
            if (options.autohide) {
                if (scrolling > options.offset) {
                    elem.fadeIn(options.speed);
                }
                else elem.fadeOut(options.speed);
            }
        });
    };
})(jQuery);
/* 返回顶部插件 end */

$(function(){
    $('.back-to-top').toTop({
        autohide: true,//返回顶部按钮是否自动隐藏。可以设置true或false。默认为true
        offset: 100,//页面滚动到距离顶部多少距离时隐藏返回顶部按钮。默认值为420
        speed: 500,//滚动和渐隐的持续时间，默认值为500
        right: 25,//返回顶部按钮距离屏幕右边的距离，默认值为15
        bottom: 50//返回顶部按钮距离屏幕顶部的距离，默认值为30
    });

    if(isMac()){
        $("body").addClass("mac");
    }


})
var staticLoaddingSrc =  "/front/zcool/images/page_loading.gif",
    LoaddingDom = '<div id="ajaxfeed-loading" style="text-align: center;"><img src="' + staticLoaddingSrc + '"></div>';
var isMac = function() {
        return /macintosh|mac os x/i.test(navigator.userAgent)
    },
    isWindows = function() {
        return /windows|win32/i.test(navigator.userAgent)
    };
var html_encode = function(e) {
        return e.replace(/</g, "&lt;").replace(/>/g, "&gt;")
    },
    cancelbuble = function(e) {
        e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = !0
    };
function hideGlobalMaskLayer() {
    $(".js-seconds-shade").is(":visible") ? $(".js-seconds-shade").remove() : ($(".shade").hide().removeClass("project-view"), $("html").removeClass("body-fixed"), $(".mask-layer").addClass("hide"), isWindows() && $("html").removeClass("scroll-fixed"))
}
function showGlobalMaskLayer() {
    $(".shade").is(":visible") ? $("html").append($('<div class="js-seconds-shade"></div>')) : ($(".shade").show().addClass("project-view"), $("html").addClass("body-fixed"), isWindows() && $("html").addClass("scroll-fixed"))
}

function popFirstInputFocus(e) {
    var t = e.val();
    e.focus(),
    e.val(""),
    e.val(t);
}

function navTypeTopFix() {
    var e = $(document),
        t = $(".subnav-content-wrap"),
        n = t.position().top;
    t.height();
    $(".subnav-content-wrap").css("height", t.height()),
        window.onresize = function() {
            n = t.position().top
        },
        $(window).on("scroll",
            function(t) {
                var o = document.body || document.documentElement && document.documentElement,
                    r = window.pageXOffset || o.scrollLeft,
                    i = window.pageYOffset || o.scrollTop;
                i >= 0 && (0 == $("#confighome").is(":visible") ? e.scrollTop() >= n ? ($(".subnav-wrap").addClass("tab-nav-fixed"), $(".temporary-entrance").fadeOut("fast")) : ($(".subnav-wrap").removeClass("tab-nav-fixed"), $(".temporary-entrance").fadeIn("fast")) : e.scrollTop() >= n + $("#confighome").height() ? $(".subnav-wrap").addClass("tab-nav-fixed") : $(".subnav-wrap").removeClass("tab-nav-fixed")),
                r >= 0 && $(".tab-nav-fixed").css("left", -window.pageXOffset)
            })
}
//弹出成功消息提示
function pageToastSuccess(e, t) {
    $(".toast-success").find(".toast-tips-text .toast-first-text").html(e),
    t && $(".toast-tips-text .toast-socend-text").html(t),
        $(".toast-success").addClass("scale"),
        setTimeout(function() {
            $(".toast-success").removeClass("scale")
        },2500);
}
//弹出失败消息提示
function pageToastFail(e, t) {
    $(".toast-fail").find(".toast-tips-text .toast-first-text").html(e),
    t && $(".toast-tips-text .toast-socend-text").html(t),
        $(".toast-fail").addClass("scale"),
        setTimeout(function() {
            $(".toast-fail").removeClass("scale")
        }, 2500);
}

function hideConfirmMaskLayer() {
    $("html").find(".confirm-shade").remove(),
        $("html").removeClass("confirm-body-fixed"),
    isWindows() && !$(".shade:not([data-discriminate])").is(":visible") && $("html").removeClass("scroll-fixed")
}
function showConfirmMaskLayer() {
    $("html").append($('<div class="confirm-shade shade" data-discriminate="second"></div>')),
        $("html").addClass("confirm-body-fixed"),
    isWindows() && $("html").addClass("scroll-fixed")
}

//点击提交按钮后设置为按钮不可用
function loaddingBtnDis(e) {
    e.attr({
        disabled: !0,
        "data-val": e.val()
    }),
        e.val("提交中...").addClass("btn-disabled").removeClass("btn-default-main")
}
//恢复提交按钮后不可用状态为可用
function cancelLoaddingBtnDis(e) {
    e.attr("data-val") && e.val(e.attr("data-val")),
        e.removeAttr("disabled").removeClass("btn-disabled").addClass("btn-default-main")
}

function pageConfirm(msg, t, okCallBack, cancleCallBack) {
    showConfirmMaskLayer();
    var r = $(".pop-up-confirm");
    $(".pop-up-confirm").removeClass("hide").find(".tips-text").html(msg),
        $(".pop-up-confirm").css("margin-top", -(r.innerHeight() / 2)),
        $(".pop-up-confirm .pop-confirm").unbind(),
        $(".pop-up-confirm .pop-confirm").on("click",
            function() {
                $(".pop-up-confirm").addClass("hide"),
                    hideConfirmMaskLayer(),
                okCallBack && okCallBack();
            }),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").unbind(),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").on("click",
            function() {
                $(".pop-up-confirm").addClass("hide"),
                    hideConfirmMaskLayer(),
                cancleCallBack && cancleCallBack();
        });
}
function pageConfirmSeconds(msg, t, okCallBack, cancleCallBack) {
    showConfirmMaskLayer(),
        $(".pop-up-confirm").removeClass("hide").find(".tips-text").html(e),
        $(".pop-up-confirm .pop-confirm").unbind(),
        $(".pop-up-confirm .pop-confirm").on("click",
            function() {
                okCallBack && (loaddingBtnDis($(this)),okCallBack())
            }),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").unbind(),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").on("click",
            function() {
                $(".pop-up-confirm").addClass("hide"),
                    hideConfirmMaskLayer(),
                    cancelLoaddingBtnDis($(".pop-up-confirm .pop-confirm")),
                cancleCallBack && cancleCallBack()
            })
}

function zcoolAlert(msg, okCallBack, closeCallBack) {
    $(".alert-confirm").removeClass("hide"),
    $(".alert-confirm .tips-alert-text").html(msg);
    var o = $(".alert-confirm").width() / 2,
        r = $(".alert-confirm").innerHeight() / 2;
    $(".alert-confirm").css({
        "margin-left": -o,
        "margin-top": -r
    }),
    $(".alert-confirm .pop-confirm").on("click",
            function() {
                $(this).parents(".alert-confirm").addClass("hide"),
                    $(this).unbind("click"),
                okCallBack && okCallBack()
            }),
    $(".alert-confirm .pop-close").on("click",
            function() {
                $(this).parents(".alert-confirm").addClass("hide"),
                    $(this).unbind("click"),
                closeCallBack && closeCallBack()
    });
}


function getUid() {
    for (var e = document.cookie.split("; "), t = "0", n = 0; n < e.length; n++) {
        var o = e[n].split("="),
            r = o[0],
            i = o[1];
        if ("uid" == r) {
            t = i;
            break
        }
    }
    return t
}
function islogin() {
    return getUid() > 0
}
function isNotlogin() {
    return getUid() <= 0;
}

function openLoginWindow(e) {
    $(".pop-login").length || $('<div class="pop-login hide" id="popLogin">' +
        '<iframe allowTransparency="true" id="loginChild" width="400" height="500" name="loginChild" scrolling="no" src="/login/frame" style="background-color:transparent" marginwidth="0" frameborder="0" width="100%" height="100%"></iframe></div>').appendTo("body"),
        $(".pop-login").show(),
        showGlobalMaskLayer(),
    $(".login-close").length || $(".pop-login").append('<div class="login-close"></div>'),
        $("#popLogin").off("click"),
        $(".pop-login").on("click", ".login-close",
            function() {
                $(".pop-login").hide(),
                    hideGlobalMaskLayer()
            }),
        window.zcoolLoginSuccessUpdatePageCb = e
}
function initLoginSuccessPage() {
    var e, t = window.zcoolLoginSuccessUpdatePageCb;
    t ? (e = function() {
        hideGlobalMaskLayer(),
            $(".pop-login").hide(),
            update_logined_nav(),
            $.each(t,
                function(e, t) {
                    t()
                });
        var e = $(".upload a").attr("data-click-login");
        e && "1" === e && $(".upload a").attr("data-click-login", "0")
    })() : (hideGlobalMaskLayer(), $(".pop-login").hide(), update_logined_nav())
}
function writeUidToCookie(uid) {
    document.cookie = "uid=" + encodeURI(uid) + ";expires=1;path=/";
}
function deleteUidToCookie() {
    var uid=getUid();
    document.cookie = "uid=-1"+ ";expires=-1;path=/";
}
function syncUidToCookie(uid) {
    var oldUid = getUid();
    var  newUid = uid;
    if(newUid==oldUid){
    }else{
        writeUidToCookie(newUid);
    }
}
function update_logined_nav(e) {
    function t() {
        $.ajax({
            type: "GET",
            url: "/nav/getUserInfo?_t=" + n,
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(json) {
                if (!$("#user-info").length) return ! 1;
                var n = template("user-info", json);
                if (n.indexOf("{Template Error}") >= 0) {
                    $(".user-center>.login").addClass("hide"),
                    $(".user-center>.unlogin").removeClass("hide");
                } else{
                    $(".user").html(n);
                    //countMessage();
                    if(0 == json.code){
                        //syncUserInfoToCookie(json.data);
                        syncUidToCookie(json.data.uid);
                        e && e();
                    }

                }
            },
            error: function(e, t, n) {
                if (reTtime <= 3) {
                    reTtime += 1,
                        update_logined_nav();
                }
            }
        })
    }
    $(".user").html("");
    $(".user-center>.login").removeClass("hide");
    $(".user-center>.unlogin").addClass("hide");
    $("header .user-center").removeClass("user-center-unlogin");
    $("header .search").removeClass("search-unlogin");
    var n = (new Date).getTime();
    t();
    //getNavMsgCenterInfo();
    //document.cookie = "r_drefresh_count=0;expires=1;path=/;domain=" + zRootDomain
}
function logout_update_unlogined_nav() {
    $(".user-center>.login").addClass("hide"),
        $(".user-center>.unlogin").removeClass("hide"),
        $("header .user-center").addClass("user-center-unlogin"),
        $("header .search").addClass("search-unlogin"),
        $(".js-header_upload a").attr("href", "javascript:;");
        //unloginedUpdateStatus();
}
function asyncRequestIsExistPhone(successCallBack, errorCallBack) {
    $.ajax({
        type: "post",
        url: "/nav/isBindPhone",
        data: {},
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(json) {
            successCallBack(json);
        },
        error: function() {
            errorCallBack && errorCallBack();
        }
    })
}

//弹出提示绑定手机的对话框
function publishedbindLayerShow() {
    $(".pop-up").hide();
    showGlobalMaskLayer();
        zcoolAlert("为确保您账户的安全及正常使用，依《网络安全法》相关要求，2017年6月1日起会员发布信息需绑定手机。",
            function() {
                hideGlobalMaskLayer();
                window.open("/modifyphone_view?type=1");
                $(".alert-confirm .pop-confirm").val("我知道了");
            },
            function() {
                hideGlobalMaskLayer();
                $(".alert-confirm .pop-confirm").val("我知道了");
            }),
        $(".alert-confirm").addClass("remind-bind-pop"),
        $(".alert-confirm .pop-confirm").val("去绑定")
}
function showRemindBindLayer(e, t) {
    /*getUid() > 0 ? remindBindFlag && (remindBindFlag = !1, asyncRequestIsExistPhone(function(t) {
            0 == t.data ? publishedbindLayerShow() : e && e(),
                remindBindFlag = !0
        },
        function() {
            remindBindFlag = !0
        })) : (cb = function() {
        "function" == typeof t && t()
    },
        doLoginWindow(cb));*/

    if(islogin()){
        //if(!remindBindFlag){
            asyncRequestIsExistPhone(function(t) {

                    if(!(0 == t.code)){ console.log(t);
                        publishedbindLayerShow();
                    }else{
                        e && e();
                    }
                },
                function() {

                }
            )
        //}
    }else{
        var cb = function() {
            "function" == typeof t && t()
        };
        doLoginWindow(cb);
    }
}
function loginUploadEditEntryLocation(url) {
    function t() {
        AEleUnbindPhoneTransfer(url);
    }
    if (islogin()) t();
    else {
        var n = new Array;
        n.push(t),
        openLoginWindow(n);
    }
}
function AEleUnbindPhoneTransfer(url) {
    function t() {
        window.location.href = url
    }
    showRemindBindLayer(t);
}
$(function() {
    $(".js-header_upload a").on("click",
        function() {
            loginUploadEditEntryLocation($(".js-header_upload a").attr("data-upload"));
            islogin() || $(".js-header_upload a").attr("data-click-login", "1");
    });
    if(islogin()){
        "function" == typeof update_logined_nav && update_logined_nav();
    }else{
        "function" == typeof logout_update_unlogined_nav && logout_update_unlogined_nav(), getPassportEC();
    }
});
function getPassportEC(){}
/*私信*/
function prepareSendMessage(id, name) {
    function n() {
        var n = new PrivatePOP({
            memberId: id,
            memberName: name
        });
        n.openPrivateWindow();
    }
    if(islogin()){
        n();
    }else{
        doLoginWindow(n);
    }
}
function doLoginWindow(e) {
    var t = e,
        n = [t];
    openLoginWindow(n)
}
    function PrivatePOP(e) {
        this.memberId = e.memberId,
        this.memberName = e.memberName,
        this.getUserListCallback = e.getUserListCallback,
        this.sendSuccessCallback = e.sendSuccessCallback;
    }

    PrivatePOP.prototype.openPrivateWindowListening = function() {
        if(islogin()){
            this.openPrivateWindow();
        }else{
            openLoginWindow(this.openPrivateWindow);
        }
    },
    PrivatePOP.prototype.openPrivateWindow = function() {
        function e() {
            function e(e) {
                var t = "";
                t += '<div class="contacts-list hide" style="height:400px;overflow-x:hidden;">',
                    t += "<ul>",
                    t += '<li class="contacts-title">最近联系人</li>',
                    $.each(e,
                        function(e, n) {
                            t += '<li data-name="' + n.name + '"><a href="javascript:;"><img src="' + n.img + '" width="30" height="30" alt=""></a>' + n.name + "</li>"
                        }),
                    t += "</ul>",
                    t += "</div>",
                    $("#del_message").parent().append(t),
                    $(".contacts-list").mCustomScrollbar({
                        theme: "dark"
                    }),
                    $(".contacts-list li").on("click mousedown",
                        function() {
                            var e = $(this).attr("data-name");
                            $("#del_message").val(e),
                                $(".contacts-list").addClass("hide")
                        })
            }
            $("#del_message").focus(function() {
                $(this).siblings(".contacts-list").removeClass("hide")
            }),
                $("#del_message").blur(function() {
                    $(this).siblings(".contacts-list").addClass("hide")
                });
            var n = getUid(),
                o = $("#del_message");
            if(n === t.memberId){
                pageToastFail("不能发消息给自己哦");
                return
            }
            if(!t.getUserListCallback || t.memberId || t.memberName){
                o.prop("value", t.memberName);
                o.prop("disabled", "disabled");
            }else{
                t.getUserListCallback(e);
                $(".private-pop").show();
                showGlobalMaskLayer();
                t.doSMSListening(t);
                t.cancelSMSListening(t);
                popFirstInputFocus($("#private-textarea"));
            }
            //return n === t.memberId ? void pageToastFail("不能发消息给自己哦") : (!t.getUserListCallback || t.memberId || t.memberName ? (o.prop("value", t.memberName), o.prop("disabled", "disabled")) : t.getUserListCallback(e), $(".private-pop").show(), showGlobalMaskLayer(), t.doSMSListening(t), t.cancelSMSListening(t), void popFirstInputFocus($("#private-textarea")))
        }
        var t = this;
        showRemindBindLayer(e);
    },
    PrivatePOP.prototype.doSMSListening = function(e) {
        $(".private-pop .pop-confirm").on("click",
            function() {
                if (e.smsContent = $("#private-textarea").val(), e.memberName = $("#del_message").val(), "" != e.memberName && "undefined" != typeof e.smsContent && "" != e.smsContent) {
                    e.disableStyle();
                    var $this = $(this);
                    e.SmsToMember(e, $this)
                } else{
                    e.ableStyle();
                    pageToastFail("" == e.memberName ? "你还没有选择发送的对象哦": "请输入私信内容");
                }
        });
    },

    //发送私信
    PrivatePOP.prototype.SmsToMember = function(e, obj) {
        $.ajax({
            type: "POST",
            url: "/messages/sendMessagePrivate",
            data: {
                content: e.smsContent,
                memberTo: e.memberName
            },
            xhrFields: {
                withCredentials: !0
            },
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(json) {
                e.privateCallback(json, obj, e)
            }
        })
    },
    PrivatePOP.prototype.privateCallback = function(json, obj, e) {
        if(0 == json.code){
            pageToastSuccess("私信发送成功");
            e.confirmClose(obj);
            $("#private-textarea").val("");
            obj.parents(".pop-up").find(".count").removeClass("exceeded").html("600");
            $("#del_message").parent().find("div").remove();
            this.sendSuccessCallback && setTimeout(this.sendSuccessCallback, 1700);
        }else{
            pageToastFail(json.msg);
        }
        e.ableStyle();
    },
    PrivatePOP.prototype.disableStyle = function() {
        $(".pop-confirm").prop("value", "发送中...");
        $(".pop-confirm").prop("disabled", !0);
    },
    PrivatePOP.prototype.ableStyle = function() {
        $(".pop-confirm").prop("value", "确定");
        $(".pop-confirm").prop("disabled", !1);
    },
    PrivatePOP.prototype.cancelSMSListening = function(e) {
        $(".private-pop .pop-cancel, .private-pop .pop-close").on("click",
            function() {
                e.confirmClose($(this))
        });
    },
    PrivatePOP.prototype.confirmClose = function(e) {
        $("#del_message").parent().find("div").remove();
        e.parents(".pop-up").hide();
        hideGlobalMaskLayer();
        $(".private-pop .pop-confirm").unbind("click");
    };


/*!art-template - Template Engine | start */
!
    function() {
        function e(e) {
            return e.replace(D, "").replace(m, ",").replace(M, "").replace(y, "").replace(j, "").split(x)
        }
        function t(e) {
            return "'" + e.replace(/('|\\)/g, "\\$1").replace(/\r/g, "\\r").replace(/\n/g, "\\n") + "'"
        }
        function n(n, o) {
            function r(e) {
                return c += e.split(/\n/).length - 1,
                l && (e = e.replace(/\s+/g, " ").replace(/<!--[\w\W]*?-->/g, "")),
                e && (e = w[1] + t(e) + w[2] + "\n"),
                    e
            }
            function i(t) {
                var n = c;
                if (A ? t = A(t, o) : a && (t = t.replace(/\n/g,
                    function() {
                        return c++,
                        "$line=" + c + ";"
                    })), 0 === t.indexOf("=")) {
                    var r = u && !/^=[=#]/.test(t);
                    if (t = t.replace(/^=[=#]?|[\s;]*$/g, ""), r) {
                        var i = t.replace(/\s*\([^\)]+\)/, "");
                        p[i] || /^(include|print)$/.test(i) || (t = "$escape(" + t + ")")
                    } else t = "$string(" + t + ")";
                    t = w[1] + t + w[2]
                }
                return a && (t = "$line=" + n + ";" + t),
                    v(e(t),
                        function(e) {
                            if (e && !d[e]) {
                                var t;
                                t = "print" === e ? m: "include" === e ? M: p[e] ? "$utils." + e: g[e] ? "$helpers." + e: "$data." + e,
                                    y += e + "=" + t + ",",
                                    d[e] = !0
                            }
                        }),
                t + "\n"
            }
            var a = o.debug,
                s = o.openTag,
                f = o.closeTag,
                A = o.parser,
                l = o.compress,
                u = o.escape,
                c = 1,
                d = {
                    $data: 1,
                    $filename: 1,
                    $utils: 1,
                    $helpers: 1,
                    $out: 1,
                    $line: 1
                },
                h = "".trim,
                w = h ? ["$out='';", "$out+=", ";", "$out"] : ["$out=[];", "$out.push(", ");", "$out.join('')"],
                D = h ? "$out+=text;return $out;": "$out.push(text);",
                m = "function(){var text=''.concat.apply('',arguments);" + D + "}",
                M = "function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);" + D + "}",
                y = "'use strict';var $utils=this,$helpers=$utils.$helpers," + (a ? "$line=0,": ""),
                j = w[0],
                x = "return new String(" + w[3] + ");";
            v(n.split(s),
                function(e) {
                    e = e.split(f);
                    var t = e[0],
                        n = e[1];
                    1 === e.length ? j += r(t) : (j += i(t), n && (j += r(n)))
                });
            var N = y + j + x;
            a && (N = "try{" + N + "}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:" + t(n) + ".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}");
            try {
                var C = new Function("$data", "$filename", N);
                return C.prototype = p,
                    C
            } catch(P) {
                throw P.temp = "function anonymous($data,$filename) {" + N + "}",
                    P
            }
        }
        var o = function(e, t) {
            return "string" == typeof t ? h(t, {
                filename: e
            }) : a(e, t)
        };
        o.version = "3.0.0",
            o.config = function(e, t) {
                r[e] = t
            };
        var r = o.defaults = {
                openTag: "<%",
                closeTag: "%>",
                escape: !0,
                cache: !0,
                compress: !1,
                parser: null
            },
            i = o.cache = {};
        o.render = function(e, t) {
            return h(e, t)
        };
        var a = o.renderFile = function(e, t) {
            var n = o.get(e) || d({
                filename: e,
                name: "Render Error",
                message: "Template not found"
            });
            return t ? n(t) : n
        };
        o.get = function(e) {
            var t;
            if (i[e]) t = i[e];
            else if ("object" == typeof document) {
                var n = document.getElementById(e);
                if (n) {
                    var o = (n.value || n.innerHTML).replace(/^\s*|\s*$/g, "");
                    t = h(o, {
                        filename: e
                    })
                }
            }
            return t
        };
        var s = function(e, t) {
                return "string" != typeof e && (t = typeof e, "number" === t ? e += "": e = "function" === t ? s(e.call(e)) : ""),
                    e
            },
            f = {
                "<": "&#60;",
                ">": "&#62;",
                '"': "&#34;",
                "'": "&#39;",
                "&": "&#38;"
            },
            A = function(e) {
                return f[e]
            },
            l = function(e) {
                return s(e).replace(/&(?![\w#]+;)|[<>"']/g, A)
            },
            u = Array.isArray ||
                function(e) {
                    return "[object Array]" === {}.toString.call(e)
                },
            c = function(e, t) {
                var n, o;
                if (u(e)) for (n = 0, o = e.length; o > n; n++) t.call(e, e[n], n, e);
                else for (n in e) t.call(e, e[n], n)
            },
            p = o.utils = {
                $helpers: {},
                $include: a,
                $string: s,
                $escape: l,
                $each: c
            };
        o.helper = function(e, t) {
            g[e] = t
        };
        var g = o.helpers = p.$helpers;
        o.onerror = function(e) {
            var t = "Template Error\n\n";
            for (var n in e) t += "<" + n + ">\n" + e[n] + "\n\n";
            "object" == typeof console && console.error(t)
        };
        var d = function(e) {
                return o.onerror(e),
                    function() {
                        return "{Template Error}"
                    }
            },
            h = o.compile = function(e, t) {
                function o(n) {
                    try {
                        return new f(n, s) + ""
                    } catch(o) {
                        return t.debug ? d(o)() : (t.debug = !0, h(e, t)(n))
                    }
                }
                t = t || {};
                for (var a in r) void 0 === t[a] && (t[a] = r[a]);
                var s = t.filename;
                try {
                    var f = n(e, t)
                } catch(A) {
                    return A.filename = s || "anonymous",
                        A.name = "Syntax Error",
                        d(A)
                }
                return o.prototype = f.prototype,
                    o.toString = function() {
                        return f.toString()
                    },
                s && t.cache && (i[s] = o),
                    o
            },
            v = p.$each,
            w = "break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",
            D = /\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g,
            m = /[^\w$]+/g,
            M = new RegExp(["\\b" + w.replace(/,/g, "\\b|\\b") + "\\b"].join("|"), "g"),
            y = /^\d[^,]*|,\d[^,]*/g,
            j = /^,+|,+$/g,
            x = /^$|,+/;
        r.openTag = "{{",
            r.closeTag = "}}";
        var N = function(e, t) {
            var n = t.split(":"),
                o = n.shift(),
                r = n.join(":") || "";
            return r && (r = ", " + r),
            "$helpers." + o + "(" + e + r + ")"
        };
        r.parser = function(e) {
            e = e.replace(/^\s/, "");
            var t = e.split(" "),
                n = t.shift(),
                r = t.join(" ");
            switch (n) {
                case "if":
                    e = "if(" + r + "){";
                    break;
                case "else":
                    t = "if" === t.shift() ? " if(" + t.join(" ") + ")": "",
                        e = "}else" + t + "{";
                    break;
                case "/if":
                    e = "}";
                    break;
                case "each":
                    var i = t[0] || "$data",
                        a = t[1] || "as",
                        s = t[2] || "$value",
                        f = t[3] || "$index",
                        A = s + "," + f;
                    "as" !== a && (i = "[]"),
                        e = "$each(" + i + ",function(" + A + "){";
                    break;
                case "/each":
                    e = "});";
                    break;
                case "echo":
                    e = "print(" + r + ");";
                    break;
                case "print":
                case "include":
                    e = n + "(" + t.join(",") + ");";
                    break;
                default:
                    if (/^\s*\|\s*[\w\$]/.test(r)) {
                        var l = !0;
                        0 === e.indexOf("#") && (e = e.substr(1), l = !1);
                        for (var u = 0,
                                 c = e.split("|"), p = c.length, g = c[u++]; p > u; u++) g = N(g, c[u]);
                        e = (l ? "=": "=#") + g
                    } else e = o.helpers[n] ? "=#" + n + "(" + t.join(",") + ");": "=" + e
            }
            return e
        },
            "function" == typeof define ? define(function() {
                return o
            }) : "undefined" != typeof exports ? module.exports = o: this.template = o
    } ();

/*!art-template - Template Engine | end */

function MaxYou(e, t) { !
    function(e) {
        e.fn.autoTextarea = function(t) {
            var n = {
                    maxHeight: null,
                    minHeight: e(this).height()
                },
                o = e.extend({},
                    n, t);
            return e(this).each(function() {
                e(this).bind("paste cut keydown keyup focus blur",
                    function() {
                        var e, t = this.style;
                        this.style.height = o.minHeight + "px",
                        this.scrollHeight > o.minHeight && (o.maxHeight && this.scrollHeight > o.maxHeight ? (e = o.maxHeight, t.overflowY = "scroll") : (e = this.scrollHeight, t.overflowY = "hidden"), t.height = e + "px")
                    })
            })
        }
    } (jQuery),
    $(e).autoTextarea({
        maxHeight: 130,
        minHeight: t || 42
    })
}
function MaxMe(e, t) {
    MaxYou(e, t)
}

function zCharCount(e, t) {
    t.warning || (t.warning = 0);
    var n = t.allowed - t.warning;
    n < 10 ? e.css("padding-right", "30px") : n < 100 ? e.css("padding-right", "40px") : n < 1e3 ? e.css("padding-right", "50px") : n < 1e4 && e.css("padding-right", "60px");
    var o = {
            allowed: 2e3,
            warning: 0,
            css: "count"
        },
        t = $.extend(o, t);
    e.charCount(t)
}
function zCharCount_withExceedCount(e, t) {
    var n = 1e4;
    t.exceed && (n = t.exceed),
        t.useWarningCount = !0,
        t.allowed = t.allowed + n,
        t.warning = n,
        zCharCount(e, t)
};

(function(e){
            e.fn.initTextareaStyle = function(t) {
            var n = {
                    height: 42
                },
                o = e.extend(n, t);
            e(this).length && ("" !== e(this).val() ? e(this)[0].scrollHeight > 130 ? e(this).css({
                overflow: "auto",
                height: "130px"
            }) : e(this)[0].scrollHeight < 130 && e(this)[0].scrollHeight > 42 ? e(this).css("height", e(this)[0].scrollHeight + "px") : e(this).css("height", o.height + "px") : e(this).css("height", o.height + "px"))
        }
        e.fn.charCount = function(t) {
            function n(n) {
                function o() {
                    for (var o = 0,
                             r = 0,
                             i = 0; i < e(n).val().length; i++) if (e(n).val().charCodeAt(i) > 255 ? (o += 2, r++) : (o++, r++), o == t.allowed) return r
                }
                for (var r = 0,
                         i = 0,
                         a = (new RegExp("[\\u4E00-\\u9FFF]+", "g"), 0); a < e(n).val().length; a++) e(n).val().charCodeAt(a) > 255 ? (r += 2, i++) : (r++, i++);
                var s = t.allowed - r;
                s < t.warning && s >= 0 ? (e(n).next().addClass(t.cssWarning), e(n).addClass("borderred")) : (e(n).next().removeClass(t.cssWarning), e(n).removeClass("borderred")),
                    s < 0 ? (e(n).next().addClass(t.cssExceeded), e(n).val(e(n).val().substr(0, o())), e(n).val().length = t.allowed, e(n).addClass("borderred")) : e(n).next().removeClass(t.cssExceeded);
                var f = 0;
                s > f && e(n).next().removeClass(t.cssExceeded),
                    t.useWarningCount ? e(n).next().html(t.counterText + t.allowed - r - t.warning) : (s < 0 && (e(n).removeClass("borderred"), e(n).next().removeClass(t.cssExceeded), s = 0), e(n).next().html(t.counterText + s))
            }
            var o = {
                    useWarningCount: !1,
                    allowed: 140,
                    warning: 25,
                    css: "counter",
                    counterElement: "span",
                    cssWarning: "warning",
                    cssExceeded: "exceeded",
                    counterText: ""
                },
                t = e.extend(o, t);
            return this.each(function() {
                e(this).after("<" + t.counterElement + ' class="' + t.css + '">' + t.counterText + "</" + t.counterElement + ">"),
                    n(this),
                    e(this).keyup(function() {
                        n(this)
                    }),
                    e(this).change(function() {
                        n(this)
                    }),
                    e(this).blur(function() {
                        n(this)
                    })
            }),
                t
        }
 }) (jQuery);


$(".textarea-style").initTextareaStyle(),
    $(".textarea-style").on("propertychange", MaxMe);