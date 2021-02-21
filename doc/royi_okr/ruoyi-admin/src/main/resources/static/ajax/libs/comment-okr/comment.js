$(function() {

    //监听ctrl + enter 提交事件
    $(document).keypress(function(e) {
        if(e.ctrlKey && $("#txt-comment").val() && e.which == 13 || e.which == 10 ) {
            if( $('.comment').hasClass("comment-open") ){
                $(".submit-box button").click();
            }
        }
    })

    /*
     * 打开评论窗口
     */
    $('.comment-input').focus(function(){
        $('.comment').addClass("comment-open");
        $('#txt-comment').focus();
    });

    /*
     * 评论复评论
     */
    $('.comment-detail').on("focus",".input-textarea",function(e){
        $(this).addClass("expense");
    });

    /*
     * 关闭评论窗口
     */
    $('.btn-link-default').focus(function(){
        $('.comment').removeClass("comment-open");
    });

    /*
     *  点击评论回复
     */
    $(".comment-list-box").on("click",".reply-btn",function (event) {
        if($(this).parents("li").siblings().find(".input-textarea").size()){
            $(this).parents("li").siblings().find(".input-textarea").remove();
        }
        if( $(this).parent().siblings(".input-textarea").size() > 0 ){
            $(this).parent().siblings(".input-textarea").remove();
        } else {
            $(this).parent().after(commentInputBox($(this).attr("parentid"),$(this).attr("commentid"),$(this).attr("type"),this));
        }
        //关闭子集评论展示
        $(this).parent().siblings(".comment-reply-list").hide();
    })

    /*
     *  展开评论
     */
    $('.reply-num').click(function () {
        let node = $(this).parent().siblings(".comment-reply-list");
        if( node.is(':hidden')){
            $(this).children("span").attr("data",$(this).children("span").text());
            $(this).children("span").text("收起评论");
            $(this).children('.fa').removeClass("fa-angle-down").addClass("fa-angle-up");
            node.show();
        } else {
            $(this).children("span").text( $(this).children("span").attr("data") );
            $(this).children('.fa').removeClass("fa-angle-up").addClass("fa-angle-down");
            node.hide();
        }
        //关闭回复输入框
        $(this).parent().siblings(".input-textarea").hide();
    });

    /*
     *  打开表情包
     */
    $('.fa-smile-o').qqFace({
        id : 'facebox',
        assign:'txt-comment',
        path:'/face/'
    });

    /*
     *  评论关联：团队和当前O对应的OK 对话框框打开
     */
    $('.pbox-at').click(function () {
        $.modal.open("选择需要关联的目标详情", "/comment/addAtAttment/" + $(this).parent().attr("orkid"),600,$(window).height()*0.61);
    });

    /*
     *  评论关联：项目 对话框框打开
     */
    $('.pbox-associated').click(function () {

        $.modal.open("选择需要关联的项目详情", "/comment/addAtProject/" + $(this).parent().attr("orkid"),600,$(window).height()*0.61);
    });

    /*
     *  打开附件上次对话框
     */
    $('.pbox-file').click(function () {
        $(".file-attachment")[0].click();
    });

    /*
     * 触发器
     * 文件上次
     */
    $("#file-attachment").on("change", function () {
        let formData = new FormData();
        let files = $("#file-attachment")[0].files;
        let flag  = 0;
        let hasLen = $(".attachment-list-wrap>li").length;
        //附件信息
        for (let i = 0; i < files.length; i++) {
            if ( (hasLen+i) > 5){
                $.modal.msg("最大上传6个文件！后面的不会上传！"); continue;
            }
            let fileInfo = {};
            fileInfo.name = files[i].name;
            fileInfo.size = files[i].size;
            fileInfo.type = files[i].type;
            if((fileInfo.size/(1024*1024)) > 300)  {
                $.modal.msg("文件'"+fileInfo.name+"'大小超过了300M!,无法上传！");
            } else if( fileInfo.type == ""){
                $.modal.msg("文件'"+fileInfo.name+"'的文件类型未知,无法上传！");
            } else {
                flag++;
                addCommentMetaFiles(files[i]);
                formData.append("commentMetaFiles", files[i]);
            }
        }
        //存在有效文件才上传
        if( flag > 0 ) {
            $.ajax({
                url: "/comment/meta/addFile",
                type: "post",
                data: formData,
                contentType: false,
                processData: false,
                xhr:function(){
                    let ajaxLoading = $.ajaxSettings.xhr();
                    if( ajaxLoading.upload ){
                        $(".comment-attachment-wrap .progress-file").show();
                        ajaxLoading.upload.addEventListener('progress',function(e){
                            let loaded  = e.loaded;                             //已经上传大小情况
                            let total   = e.total;                              //附件总大小
                            let percent = Math.floor(100*loaded/total)+"%";  //已经上传的百分比
                            $(".comment-attachment-wrap .progress-bar").css("width",percent);
                            if( percent == "100%" ) {
                                setTimeout(
                                function (){$(".comment-attachment-wrap .progress-file").hide()
                                },1000);
                            }
                        }, false);
                    }
                    return ajaxLoading;
                },
                success: function (result) {
                    //处理数据
                    againCommentMetaFiles(result.data);
                }
            })
        }
    })

    layer.photos({
        photos: '.showImage',
        anim: 0 ,
        shadeClose:false,
        closeBtn:2,
    });

})

