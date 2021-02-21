/*弹出举报不发评论信息js*/
var targetId;
function jubao(targetId){
    //console.log(targetId);
    var content='<style>.jubao{background:#fff;position:relative;overflow:hidden;width:404px;height:133px}.radio-item{width:160px;height:28px;position:relative;float:left;cursor:pointer}.radio-box{width:16px;height:16px;background:url(/resources/myblog/images/icon/icon_radio_0.jpg);display:block;float:left;margin-top:5px;margin-right:14px}.radio-checked{background:url(/resources/myblog/images/icon/icon_radio_1.jpg)!important}.radio-item .radio-text{margin-top:4px;display:inline-block;font-family:"Microsoft Yahei";font-weight:400;font-size:14px;color:#333}</style>';
    content+='<div class="jubao" >'+
    '<div style="text-align: center;display: block;margin: 0 auto;overflow: hidden;padding: 17px 20px 20px 50px;">'+
    '<div class="radio-item">'+
    '<span data-value="1"  class="radio-box radio-checked"></span>'+
    '<span class="radio-text">淫秽色情</span>'+
    '</div>'+
    '<div class="radio-item">'+
    '<span  data-value="2"  class="radio-box"></span>'+
    '<span class="radio-text">广告垃圾</span>'+
    ' </div>'+
    '<div class="radio-item">'+
    '<span  data-value="3" class="radio-box"></span>'+
    '<span class="radio-text">违法信息</span>'+
    ' </div>'+
    '<div class="radio-item">'+
    '<span  data-value="4" class="radio-box"></span>'+
    ' <span class="radio-text">其他</span>'+
    ' </div>'+
    ' </div>'+
    '<div style="text-align: center;display: inline-block;width: 100%;">  <a id="jubao_btn_"+targetId class="am-btn am-btn-secondary am-round ui button primary"  style="line-height: 0.8;" onclick="jubaoSubmit('+targetId+')" >提交</a></div>'+
    ' </div>';

    layer.open({
        type: 1,
        title:"请选择举报类型",
        skin: '123', //加上边框
        area: ['404px','178px'],
        shadeClose: true,
        content: content
    });

}
function jubaoSuccess(){
    var content='<style>.rpt-hint{display:none;padding-top:48px}.rpt-hint-image{display:inline-block;width:40px;height:40px;background:url(/resources/myblog/images/icon/icon_ok.png) no-repeat;margin-bottom:24px;margin-left:182px}.rpt-hint-text{font-size:15px;font-weight:600;text-align:center} </style>';
    content+='<div class="jubaoSuccess" style="background: #fff;position:relative; overflow:hidden ;width:404px;height:178px;">'+
    '<div class="rpt-hint rpt-page" style="display: block;">'+
    '<div class="rpt-hint-image"></div>'+
    '<div class="rpt-hint-text">您的举报已成功提交，正在忙碌的审核中。</div>'+
    '</div>'+
    '</div>';
    layer.open({
        type: 1,
        title:false,
        skin: '123', //加上边框
        area: ['404px','178px'],
        shadeClose: true,
        content: content
    });
}
$(document).on('click', '.jubao .radio-item', function() {
    // alert($(this).siblings().length);
    var $obj=$(this).children().eq(0);
    var v=$obj.data("value");
    //layer.msg("当前选中："+v);
    var b=$obj.hasClass("radio-checked");
    if(b){
        $.each($(this).siblings(),function(i,obj){
            var b= $(obj).children().eq(0).hasClass("radio-checked");
            $(obj).children().eq(0).removeClass("radio-checked");
        })
    }else{
        $obj.addClass("radio-checked");
        $.each($(this).siblings(),function(i,obj){
            $(obj).children().eq(0).removeClass("radio-checked");
        })
    }
});
function getJuBaoValue(){
    var now_value="";
    $.each( $(".jubao .radio-item"),function(){
        if($(this).children().eq(0).hasClass("radio-checked")){
            now_value=$(this).children().eq(0).data("value");
        }
    });
    return now_value;
}

function jubaoSubmit(targetId){
    var v=getJuBaoValue();
    layer.msg("举报提交的是："+v);
}