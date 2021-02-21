$('.radio label').on('click', function() {
    $(this).parents('.radio').find("input[name=radio]").parent().removeClass().addClass('radio-1');
    $(this).parents('.radio').find("input[name=radio]:checked").parent().removeClass().addClass('radio-0');
})
$('.radio label').on('click', function() {
    $(this).parents('.radio').find("input[name=children]").parent().removeClass().addClass('radio-1');
    $(this).parents('.radio').find("input[name=children]:checked").parent().removeClass().addClass('radio-0');
})
$('.more').on('click', function() {
    $('.other-personal-link').show();
    $(this).hide()
})


$('.pop-domain-name .pop-cancel,.pop-domain-name .pop-close').on('click', function() {
    $('input[name="domain-name"]').val('')
    $('.domain-name-error').hide();
    hideGlobalMaskLayer();
    $('.pop-domain-name').hide();
})

zCharCount_withExceedCount($("#signature"), {
    allowed: 40,
    exceed: 10
});
zCharCount_withExceedCount($("#workmark"), {
    allowed: 16,
    exceed: 10
});


zCharCount($("#description"), {
    allowed: 2000
});

// 贴标签
function Labelling(obj) {
    this.labelling = obj.labelling;
    this.workmark = obj.workmark;
    this.workcon = obj.workcon;
    this.disbled = obj.disbled;
    this.close = this.workcon.find('span>i');
    this.textnum = this.workmark.siblings('.counter').html();
    this.tagNum;
    this.disabledBtnFn();
    this.clickAdd();
    this.tagClose();
}
Labelling.prototype = {
    fn1: function() {
        var me = this;
        var numberOfTag = this.workcon.find('span').length;
        this.tagNum = 5 - numberOfTag;
        this.labelling.val("贴标签" + '(' + this.tagNum + ')')
    },
    AddTag:function(inputc, workc){
        var me = this;
        inputc = html_encode(inputc)
        if (inputc != "") {
            var appendTag = '<span class="mark" title="' + inputc + '">' + inputc + ' <i></i></span>'
            workc.append(appendTag)
        }
        me.fn1()
        if (me.tagNum == 0) {
            me.labelling.hide()
            me.disbled.show()
            return;
        }
    },
    disabledBtnFn: function() {
        var me = this;
        this.workmark.on('keyup', function() {
            count = parseInt($(this).parent().find('.count').html());
            if(count < 0){
                me.labelling.attr('disabled', 'disabled').addClass('disabled-color');
            }else if ($(this).val() != "") {
                me.labelling.removeAttr('disabled').removeClass('disabled-color');
            } else {
                me.labelling.attr('disabled', 'disabled').addClass('disabled-color');
            }
        })
    },
    clickAdd: function() {
        var me = this,
            flag = true,
            tags = [];
        this.fn1()
        this.labelling.on('click', function() {
            // e.preventDefault()
            count = parseInt($(this).parent().find('.count').html());

            var inputTag;
            if(me.workmark.val().trim() == ""){
                return;
            }else{
                inputTag = $('#workmark').val();

            }
            me.workmark.val('')
            me.labelling.attr('disabled', 'disabled').addClass('disabled-color');
            if (!validateTag(inputTag)) {
                return;
            }
            if (count < 0) {
                pageToastFail("字符超出限制");
            } else {
                if (validateTag(inputTag)) {
                    if (me.tagNum == 0) {
                        return;
                    }
                    submitTagData(me, inputTag);
                }
            }
        })
        $('#workmark').on('keydown', function(event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];

            if (e.keyCode == 13 || e.keyCode == 32) {
                e.preventDefault()
                count = parseInt($(this).parent().find('.count').html());
                var inputTag;
                if(me.workmark.val().trim() == ""){
                    return;
                }else{
                    inputTag = $('#workmark').val();
                }
                me.workmark.val('')
                me.labelling.attr('disabled', 'disabled').addClass('disabled-color');
                if (!validateTag(inputTag)) {
                    return;
                }
                if (me.tagNum == 0) {
                    return;
                }
                if (count < 0) {
                    pageToastFail("字符超出限制");
                } else {

                    if (validateTag(inputTag)) {
                        if (me.tagNum == 0) {
                            return;
                        }
                        submitTagData(me, inputTag);
                    }
                }
            }
        })

        function submitTagData(me, tag) {
            $.ajax({
                type: "post",
                url: ctx + "setting/addTag?tag=" + tag,
                xhrFields: {
                    withCredentials: true
                },
                success: function(data) {
                    data.code == 0 ? me.AddTag(tag, me.workcon) : pageToastFail(data.msg);
                },
                error:function(){
                    pageToastFail("服务异常，请稍后重试");
                }
            })
        }

        function validateTag(inputTag) {
            var flag = true
            for (var i = 0; i < $('.mark-con').find('span').length; i++) {
                if (inputTag.toLowerCase() != $($('.mark-con').find('span')[i]).attr('title').toLowerCase()) {
                    flag = true;
                    continue;
                } else {
                    flag = false;
                    pageToastFail("标签不能重复");
                    break;
                }
            }
            return flag
        }
    },
    tagClose: function() {
        var me = this;
        this.workcon.on('click', this.close, function(event) {
            var tag = $(event.target).parents('.mark').text();
            $(event.target).parents('.mark').remove();
            if (me.tagNum == 0) {
                me.labelling.show()
                me.disbled.hide()
            }
            me.fn1()
            $.ajax({
                type: "post",
                url: ctx + "setting/delTag?tag=" + tag,
                xhrFields: {
                    withCredentials: true
                },
                success: function(data) {}
            })
        })
    }
}
new Labelling({
    labelling: $('.mark-btn'),
    workmark: $('#workmark'),
    workcon: $('.mark-con'),
    disbled: $('.mark-disabled')
})

