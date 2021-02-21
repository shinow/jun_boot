

/***********
 * data-code ,
 * data-path ，写到js-tab-city的元素上，点击当前方便获取；
 * 放置当前选中城市的dom“.cur-city-select”，会在元素class为".city-plug-parent"的里面
 *
 */
(function ($) {


    $.city = function (options) {
        var defaults = {
            Clickcb: function () {
                $.noop();
            }
        };
        var sets = $.extend(defaults, options || {});

        var allCountry = "",
            cinProvince = "",
            provinceid = "",
            citySelected,
            countryArray;


        var countryCityPlug = {
            render: function () {
                var str = "";
                var childrenStr = "";
                var cityid,
                    countryPath,
                    flag = true,
                    panelLayer,
                    hotCity,
                    countryLevel1,
                    isexitId,
                    CitySelected,
                    provinceTop,
                    clickNum = 0;


                $('.js-tab-city').on('click', function () {
                    var _this = $(this);
                    CitySelected = panelLayer = null;
                    isexitId = false;
                    cityTop = provinceTop = str = childrenStr = "";

                    if(_this.parents(".city-plug-parent").length){
                        CitySelected = _this.parents(".city-plug-parent").find('.cur-city-select');
                        panelLayer = _this.parents(".city-plug-parent").find('.city-panel-layer');
                    }else{
                        CitySelected = _this.find('.cur-city-select');
                        panelLayer = _this.find('.city-panel-layer');
                    }
                    // if(_this.attr('data-code')){
                    cityid = CitySelected.attr('data-code');
                    countryPath = CitySelected.attr('data-path');
                    // }else{
                    //    cityid = $('#city-city').attr('data-code');
                    //    countryPath = $('#city-city').attr('data-path');
                    // }
                    if(countryPath){
                        if(countryPath.indexOf(',7,') > -1 && (panelLayer.find("input[name=city-radio][value=cn]").prop('checked') || panelLayer.find("input[name=city-radio][value=cn]").attr('checked'))){
                            citySelected = "cn";
                        }else if(panelLayer.find("input[name=city-radio][value=en]").prop('checked')){
                            citySelected = "en";
                        }
                    }else{
                        // 不是国家，例如不限城市，等等。。。默认显示中国y
                        citySelected = "cn";
                    }


                    countryArray = countryPath.split(',');

                    if (panelLayer.is(":visible")) {
                        panelLayer.hide()
                    } else {
                        // 多个城市，先隐藏所有，只显示当前点击的城市layer
                        $('.city-panel-layer').hide();
                        panelLayer.show()
                    }
                    if(panelLayer.find('.city-content').length){
                        // 如果城市dom有，不需在请求；
                        provinceTop = panelLayer.find('.city-province-list a[class=active]').position().top;
                        if(panelLayer.find('.city-city-list a[class=active]').length){
                            cityTop = panelLayer.find('.city-city-list a[class=active]').position().top;
                            $(".city-scroll-div").mCustomScrollbar('scrollTo',cityTop);
                        }
                        $(".province-scroll-div").mCustomScrollbar('scrollTo',provinceTop);

                        return false;
                    }
                    $('.js-city-covers').html(LoaddingDom);
                    if(flag){
                        flag = false;
                        $.ajax({
                            type: "GET",
                            url: proMainZDomain + "/city/getAll",
                            xhrFields: {
                                withCredentials: true
                            },
                            crossDomain: true,
                            headers: {
                                "X-Requested-With": "XMLHttpRequest"
                            },
                            dataType: "json",
                            success: function (data) {
                                flag = true;
                                allCountry = data.data.all.allCountry;
                                hotCountry = data.data.all.hotCountry;
                                hotCity = "";
                                cinProvince = data.data.china.byProvince;
                                countryLevel1 = "";

                                window.renderCity = function() {

                                    if (citySelected == "cn") {
                                        var ale = countryArray.length - 3;
                                        provinceid =  countryArray[ale];
                                    } else {
                                        // 获取国家id
                                        panelLayer.find("input[name=city-radio]").attr('checked',false).parent().removeClass('radio-0').addClass('radio-1')
                                        panelLayer.find("input[name=city-radio]:eq(1)").parent().removeClass('radio-1').addClass('radio-0');
                                        panelLayer.find("input[name=city-radio]:eq(1)").attr('checked',true)
                                        provinceid = countryArray[2];
                                    }
                                    if (citySelected == "cn") {
                                        hotCity = data.data.china.hotCity;
                                    } else {
                                        hotCity = data.data.all.hotCountry;
                                    }

                                    str += "<div class=\"border-top city-content js-china\"><section class=\"left city-province-list\">" +
                                        "<div class=\"province-scroll-div\">" +
                                        "<dl class=\"\">" +
                                        "<dd class=\"\"><a data-code=\"0\" class=\"active\">" + messagesWeb.popular_cities + "</a></dd>";
                                    if (citySelected == "cn") {
                                        // 中国省级渲染

                                        $.each(cinProvince, function (index,cinProvinceinit) {
                                            str += "<dd><a data-code=\"" + cinProvinceinit.id + "\">" + languageName(cinProvinceinit) + "</a></dd>";
                                        })
                                    } else {
                                        // 全国州、国家渲染
                                        $.each(allCountry, function (index, allCountryItem) {
                                            str += "<dt data-code=\"" + allCountryItem.id + "\">" + languageName(allCountryItem) + "</dt>";
                                            if (allCountryItem.children.length) {
                                                // 国家级countryChildren；
                                                $.each(allCountryItem.children, function (index, countryChildren) {
                                                    str += "<dd ><a data-code=\"" + countryChildren.id + "\">" + languageName(countryChildren) + "</a></dd>";
                                                })
                                            }
                                        })
                                    }

                                    str += "</dl>" +
                                        "</div>" +
                                        "</section>" +
                                        "<section class=\"left border-left city-city-list\">" +
                                        " <div class=\"city-scroll-div\">";
                                    // 判断是否是北京

                                    if (citySelected == "cn") {
                                        isexitId = false;
                                        $.each(cinProvince, function (index, cinProvinceItem) {
                                            if (cinProvinceItem.children) {
                                                $.each(cinProvinceItem.children, function (index, cinCityItem) {
                                                    if (cinCityItem.pid == provinceid) {
                                                        str += "<div class=\"city-children3\"><a data-code=\"" + cinCityItem.id + "\">" + languageName(cinCityItem) + "</a></div>";
                                                        isexitId = true;
                                                    }
                                                })
                                            }
                                        })
                                        if(!isexitId){
                                            $.each(hotCity, function (index, hotCityItem) {
                                                str += "<div class=\"city-children3\"><a data-code=\"" + hotCityItem.id + "\">" + languageName(hotCityItem) + "</a></div>";
                                            })
                                            provinceid = 0;
                                        }
                                    } else {
                                        // 全国
                                        isexitId = false;
                                        $.each(eval(allCountry), function (index, allCountryItem1) {
                                            if (allCountryItem1.children.length) {
                                                // allCountryItem1.children  所有国家级列表
                                                $.each(eval(allCountryItem1.children), function (index, countryChildren2) {
                                                    // 判断是否有省
                                                    if (eval(countryChildren2).children) {

                                                        $.each(eval(countryChildren2.children), function (index, countryChildren3) {
                                                            // get与点击国家的id相同的列表，渲染此列表；
                                                            if (eval(countryChildren3).pid == provinceid) {

                                                                // 获取最后一级省级下的市 且存在的
                                                                if (eval(countryChildren3).children) {
                                                                    str += "<div class=\"city-children3\" data-code=\"" + countryChildren3.id + "\"><span class=\"c-bbb\">" + languageName(countryChildren3) + "</span>";
                                                                    str += "<ul>";
                                                                    $.each(eval(countryChildren3.children), function (index, countryChildren4) {
                                                                        str += "<li class=\"\"><a data-code=\"" + countryChildren4.id + "\">" + languageName(countryChildren4) + "</a></li>";
                                                                    })
                                                                    str += "</ul>";
                                                                } else {
                                                                    str += "<div class=\"city-children3\"><a data-code=\"" + countryChildren3.id + "\">" + languageName(countryChildren3) + "</a>";
                                                                }
                                                                str += "</div>";
                                                                isexitId = true;
                                                            }
                                                        })
                                                    }
                                                })
                                            }
                                        });
                                        if(!isexitId){
                                            $.each(eval(hotCity), function (index, hotCityItem) {
                                                str += "<div class=\"city-children3\"><a data-code=\"" + hotCityItem.id + "\">" + languageName(hotCityItem) + "</a></div>"
                                            })
                                            provinceid = 0;
                                        }
                                    }


                                    str += "</div></section></div>";
                                    panelLayer.find('.js-city-covers').html(str);

                                    panelLayer.find('.city-province-list a').removeClass('active');
                                    panelLayer.find('.city-province-list a[data-code=' + parseInt(provinceid) + ']').addClass('active');

                                    selectedCurCity()
                                    var curcitydom = panelLayer.find('.city-city-list a[data-code=' + cityid + ']');
                                    var curprovincedom = panelLayer.find('.city-province-list a[data-code=' + parseInt(provinceid) + ']');
                                    if(curcitydom.length){
                                        cityTop = panelLayer.find('.city-city-list a[data-code=' + cityid + ']').position().top;
                                    }
                                    if(curprovincedom.length){
                                        provinceTop = panelLayer.find('.city-province-list a[data-code=' + parseInt(provinceid) + ']').position().top;
                                    }

                                    $('.province-scroll-div,.city-scroll-div').mCustomScrollbar({
                                        theme: "dark", //主题颜色
                                        autoHideScrollbar: true, //是否自动隐藏滚动条
                                        scrollInertia: 100, //滚动延迟
                                        horizontalScroll: false, //水平滚动条
                                        mouseWheel: {
                                            preventDefault: true
                                        },
                                        updateOnContentResize: true,
                                        callbacks: {
                                            onInit: function () {
                                                // 很神奇的问题，一次滚动到不了底部，暂时再延迟滚动一次
                                                // $(this).mCustomScrollbar('scrollTo',provinceTop);
                                            },
                                            onScrollStart: function () {
                                                $(this).find('.mCustomScrollBox .mCSB_scrollTools').css({ "opacity": '1', '-webkit-animation': 'none' })
                                            },
                                            whileScrolling: function () {
                                                $(this).find('.mCustomScrollBox .mCSB_scrollTools').css({ "opacity": '1', '-webkit-animation': 'none' })
                                            },
                                            onScroll: function () {
                                                //滚动完成后触发事件
                                                $(this).find('.mCustomScrollBox .mCSB_scrollTools').removeAttr('style')
                                            },

                                        },
                                    });
                                    $(".province-scroll-div").mCustomScrollbar('scrollTo',provinceTop);
                                    $(".city-scroll-div").mCustomScrollbar('scrollTo',cityTop);
                                    // $('.city-scroll-div').mCustomScrollbar({
                                    //     theme: "dark", //主题颜色
                                    //     autoHideScrollbar: true, //是否自动隐藏滚动条
                                    //     scrollInertia: 100, //滚动延迟
                                    //     horizontalScroll: false, //水平滚动条
                                    //     mouseWheel: {
                                    //         preventDefault: true
                                    //     },
                                    //     updateOnContentResize: true,
                                    //     callbacks: {
                                    //         onScrollStart: function () {
                                    //             $('.city-scroll-div .mCustomScrollBox .mCSB_scrollTools').css({ "opacity": '1', '-webkit-animation': 'none' })
                                    //         },
                                    //         whileScrolling: function () {
                                    //             $('.city-scroll-div .mCustomScrollBox .mCSB_scrollTools').css({ "opacity": '1', '-webkit-animation': 'none' })
                                    //         },
                                    //         onScroll: function () {
                                    //             //滚动完成后触发事件
                                    //             $('.city-scroll-div .mCustomScrollBox .mCSB_scrollTools').removeAttr('style')
                                    //         },

                                    //     },
                                    // });

                                    bind()
                                }

                                renderCity()


                                function bind() {
                                    $('.city-province-list dl a').on('click', function () {
                                        childrenStr = "";
                                        var _this = $(this);
                                        var curId = $(this).attr('data-code');
                                        $('.city-province-list dl a').removeClass('active');
                                        _this.addClass('active')
                                        if (curId == 0) {
                                            $.each(eval(hotCity), function (index, hotCityItem) {
                                                childrenStr += "<div class=\"city-children3\"><a data-code=\"" + hotCityItem.id + "\">" + languageName(hotCityItem) + "</a></div>"
                                            })
                                        } else {
                                            if (citySelected == "cn") {
                                                $.each(cinProvince, function (index, cinProvinceItem) {
                                                    if (cinProvinceItem.children) {
                                                        $.each(cinProvinceItem.children, function (index, cinCityItem) {
                                                            if (cinCityItem.pid == curId) {
                                                                childrenStr += "<div class=\"city-children3\"><a data-code=\"" + cinCityItem.id + "\">" + languageName(cinCityItem) + "</a></div>";
                                                            }
                                                        })
                                                    }
                                                })
                                            } else {
                                                // 全国
                                                $.each(eval(allCountry), function (index, allCountryItem1) {
                                                    if (allCountryItem1.children.length) {
                                                        // allCountryItem1.children  所有国家级列表
                                                        $.each(eval(allCountryItem1.children), function (index, countryChildren2) {
                                                            // 判断是否有省
                                                            if (eval(countryChildren2).children) {

                                                                $.each(eval(countryChildren2.children), function (index, countryChildren3) {
                                                                    // get与点击国家的id相同的列表，渲染此列表；
                                                                    if (eval(countryChildren3).pid == curId) {

                                                                        // 获取最后一级省级下的市 且存在的
                                                                        if (eval(countryChildren3).children) {
                                                                            childrenStr += "<div class=\"city-children3\" data-code=\"" + countryChildren3.id + "\"><span class=\"c-bbb\">" + languageName(countryChildren3) + "</span>";
                                                                            childrenStr += "<ul>";
                                                                            $.each(eval(countryChildren3.children), function (index, countryChildren4) {
                                                                                childrenStr += "<li class=\"\"><a data-code=\"" + countryChildren4.id + "\">" + languageName(countryChildren4) + "</a></li>";
                                                                            })
                                                                            childrenStr += "</ul>";
                                                                        } else {
                                                                            childrenStr += "<div class=\"city-children3\"><a data-code=\"" + countryChildren3.id + "\">" + languageName(countryChildren3) + "</a>";

                                                                        }
                                                                        childrenStr += "</div>";

                                                                    }
                                                                })

                                                            }else{
                                                                if(countryChildren2.id == curId){
                                                                    childrenStr += "<div class=\"city-children3\"><a data-code=\"" + countryChildren2.id + "\">" + languageName(countryChildren2) + "</a></div>"
                                                                }

                                                            }
                                                        })
                                                    }
                                                })
                                            }
                                        }

                                        if ($('.city-scroll-div .mCSB_container').length) {
                                            $('.city-scroll-div .mCSB_container').html('')
                                            $('.city-scroll-div .mCSB_container').append(childrenStr)
                                        } else {
                                            $('.city-scroll-div').html(childrenStr)
                                        }

                                        $('.city-scroll-div').mCustomScrollbar({
                                            theme: "dark", //主题颜色
                                            autoHideScrollbar: true, //是否自动隐藏滚动条
                                            scrollInertia: 100, //滚动延迟
                                            horizontalScroll: false, //水平滚动条
                                            mouseWheel: {
                                                preventDefault: true
                                            },
                                            updateOnContentResize: true,
                                            callbacks: {
                                                onScrollStart: function () {
                                                    $('.city-scroll-div .mCustomScrollBox .mCSB_scrollTools').css({ "opacity": '1', '-webkit-animation': 'none' })
                                                },
                                                whileScrolling: function () {
                                                    $('.city-scroll-div .mCustomScrollBox .mCSB_scrollTools').css({ "opacity": '1', '-webkit-animation': 'none' })
                                                },
                                                onScroll: function () {
                                                    //滚动完成后触发事件
                                                    $('.city-scroll-div .mCustomScrollBox .mCSB_scrollTools').removeAttr('style')
                                                },

                                            },
                                        });
                                        if ($('.city-scroll-div').hasClass('mCustomScrollbar')) {
                                            $('.city-scroll-div').mCustomScrollbar("update");
                                        }
                                        // 选中当前所在的城市
                                        selectedCurCity()
                                        lastLevelbind()
                                    })

                                    function lastLevelbind(){
                                        // 最下一级绑定click选中事件
                                        $('.city-city-list a').on('click', function () {
                                            var city_this = $(this);
                                            $('.city-city-list a').removeClass('active');
                                            if(_this.attr('data-code')){
                                                CitySelected.html(city_this.html()).attr('data-code', city_this.attr('data-code'));
                                            }else{
                                                CitySelected.html(city_this.html()).attr('data-code', city_this.attr('data-code'));
                                            }
                                            $('.city-panel-layer').hide();
                                            city_this.addClass('active');
                                            return sets.Clickcb(city_this)
                                        })
                                    }
                                    lastLevelbind()

                                    $(document).on('click', function (event) {
                                        $('.city-panel-layer').hide();
                                    })
                                    $('.city-panel-layer,.js-tab-city').on('click', function (e) {
                                        e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = true
                                    })
                                }
                            }
                        })
                    }

                })

                function selectedCurCity(){
                    panelLayer.find('.city-city-list a').removeClass('active');
                    panelLayer.find('.city-city-list a[data-code=' + cityid + ']').addClass('active');
                }

                // 点击单选切换国家
                $('.js-city-radio label').on('click', function () {
                    citySelected = panelLayer.find("input[name=city-radio]:checked").attr('value');
                    panelLayer.find("input[name=city-radio]").parent().removeClass('radio-0').addClass('radio-1');
                    panelLayer.find("input[name=city-radio]:checked").parent().removeClass('radio-1').addClass('radio-0');

                    if($(this).hasClass('radio-0')) {
                        str = "";
                        panelLayer.find('.js-city-covers').html('');
                        renderCity()
                    }

                })
            }
        }
        countryCityPlug.render()
    }

})(jQuery);
