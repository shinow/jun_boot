/**
 * 首页方法封装处理
 * Copyright (c) 2019 ruoyi
 */
layer.config({
    extend: 'moon/style.css',
    skin: 'layer-ext-moon'
});

$(function() {
    // MetsiMenu
    $('#side-menu').metisMenu();

    //固定菜单栏
    $(function() {
        $('.sidebar-collapse').slimScroll({
            height: '100%',
            railOpacity: 0.9,
            alwaysVisible: false
        });
    });

    // 菜单切换
    $('.navbar-minimalize').click(function() {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    $('#side-menu>li').click(function() {
        if ($('body').hasClass('mini-navbar')) {
            NavToggle();
        }
    });
    $('#side-menu>li li a').click(function() {
        if ($(window).width() < 769) {
            NavToggle();
        }
    });

    $('.nav-close').click(NavToggle);

    //ios浏览器兼容性处理
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        $('#content-main').css('overflow-y', 'auto');
    }

});

function localStorageSupport() {
    return "localStorage" in window && null !== window.localStorage
}

/*主题框框相关*/
$(document).ready(function () {
    function e() {
        var e = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", e + "px")
    }

    $("#side-menu").metisMenu(), $(".right-sidebar-toggle").click(function () {
        $("#right-sidebar").toggleClass("sidebar-open")
    }), $(".sidebar-container").slimScroll({
        height: "100%",
        railOpacity: .4,
        wheelStep: 10
    }), $(".open-small-chat").click(function () {
        $(this).children().toggleClass("fa-comments").toggleClass("fa-remove"), $(".small-chat-box").toggleClass("active")
    }), $(".small-chat-box .content").slimScroll({
        height: "234px",
        railOpacity: .4
    }), $(".check-link").click(function () {
        var e = $(this).find("i"), a = $(this).next("span");
        return e.toggleClass("fa-check-square").toggleClass("fa-square-o"), a.toggleClass("todo-completed"), !1
    }), $(function () {
        $(".sidebar-collapse").slimScroll({height: "100%", railOpacity: .9, alwaysVisible: !1})
    }), e(), $(window).bind("load resize click scroll", function () {
        $("body").hasClass("body-small") || e()
    }), $(window).scroll(function () {
        $(window).scrollTop() > 0 && !$("body").hasClass("fixed-nav") ? $("#right-sidebar").addClass("sidebar-top") : $("#right-sidebar").removeClass("sidebar-top")
    }), $(".full-height-scroll").slimScroll({height: "100%"}), $("#side-menu>li").click(function () {
        $("body").hasClass("mini-navbar") && NavToggle()
    }), $("#side-menu>li li a").click(function () {
        $(window).width() < 769 && NavToggle()
    }), $(".nav-close").click(NavToggle), /(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent) && $("#content-main").css("overflow-y", "auto")
})
/**
 * 移除 $e 所有 pref 开头的class
 * @param $e jq对象
 * @param pref 前缀字符串
 */
function removeClassPref ($e,pref){
    var allClass = $e.attr("class").toString().trim();
    var classs = allClass.split("\\s+");
    for (var i = 0; i < classs.length ; i++) {
        if (classs[i].indexOf(pref)==0){
            $e.removeClass(classs[i]);
        }
    }
}
$(function () {
    if ($("#fixednavbar").click(function () {
        $("#fixednavbar").is(":checked") ? ($(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top"), $("body").removeClass("boxed-layout"), $("body").addClass("fixed-nav"), $("#boxedlayout").prop("checked", !1), localStorageSupport && localStorage.setItem("boxedlayout", "off"), localStorageSupport && localStorage.setItem("fixednavbar", "on")) : ($(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top"), $("body").removeClass("fixed-nav"), localStorageSupport && localStorage.setItem("fixednavbar", "off"))
    }),$("#collapsemenu").click(function () {
        $("#collapsemenu").is(":checked") ? ($("body").addClass("mini-navbar"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("collapse_menu", "on")) : ($("body").removeClass("mini-navbar"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("collapse_menu", "off"))
    }), $("#boxedlayout").click(function () {
        $("#boxedlayout").is(":checked") ? ($("body").addClass("boxed-layout"), $("#fixednavbar").prop("checked", !1), $(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top"), $("body").removeClass("fixed-nav"), localStorageSupport && localStorage.setItem("fixednavbar", "off"), localStorageSupport && localStorage.setItem("boxedlayout", "on")) : ($("body").removeClass("boxed-layout"), localStorageSupport && localStorage.setItem("boxedlayout", "off"))
    }), $(".s-skin-0").click(function () { //默认
        //蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow
        /*return $.common.removeClassPref($("body"),"skin-"),$("body").addClass("skin-default"),
        localStorageSupport && localStorage.setItem("skin_name", "skin-default"),!1*/
    }), $(".s-skin-1").click(function () { //蓝
        return $.common.removeClassPref($("body"),"skin-"), $("body").addClass("skin-blue"),
        localStorageSupport && localStorage.setItem("skin_name", "skin-blue"),!1
    }), $(".s-skin-2").click(function () { //绿
        return $.common.removeClassPref($("body"),"skin-"), $("body").addClass("skin-green"),
        localStorageSupport && localStorage.setItem("skin_name", "skin-green"),!1
    }), $(".s-skin-3").click(function () { //紫
        return $.common.removeClassPref($("body"),"skin-"), $("body").addClass("skin-purple"),
        localStorageSupport && localStorage.setItem("skin_name", "skin-purple"),!1
    }), $(".s-skin-4").click(function () { //红
        return $.common.removeClassPref($("body"),"skin-"), $("body").addClass("skin-red"),
        localStorageSupport && localStorage.setItem("skin_name", "skin-red"),!1
    }), $(".s-skin-5").click(function () { //黄
        return $.common.removeClassPref($("body"),"skin-"), $("body").addClass("skin-yellow"),
        localStorageSupport && localStorage.setItem("skin_name", "skin-yellow"),!1
    }), localStorageSupport){
        var e = localStorage.getItem("collapse_menu"), a = localStorage.getItem("fixednavbar"),
            o = localStorage.getItem("boxedlayout");
        "on" == e && $("#collapsemenu").prop("checked", "checked"), "on" == a && $("#fixednavbar").prop("checked", "checked"), "on" == o && $("#boxedlayout").prop("checked", "checked")

    }
    if (localStorageSupport) {//第一次加载 初始化 主题设置
        var e = localStorage.getItem("collapse_menu"), a = localStorage.getItem("fixednavbar"),
            o = localStorage.getItem("boxedlayout"), l = $("body"),skinName = localStorage.getItem("skin_name");
        "on" == e && (l.hasClass("body-small") || l.addClass("mini-navbar")),
        "on" == a && ($(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top"),
            l.addClass("fixed-nav")),
        "on" == o && l.addClass("boxed-layout"),
        skinName != null &&$.common.removeClassPref(l,"skin-")&&l.addClass(skinName);
    }
    SmoothlyMenu();
});


$(window).bind("load resize",
function() {
    if ($(this).width() < 769) {
        $('body').addClass('mini-navbar');
        $('.navbar-static-side').fadeIn();
        $(".sidebar-collapse .logo").addClass("hide");
    }
});

function NavToggle() {
    $('.navbar-minimalize').trigger('click');
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar')) {
        $('.menu-search').show();
        $('#side-menu').hide();
        $(".sidebar-collapse .logo").removeClass("hide");
        setTimeout(function() {
            $('#side-menu').fadeIn(500);
        },
        100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('.menu-search').hide();
        $('#side-menu').hide();
        $(".sidebar-collapse .logo").addClass("hide");
        setTimeout(function() {
            $('#side-menu').fadeIn(500);
        },
        300);
    } else {
        $('.menu-search').show();
        $('#side-menu').removeAttr('style');
    }
}

/**
 * iframe处理
 */
$(function() {
    //计算元素集合的总宽度
    function calSumWidth(elements) {
        var width = 0;
        $(elements).each(function() {
            width += $(this).outerWidth(true);
        });
        return width;
    }

    //滚动到指定选项卡
    function scrollToTab(element) {
        var marginLeftVal = calSumWidth($(element).prevAll()),
        marginRightVal = calSumWidth($(element).nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        },
        "fast");
    }

    //查看左侧隐藏的选项卡
    function scrollTabLeft() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')) + 50);
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).prev();
                }
                scrollVal = calSumWidth($(tabElement).prevAll());
            }
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        },
        "fast");
    }

    //查看右侧隐藏的选项卡
    function scrollTabRight() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
            if (scrollVal > 0) {
                $('.page-tabs-content').animate({
                    marginLeft: 0 - scrollVal + 'px'
                },
                "fast");
            }
        }
    }

    //通过遍历给菜单项加上data-index属性
    $(".menuItem").each(function(index) {
        if (!$(this).attr('data-index')) {
            $(this).attr('data-index', index);
        }
    });

    function menuItem() {
        // 获取标识数据
        var dataUrl = $(this).attr('href'),
        dataIndex = $(this).data('index'),
        menuName = $.trim($(this).text()),
        flag = true;
        if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;

        // 选项卡菜单已存在
        $('.menuTab').each(function() {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.menuTab').removeClass('active');
                    scrollToTab(this);
                    // 显示tab对应的内容区
                    $('.mainContent .RuoYi_iframe').each(function() {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.RuoYi_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });
        // 选项卡菜单不存在
        if (flag) {
            var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $('.menuTab').removeClass('active');

            // 添加选项卡对应的iframe
            var str1 = '<iframe class="RuoYi_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            $('.mainContent').find('iframe.RuoYi_iframe').hide().parents('.mainContent').append(str1);
            
            $.modal.loading("数据加载中，请稍后...");
            
            $('.mainContent iframe:visible').load(function () {
            	$.modal.closeLoading();
            });
            
            // 添加选项卡
            $('.menuTabs .page-tabs-content').append(str);
            scrollToTab($('.menuTab.active'));
        }
        return false;
    }

    $('.menuItem').on('click', menuItem);

    // 关闭选项卡菜单
    function closeTab() {
        var closeTabId = $(this).parents('.menuTab').data('id');
        var currentWidth = $(this).parents('.menuTab').width();

        // 当前元素处于活动状态
        if ($(this).parents('.menuTab').hasClass('active')) {

            // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
            if ($(this).parents('.menuTab').next('.menuTab').size()) {

                var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
                $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

                $('.mainContent .RuoYi_iframe').each(function() {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.RuoYi_iframe').hide();
                        return false;
                    }
                });

                var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                if (marginLeftVal < 0) {
                    $('.page-tabs-content').animate({
                        marginLeft: (marginLeftVal + currentWidth) + 'px'
                    },
                    "fast");
                }

                //  移除当前选项卡
                $(this).parents('.menuTab').remove();

                // 移除tab对应的内容区
                $('.mainContent .RuoYi_iframe').each(function() {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }

            // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
            if ($(this).parents('.menuTab').prev('.menuTab').size()) {
                var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
                $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
                $('.mainContent .RuoYi_iframe').each(function() {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.RuoYi_iframe').hide();
                        return false;
                    }
                });

                //  移除当前选项卡
                $(this).parents('.menuTab').remove();

                // 移除tab对应的内容区
                $('.mainContent .RuoYi_iframe').each(function() {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }
        }
        // 当前元素不处于活动状态
        else {
            //  移除当前选项卡
            $(this).parents('.menuTab').remove();

            // 移除相应tab对应的内容区
            $('.mainContent .RuoYi_iframe').each(function() {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
            scrollToTab($('.menuTab.active'));
        }
        return false;
    }

    $('.menuTabs').on('click', '.menuTab i', closeTab);

    //关闭其他选项卡
    function closeOtherTabs() {
        $('.page-tabs-content').children("[data-id]").not(":first").not(".active").each(function() {
            $('.RuoYi_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').css("margin-left", "0");
    }
    $('.tabCloseOther').on('click', closeOtherTabs);

    //滚动到已激活的选项卡
    function showActiveTab() {
        scrollToTab($('.menuTab.active'));
    }
    $('.tabShowActive').on('click', showActiveTab);

    // 点击选项卡菜单
    function activeTab() {
        if (!$(this).hasClass('active')) {
            var currentId = $(this).data('id');
            // 显示tab对应的内容区
            $('.mainContent .RuoYi_iframe').each(function() {
                if ($(this).data('id') == currentId) {
                    $(this).show().siblings('.RuoYi_iframe').hide();
                    return false;
                }
            });
            $(this).addClass('active').siblings('.menuTab').removeClass('active');
            scrollToTab(this);
        }
    }

    // 点击选项卡菜单
    $('.menuTabs').on('click', '.menuTab', activeTab);

    //刷新iframe
    function refreshTab() {
    	var currentId = $('.page-tabs-content').find('.active').attr('data-id');
    	var target = $('.RuoYi_iframe[data-id="' + currentId + '"]');
        var url = target.attr('src');
        target.attr('src', url).ready();
    }
    
    // 全屏显示
    $('#fullScreen').on('click', function () {
    	$('#wrapper').fullScreen();
    });
    
    // 刷新按钮
    $('.tabReload').on('click', refreshTab);

    // 双击选项卡全屏显示
    $('.menuTabs').on('dblclick', '.menuTab', refreshTab);

    // 左移按扭
    $('.tabLeft').on('click', scrollTabLeft);

    // 右移按扭
    $('.tabRight').on('click', scrollTabRight);
    
    // 关闭当前
    $('.tabCloseCurrent').on('click', function () {
        $('.page-tabs-content').find('.active i').trigger("click");
    });

    // 关闭全部
    $('.tabCloseAll').on('click', function() {
        $('.page-tabs-content').children("[data-id]").not(":first").each(function() {
            $('.RuoYi_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').children("[data-id]:first").each(function() {
            $('.RuoYi_iframe[data-id="' + $(this).data('id') + '"]').show();
            $(this).addClass("active");
        });
        $('.page-tabs-content').css("margin-left", "0");
    });
    
    // tab全屏显示
    $('.tabMaxCurrent').on('click', function () {
        activeTabMax();
    });
    
    // 关闭全屏
    $('#ax_close_max').click(function(){
    	$('#content-main').toggleClass('max');
    	$('#ax_close_max').hide();
    })

    // 全屏显示
    function activeTabMax() {
        $('#content-main').toggleClass('max');
        $('#ax_close_max').show();
    }
    
    $(window).keydown(function(event) {
        if (event.keyCode == 27) {
            $('#content-main').removeClass('max');
            $('#ax_close_max').hide();
        }
    });
    //快速定位菜单
    function filtMenu(value) {
        value=value.trim();
        $(".menu-level-1").each(function (index, e) {
            var $m1 = $(e);//一级菜单对象
            if ($m1.text().trim()==value){ $m1.click();return;}
            $m1.next(".nav-second-level").find(".menu-level-2").each(function (index2,e2) {
                var $m2 = $(e2);//二级菜单对象
                if ($m2.text().trim()==value){ $m1.click();$m2.click();return;}
                $m2.next(".nav-third-level").find(".menu-level-3").each(function (index3,e3) {
                    var $m3 = $(e3);//二级菜单对象
                    if ($m3.text().trim()==value){ $m1.click();$m2.click();$m3.click(); return;}
                })
            })
        });
    }
    $(".menu-search-input").keydown(function (event) {
        if (event.keyCode == 13) {
            filtMenu(this.value);
        }else if (event.keyCode == 27) {
            $("#menuALL").click();
        }
    });
    $(".menu-search-input").change(function () {
            filtMenu(this.value);
    });
    $("#menuALL").click(function () {
        $(".menu-search-input").val("");
        $(".menu-search-input").focus();
    });
    //初始化 菜单定位选项
    $(".menu-level-1").each(function (index, e) {
        var $m1 = $(e);//一级菜单对象
        $("#allMenuData").append("<option value=\""+$m1.text().trim()+"\">{ 一 }级菜单</option>")
        $m1.next(".nav-second-level").find(".menu-level-2").each(function (index2,e2) {
            var $m2 = $(e2);//二级菜单对象
            $("#allMenuData").append("<option value=\""+$m2.text().trim()+"\">[ 二 ]级菜单</option>")
            $m2.next(".nav-third-level").find(".menu-level-3").each(function (index3,e3) {
                var $m3 = $(e3);//二级菜单对象
                $("#allMenuData").append("<option value=\""+$m3.text().trim()+"\">( 三 )级菜单</option>")

            })
        })
    });
});

/*点击其他地方隐藏主题设置div*/
document.onclick = function (e) {
    //如果是开启状态
    if ($("#right-sidebar").hasClass("sidebar-open")){
        var elements = e.path;
        var isTop = false;//点击的地方是否是导航栏
        for (var i = 0; i < elements.length; i++) {
            console.log(elements[i].className.indexOf("navbar-static-top"));
            if (elements[i]!=undefined&&elements[i].className.indexOf("navbar-static-top")>-1) {
                isTop = true;
                break;
            }
        }
        //点击的其他地方,隐藏主题设置div
        if (!isTop) $("#right-sidebar").removeClass("sidebar-open");
    }
}