function AddTag(inputc, workc) {
    inputc = html_encode(inputc)
    if (inputc != "") {
        var appendTag = '<span class="mark" title="' + inputc + '">' + inputc + ' <i></i></span>'
        workc.append(appendTag)
    }
}
// 贴标签  end



var MatchingPull = {
    keyboardSection: -1,
    MatchingPullDown: function(event,input, itembox) {
        $('input[name="school-name"]').attr('data-id',"");
        if(input.val()==''||input.val().toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "")==''){
            return;
        }
        var e = event || window.event || arguments.callee.caller.arguments[0];
        var eq = [];
        itembox.show()
        $.ajax({
            type: "get",
            url: ctx + "plugs/highSchool?words=" + input.val(),
            //                async:false,
            xhrFields: {
                withCredentials: true
            },
            success: function(data) {
                /*if(data.code!=0){
                    pageToastFail(data.msg);
                    return;
                }*/
                if (data.code == 0) {
                    itembox.find('ul').show();
                    itembox.find('.empty').hide();
                    for (var i = 0; i < data.data.length; i++) {
                        eq.push('<li title="' + data.data[i].school + '" data-id="' + data.data[i].id + '">' + data.data[i].school + '</li>')
                    }
                    itembox.find('ul').html(eq.join(''));
                } else {
                    itembox.find('ul').hide();
                    itembox.find('.empty').show();
                }
            }
        })
        if (input.val() == "") {
            itembox.hide();
            input.val('').removeAttr('data-id');
            MatchingPull.keyboardSection = -1;
        }

        if (e.keyCode == 38 || e.keyCode == 40) {
            e.preventDefault()
        }
    },
    matchingSelected: function(ipt, itembox) {
        itembox.on('click', $('.school-name-wrap .school-name-list').find('li'), function(event) {
            if (event.target.nodeName == 'LI') {
                $(event.target).addClass('active').siblings('li').removeClass('active')
                ipt.val($(event.target).html()).attr('data-id', $(event.target).attr('data-id'));
                itembox.hide()
                ipt.focus()
                MatchingPull.keyboardSection = -1;
            }else{
                cancelbuble(event)
            }

        })
    },
    keyboardSectionFn: function() {
        $(document).on('keyup', function(event) {
            if ($('.school-name-wrap .school-name-list').is(":visible") == true) {
                var derail = 1;
                var inputV = $('#school-name');
                var e = event || window.event || arguments.callee.caller.arguments[0];
                var listLi = $('.school-name-wrap .school-name-list ul').find('li');
                switch (e.keyCode) {
                    case 38: //上
                        MatchingPull.keyboardSection--
                        if (MatchingPull.keyboardSection == -1) {
                            MatchingPull.keyboardSection = listLi.length - 1;
                        }
                        if (MatchingPull.keyboardSection == -2) {
                            MatchingPull.keyboardSection = listLi.length - 1;
                        }

                        listLi.eq(MatchingPull.keyboardSection).addClass('active').siblings().removeClass('active');
                        // derail = 1;
                        break;
                    case 40:
                        MatchingPull.keyboardSection++
                        if (MatchingPull.keyboardSection == listLi.length) {
                            MatchingPull.keyboardSection = 0
                        }

                        $('.school-name-wrap .school-name-list ul li').eq(MatchingPull.keyboardSection).addClass('active').siblings().removeClass('active');
                        // derail = 1;
                        break;
                }
                if (e.keyCode == 13) {
                    var mateListVal = listLi.eq(MatchingPull.keyboardSection).html();
                    inputV.val(mateListVal).attr('data-id', listLi.eq(MatchingPull.keyboardSection).attr('data-id'))
                    $('.school-name-wrap .school-name-list').hide()
                    MatchingPull.keyboardSection = -1;
                }
            }
        })
    },
    enterSelected: function() {
        $(document).on('keyup', function(event) {
            if ($('.school-name-wrap .school-name-list').is(":visible") == true) {
                if (e.keyCode == 13) {
                    var mateListVal = listLi.eq(MatchingPull.keyboardSection).html()
                    inputV.val(mateListVal)
                    $('.school-name-wrap .school-name-list').hide()
                    MatchingPull.keyboardSection = -1;
                }
            }
        })
    },
    itemHide: function() {
        $(document).on('click', function() {
            $('.school-name-wrap .school-name-list').hide()
            $('.school-name-wrap .school-name-list li').removeClass('active')
            MatchingPull.keyboardSection = -1;
        })
    }
}