/*
 *  @decs 最对具体对象，提交点赞信息
 *  @object_id 点赞对象类型
 *  @object_type 点赞对象ID
 */
function submitDigg(object_id,object_type,object) {
    //防止重复提交
    let self = $(object) ;
    let flag = self.attr('flag');
    if( flag === "true" ) return ;
    let data = {
        objectId:object_id,
        type:object_type, //type=2 表示对评论进行点赞
    };
    $.ajax({
        url:  "/thumb/digg/add",
        type: "post",
        dataType: "json",
        data: data,
        beforeSend: function () {
            self.attr('flag',"true");
        },
        success: function(result) {
            self.attr('flag',"false");
            if( result.code == 0 ){
                //实现点赞自增
                diggAdd(self);
                //实现点赞效果
                diggAddEffect(self);
            } else if(result.code == 301){
                $.modal.alertWarning(result.msg);
            } else {
                console.log("系统内部错误！");
            }
        }
    })
}

/**
 * 添加的附件
 * @param obj：附件信息
 * {
 *     href=""
 *     id = ""
 * }
 */
function addCommentMetaFiles(obj){
    let img = "";
    if(obj.type.match(/.jpg|.gif|.png|.jpeg|.bmp/i)){
        img = window.URL.createObjectURL(obj);
    }else{
        img = "/img/exe.svg";
    }
    let html  = '<li class="attachment-item">';
        html +=     '<a class="attachment-title" href="javascript:;">';
        html +=         '<img  width="16" height="16" src="'+img+'">'+obj.name;
        html +=     '</a>';
        html +=     '<div class="attachment-action">';
        html +=         '<a href="javascript:;" title="删除" onclick="deleteCommentMetaFiles(this)" class="delete-file">';
        html +=             '<i class="fa fa-trash-o"></i>';
        html +=         '</a>';
        html +=     '</div>';
        html += '</li>';
    $(".attachment-list-wrap").append(html);
}

function againCommentMetaFiles(obj){
    $(".attachment-list-wrap li:not(.flag)").each(function(index,element){
        $(this).addClass("flag");
        $(this).attr({"name":obj[index]["name"],"metaKey":obj[index]["type"],"metaValue":obj[index]["url"]})
    })
}

/**
 * 删除添加的附件
 * @param obj
 */
function deleteCommentMetaFiles(obj){
    $(obj).parent().parent().remove();
}

/*
* @Parma jquery 对象
* @desc 点赞框自增一个
* @return void
*/
function diggAddEffect( obj ){
    let html  = '<i class="fa fa-thumbs-o-up add"></i>';
        html += '<i class="fa fa-thumbs-o-up add"></i>';
        html += '<i class="fa fa-thumbs-o-up add"></i>';
    $(obj).append(html);
}
/*
 * @parma jquery 对象
 * @desc 点赞框自增一个
 * @return void
 */
function diggAdd( $dom ){
    //修改样式
    $dom.children("i").css({"color":"red"});
    //修改数据
    let $html = $dom.children("span");
    let text  = $html.text();
    if(!isNaN(text)){
        $html.html( (parseInt(text) + 1 ) +'&nbsp;' );
    } else {
        $html.html( 1 + '&nbsp;' );
    }
}