MatchingPull.matchingSelected($('#school-name'), $('.school-name-wrap .school-name-list'))
$('#school-name').on('keyup', function(e) {
    MatchingPull.MatchingPullDown(e,$(this), $('.school-name-wrap .school-name-list'))
}).on('blur', function() {
    var schoolId = $("#school-name").attr("data-id");
    if (!schoolId) {
        $("#school-name").val("");
    }
});
MatchingPull.keyboardSectionFn()
MatchingPull.itemHide()

$('#detail-btn').on('click', function() {
    var body = {};
    var schoolId = parseInt($('input[name="school-name"]').attr('data-id'));
    if (!schoolId) {
        $("input[name=school-name]").val("");
        return;
    }
    body.school=$("input[name=school-name]").val()+"#"+schoolId;
    body.height =$("#height").val();
    body.weight =$("#weight").val();

    body.education = $('.education select option:selected').attr('data-id');
    body.minzhu = $('.minzhu select option:selected').attr('data-id');
    body.xuexing = $('.xuexing select option:selected').attr('data-id');
    body.marriage = $('.marriage select option:selected').attr('data-id');
    body.salary = $('.salary select option:selected').attr('data-id');
    body.children = $('label[class="radio-0"] > input[name="children"]').val();

    $.ajax({
        type: "post",
        url: ctx + "setting/detail",
        data: body ,
        xhrFields: {
            withCredentials: true
        },
        success: function(data) {
            if (data && data.code == 0) {
                pageToastSuccess("保存成功");
            } else {
                pageToastFail(data.msg);
            }
        }
    })
})


var checkAll = {
    pattern: {
        qqAccount: /^[\s\S*]{20}$/g,
        personalizedDomainName: /^[a-zA-Z0-9]{5,16}$/,
        realmName: /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/,
        other: /^([\u4e00-\u9fa5]{2}|[\s\S*]{4})$/g
    },
    checkItem: function(value) {
        if (this.pattern.qqAccount.test(value)) {
            return 1;
        } else {
            return 0;
        }
    },
    checkItem2: function(value) {
        if (this.pattern.personalizedDomainName.test(value)) {
            return 1;
        } else {
            return 0;
        }
    },
    checkItem3: function(value) {
        if (this.pattern.realmName.test(value)) {
            return 1;
        } else {
            return 0;
        }
    }
}

$('#contact-way').on('click', function() {
    var body = {};
    body.qq = $('input[name="qq-account"]').val();
    body.qqPrivate = $('#qq-privacy option:selected').data('id')
    body.wechat = $('input[name="wechat"]').val();
    body.wechatPrivate = $('#wechat-privacy option:selected').data('id')
    $.ajax({
        type: "post",
        url: ctx + "setting/contact",
        data: body,
        xhrFields: {
            withCredentials: true
        },
        success: function(data) {
            if (data.code == 0) {
                pageToastSuccess("保存成功")
            } else {
                pageToastFail(data.msg)
            }
        }
    })
})