/*
*  @decs 针对 O 或者 KR 进行评论
*  @type 评论对象类型
*  @objectId 评论对象ID
*  type:0 :关键目标，1 :关键结果，2:评论
*/
function submitContent( objectId,type ) {
    $.modal.disable();
    let commentFiles = [];
    $(".attachment-list-wrap li").each(function(index,element){
        commentFiles.push({
            "metaKey":$(this).attr("metaKey"),
            "metaValue":$(this).attr("metaValue")
        })
    })

    let content = $("#txt-comment").val();
    if(!content && commentFiles.length==0){
        $.modal.loading("内容不能为空！");
        return false;
    }

    let data = {
        type:type,
        objectId:objectId,
        parentId:0,
        content:replaceEmoji(content),
        commentFiles:JSON.stringify(commentFiles),
    };


    $.ajax({
        url:  "/comment/add",
        type: "post",
        dataType: "json",
        data: data,
        beforeSend: function () {
            //实现点赞效果
            $.modal.loading("正在处理中，请稍后...");
        },
        success: function(result) {
            if( result.code == 0 ){
                //异步追加内容到文档
                $.modal.msgReloadSelf(result.msg,"success");
            } else {
                console.log("系统内部错误！");
            }
            $.modal.enable();
        }
    })
}

/*
*  @decs 针对 评论 进行评论
*  @type 评论对象类型
*  @objectId 评论对象ID
*  type:0 :关键目标，1 :关键结果，2:评论
*/
function submitSubContent(parentId,objectId,type,object) {
    let node    = $(object).parent();
    let content = node.prev().val();
    let append  = node.prev().attr("value");

    if( !content ) {
        $.modal.msg("内容不能为空！","warning");
        return false;
    } else {
        if( append == "true" ){
            content = content  +  node.prev().attr("connentap");
        }
    }

    let data = {
        type:type==3?2:type,
        parentId:parentId,
        objectId:objectId,
        content:content,
    };

    $.ajax({
        url:  "/comment/add",
        type: "post",
        dataType: "json",
        data: data,
        beforeSend: function () {
            //实现点赞效果
            $.modal.loading("正在处理中，请稍后...");
        },
        success: function(result) {
            if( result.code == 0 ){
                //异步追加内容到文档

                //1 关闭输入框
                $(".input-textarea").hide();

                //2 追加内容
                if(result.data){
                    node.parent().next().show().children(".comment-reply-box").prepend(commentSubBox(result.data));
                }

                //3 添加已经有多少条评论


                //3 关闭loading框
                $.modal.closeLoading();

            } else {
                console.log("系统内部错误！");
            }
        }
    })
}

/*
 *  表情包结果替换
 */