$('#basic-information').on('click', function() {
    var body = {};
    // 头像没有
    body.sex = $('label[class="radio-0"] > input[name="radio"]').val();//性别

    var homeProvinceValue=$("#homeProvinceValue").val();
    var homeCityValue=$("#homeCityValue").val();
    var homeDistrictValue=$("#homeDistrictValue").val();
    var homeCity=homeProvinceValue+"#"+homeCityValue+"#"+homeDistrictValue;
    body.homeCity =homeCity;

    var nowProvinceValue=$("#nowProvinceValue").val();
    var nowCityValue=$("#nowCityValue").val();
    var nowDistrictValue=$("#nowDistrictValue").val();
    var nowCity=nowProvinceValue+"#"+nowCityValue+"#"+nowDistrictValue;
    body.nowCity =nowCity;


    body.job = $('.job-select select option:selected').attr('data-id');
    body.description = $('#description').val();
     //console.log(body);
    $.ajax({
        type: "post",
        url: ctx + "setting/basic",
        data: body,
        xhrFields: {
            withCredentials: true
        },

        success: function(data) {
            if (data.code == 0) {
                pageToastSuccess("保存成功");
            } else {
                pageToastFail(data.msg);
            }
        }
    })
})



$('#personal-links tr').each(function(i, item) {
    var personalLinks = $(item).find('td input');
    personalLinks.on('blur', function() {
        if (checkAll.checkItem3(personalLinks.val())) {
            $(item).find('.personal-link-icon').show();
        } else {
            $(item).find('.personal-link-icon').hide();
        }
    })
})

$('#personal-link-btn').on('click', function() {
    var body = {};

    function link() {
        for (var i = 0, len = $('#personal-links tr').find('.long-text-box').length; i < len; i++) {
            var url = $('#personal-links tr').find('.long-text-box input').eq(i).val()
            if (url.length > 0 && checkAll.checkItem3(url) == 0) {
                return 0;
                break;
            }
        }
        return 1;
    }
    body.weibo = $('input[name="micro-blog-sina"]').val();
    body.facebook = $('input[name="facebook"]').val();
    body.website = $('input[name="personal-website"]').val();
    if (link() == 1) {
        $.ajax({
            type: "post",
            url: ctx + "setting/link",
            data: body,
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(data) {
                if (data.code == 0) {
                    pageToastSuccess("comment_saved_success");
                } else {
                    pageToastFail(data.msg)
                }
            }
        })
    } else {
        // 链接校验失败
        pageToastFail("链接校验失败");
    }
})

// 认证
function Trade() {
    if ($('#industry').find('input:checked').parents('label').length >= 2) {

        $('.error-prompt').hide()
        return 2;
    } else if ($('#industry').find('input:checked').parents('label').length == 0) {

        $('.error-prompt').show()
        return 0;
    } else {

        $('.error-prompt').hide()
        return 1;
    }
}

var validateTrade = function () {
    if (Trade() == 2) {
        $('#industry').find('input:checkbox').not("input:checked").attr('disabled', true).parents('label').addClass('disabled');
    } else {
        $('#industry').find('input:checkbox').not("input:checked").removeAttr('disabled').parents('label').removeClass('disabled');
    }
}
validateTrade()

$('#industry label').on('click', function() {
    validateTrade()
})

$('#basic-authentication').on('click', function() {
    var industryList = $('#industry').find('label');
    var body = {};
    body.address = $('#details-address').val();
    city = ['nowProvince', 'nowCity', 'nowDistrict']
    $('.authentication-city-box .city-picker-span .title').find('.select-item').each(function(i, item) {
        body[city[i]] = $(item).attr('data-code');
    })
    body.industryIds = [];
    industryList.each(function(i, item) {
        if ($(item).find('input:checked').attr('industryIds')) {
            body.industryIds.push(parseInt($(item).find('input:checked').attr('industryIds')));
        }
    })
    Trade()
    if (Trade() == 1 || Trade() == 2) {
        $.ajax({
            type: "post",
            url: proMyZDomain + "/setting/authBasic",
            data: JSON.stringify(body),
            contentType: "application/json",
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(data) {}
        })
    }
})


//认证-轮播图
$('.carouselFigurePhoto').on('click', function() {
    var num = $('.queueList > ul > li').length
    if (num <= 0) {
        // 请上传轮播图
        pageToastFail("请上传轮播图")
        return;
    }

    var body = new Array()
    $('.queueList > ul > li').each(function(i, item) {
        if (!$(this).find('.error').length && !$(this).hasClass('state-progress')) {
            var data = {}
            data.id = $(this).data('id')
            data.path = $(this).data('path')
            data.name = $(this).data('name')
            // data.sort = i
            body.push(data)
        }
    })
    $.ajax({
        type: "post",
        url: proMyZDomain + "/setting/authHomePicture",
        data: JSON.stringify(body),
        contentType: "application/json",
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(data) {
            if (data.code != 0) {
                pageToastFail(data.msg)
            } else {
                pageToastSuccess("保存成功");
            }
        }
    })
})