function replaceEmoji(str){
    str = str.replace(/\</g,'&lt;');
    str = str.replace(/\>/g,'&gt;');
    str = str.replace(/\n/g,'<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g,'<img src="/face/$1.gif" border="0" />');
    return str;
}


/*
 *  创建评论输入框
 */
function commentInputBox(parentId,objectId,type,obj) {

    console.log( "parentId:"+parentId + "==" +"objectId:"+ objectId + "==" + "type:" + type );

    if(pUtils.isEmpty(parentId) || pUtils.isEmpty(objectId) || pUtils.isEmpty(type) ) {
        return null;
    } else {
        let value     = type == 3?true : false;
        let parentCom = type == 3?$(obj).parent().prev().text() : "";
        if( parentCom.indexOf("@") != -1 ) {
            parentCom = parentCom.substr(0,parentCom.indexOf("@"));
        }
        let holder    = type == 3?"请写下您的回复...，@"+parentCom : "写下您的回复...";
        let userId    = type == 3?$(obj).parent().prev().attr("userid") : false;
        let connentAp = "<a userId='"+userId+"' href='javascript:;'>&nbsp;@"+parentCom+"</a>";
        let reply     = "回复";
        let inputHtml = '<div class="input-textarea">';
        inputHtml +=   '<textarea placeholder="'+ holder+ '" value="'+value+'" connentAp="'+connentAp+'"></textarea>';
        inputHtml +=   '<div class="input-footer">';
        inputHtml +=     '<button class="submit-btn" onclick="submitSubContent('+parentId+','+objectId+','+type+',this);return false;" type="button">'+ reply +'</button>';
        inputHtml +=   '</div>';
        inputHtml +='</div>';
        return inputHtml;
    }
}


/*
 *  创建子集评论
 */
function commentSubBox(okrComment) {
    let inputHtml  ='<div class="comment-item">';
        inputHtml +=    '<a class="avatar" href="javascript:;">';
        inputHtml +=        '<img src="'+okrComment.avatar+'">';
        inputHtml +=    '</a>';
        inputHtml +=    '<div class="comment-detail">';
        inputHtml +=        '<div class="user-info">';
        inputHtml +=            '<a class="name" href="javascript:;">'+okrComment.userName+'</a>';
        inputHtml +=            '<span class="create-time">'+okrComment.addShowTime+'</span>';
        inputHtml +=        '</div>';
        inputHtml +=        '<p class="content">'+okrComment.content+'</p>';
        let metaCommentsLength = okrComment.metaComments.length;
        //存在附件
        if( metaCommentsLength > 0){
            inputHtml +=        '<div type="attachments">';
            inputHtml +=            '<ul class="attachments-ul">';
            for(let i = 0 ;i<metaCommentsLength ; i++){
                inputHtml +=        '<li class="attachment-li">';
                if( okrComment.metaComments[i].metaKey == "file"){
                    inputHtml +=        '<a class="attachment-title showFile" href="'+okrComment.metaComments[i].metaValue+'">';
                    inputHtml +=        '<img width="50" onerror="reSetImgUrl(this,this.src,3)" src="/img/exe.svg">';
                } else {
                    inputHtml +=        '<a class="attachment-title showImage" href="javascript:;">';
                    inputHtml +=        '<img width="50" onerror="reSetImgUrl(this,this.src,3)" src="'+okrComment.metaComments[i].metaValue+'">';
                }
                inputHtml +=        '<br/><span>'+okrComment.metaComments[i].metaKey+'</span>';
                inputHtml +=        '</a>';
                inputHtml +=        '</li>';
            }
            inputHtml +=            '</ul>';
            inputHtml +=        '</div>';
        }
        inputHtml +=        '<div class="reply">';
        inputHtml +=            '<span class="reply-btn" parentid="220" commentid="220" type="2">回复</span>';
        inputHtml +=            '<span class="digg" title="点赞" onclick="submitDigg('+okrComment.id+','+2+',this)">';
        inputHtml +=            '<span >0&nbsp;</span>';
        inputHtml +=                '<i class="fa fa-thumbs-o-up"></i>';
        inputHtml +=            '</span>';
        inputHtml +=        '</div>';
        inputHtml +=        '<div class="comment-reply-list" style="display: none;">';
        inputHtml +=            '<div class="comment-reply-box"></div>';
        inputHtml +=        '</div>';
        inputHtml +=    '</div>';
        inputHtml +='</div>';
    return inputHtml;
}

/*
 * @parma 评论加载展示效果
 * @return void
 */
function commentUiLoadEffect(){
    console.log("我是评论加载内容！");
}

/*
 * 加载更多评论
 * @param okrId 需要加载更多评论的 okr id
 * @param obj
 */
function showMoreComment(okrId,obj){
    let node    = $(obj);
    let data = {
        type:0,
        parentId:0,
        objectId:okrId,
        pageNum:node.attr("page")
    };
    $.ajax({
        url:  "/comment/list",
        type: "post",
        dataType: "json",
        data: data,
        beforeSend: function () {
            // 正在加载更多
        },
        success: function(result) {
            let length = result.rows.length;
            if( length == node.attr("left") ) { //删除更多评论按钮
                $(".load-more-comment").remove();
            } else {
                let left = node.attr("left") - 10 ;
                if( left < 0 ) left = 0;
                node.attr({"left":left,"page":node.attr("page")+1});
                $(".load-more-comment button").html("查看更多"+left+"条评论");
            }
            let html = "";
            for(let i=0;i<length;i++){
                html+= "<li>"+commentSubBox(result.rows[i])+"</li>";
            }
            $(".comment-list-box").append(html);

            layer.photos({
                photos: '.showImage',
                anim: 0 ,
                shadeClose:false,
                closeBtn:2,
            });
        }
    })
}

/*
 * 回调函数：评论关联OKR 选择结果
 * @param rows1  O 选择结果
 * @param rows2  KR 选择结果
 */
function selectOKRResult(rows1,rows2){
    let html = ""
    //o
    for (let i = 0 ;i  < rows1.length ; i++){
        html += "@@:<"+rows1[i]["content"]+">\n";
    }

    //kr
    for (let i = 0 ;i  < rows2.length ; i++){
        html += "@@:<"+rows2[i]["content"]+">\n";
    }

    $("#txt-comment").val($("#txt-comment").val() + html);

    $("#txt-comment").focus();
}


/*
 * @decs 图片url
 */
function getBase64Image(img) {
    let canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    let ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    let ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();
    let dataURL = canvas.toDataURL("image/"+ext);
    return dataURL;
}

/*
 * @decs 图片加载错误，重新加载
 */
function reSetImgUrl(imgObj,imgSrc,maxErrorNum) {
    if (maxErrorNum > 0) {
        imgObj.onerror=function(){
            reSetImgUrl(imgObj,imgSrc,maxErrorNum-1);
        };
        setTimeout(function(){
            imgObj.src=imgSrc;
        },500);
    } else {
        imgObj.onerror=null;
        imgObj.src="/img/noImg.png";
    }
}