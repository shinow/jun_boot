/**
 * Created by beanCao on 2016/11/14.
 */

$(function() {

    'use strict';

    var recordCoversimg = $('.editor-portrait #image').attr("src")

    var console = window.console || {
        log: function() {}
    };
    var URL = window.URL || window.webkitURL;
    var $image = $('#image');
    var $download = $('#download');
    var $dataX = $('#dataX');
    var $dataY = $('#dataY');
    var $dataHeight = $('#dataHeight');
    var $dataWidth = $('#dataWidth');
    var $dataRotate = $('#dataRotate');
    var $dataScaleX = $('#dataScaleX');
    var $dataScaleY = $('#dataScaleY');
    var options = {
        // viewMode:1,
        cropBoxResizable: true,
        autoCropArea: 1,
        aspectRatio: 1 / 1,
        preview: '.img-preview',
        dragMode: 'move',
        toggleDragModeOnDblclick: false,
        crop: function(e) {
            $dataX.val(Math.round(e.x));
            $dataY.val(Math.round(e.y));
            $dataHeight.val(Math.round(e.height));
            $dataWidth.val(Math.round(e.width));
            $dataRotate.val(e.rotate);
            $dataScaleX.val(e.scaleX);
            $dataScaleY.val(e.scaleY);
        }
    };
    var originalImageURL = $image.attr('src');
    var uploadedImageURL;
    var $xhrAjax;


    // Tooltip
    // $('[data-toggle="tooltip"]').tooltip();
    // Cropper
    $image.on({
        'build.cropper': function(e) {
            // console.log(e.type);
            if ($('.user-avatars img').attr('src').lastIndexOf('noavatar.gif') == -1) {
                $('.cover').remove();
                $('.cover-preview').remove();
                $('.rotate-area').css('display', 'block');
            }
        },
        'built.cropper': function(e) {
            // console.log(e.type);
        },
        'cropstart.cropper': function(e) {
            // console.log(e.type, e.action);
        },
        'cropmove.cropper': function(e) {
            // console.log(e.type, e.action);
        },
        'cropend.cropper': function(e) {
            // console.log(e.type, e.action);
        },
        'crop.cropper': function(e) {
            // console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
        },
        'zoom.cropper': function(e) {
            // console.log(e.type, e.ratio);
        }
    }).cropper(options);


    // Buttons
    if (!$.isFunction(document.createElement('canvas').getContext)) {
        $('button[data-method="getCroppedCanvas"]').prop('disabled', true);
    }

    if (typeof document.createElement('cropper').style.transition === 'undefined') {
        $('button[data-method="rotate"]').prop('disabled', true);
        $('button[data-method="scale"]').prop('disabled', true);
    }

    // Options
    $('.docs-toggles').on('change', 'input', function() {
        var $this = $(this);
        var name = $this.attr('name');
        var type = $this.prop('type');
        var cropBoxData;
        var canvasData;

        if (!$image.data('cropper')) {
            return;
        }

        if (type === 'checkbox') {
            options[name] = $this.prop('checked');
            cropBoxData = $image.cropper('getCropBoxData');
            canvasData = $image.cropper('getCanvasData');

            options.built = function() {
                $image.cropper('setCropBoxData', cropBoxData);
                $image.cropper('setCanvasData', canvasData);
            };
        } else if (type === 'radio') {
            options[name] = $this.val();
        }

        $image.cropper('destroy').cropper(options);
    });

    $('.btn-zoomout').on('click', function() {
        console.log($image);
        $image.cropper('zoom', 0.1);
    })

    $('.btn-zoomin').on('click', function() {
        console.log($image);
        $image.cropper('zoom', -0.1);
    })

    // Methods
    $('.docs-buttons').on('click', '[data-method]', function() {
        var $this = $(this);
        var data = $this.data();
        var $target;
        var result;

        if (recordCoversimg === $('.editor-portrait #image').attr("src")) {
            $('.editor-portrait').addClass('hide');
            hideGlobalMaskLayer()
            return;
        }

        if ($this.prop('disabled') || $this.hasClass('disabled')) {
            return;
        }

        if ($image.data('cropper') && data.method) {
            data = $.extend({}, data); // Clone a new one

            if (typeof data.target !== 'undefined') {
                $target = $(data.target);

                if (typeof data.option === 'undefined') {
                    try {
                        data.option = JSON.parse($target.val());
                    } catch (e) {
                        console.log(e.message);
                    }
                }
            }

            if (data.method === 'rotate') {
                $image.cropper('clear');
            }

            result = $image.cropper(data.method, data.option, data.secondOption);

            if (data.method === 'rotate') {
                $image.cropper('crop');
            }

            switch (data.method) {
                case 'scaleX':
                case 'scaleY':
                    $(this).data('option', -data.option);
                    break;

                case 'getCroppedCanvas':
                    if (result) {
                        crop(result);
                    }

                    break;

                case 'destroy':
                    if (uploadedImageURL) {
                        URL.revokeObjectURL(uploadedImageURL);
                        uploadedImageURL = '';
                        $image.attr('src', originalImageURL);
                    }

                    break;
            }

            if ($.isPlainObject(result) && $target) {
                try {
                    $target.val(JSON.stringify(result));
                } catch (e) {
                    console.log(e.message);
                }
            }

        }
    });

    function crop(result) {
        if ($('.cover').length == 0) {
            $('.btn-submit .pop-confirm').attr('disabled', 'disabled').val("上传中...").addClass('cursor-default btn-disabled').removeClass('btn-default-main');
            var dataurl = result.toDataURL('image/jpeg');
            var blob = dataURLtoBlob(dataurl);
            //使用ajax发送
            var fd = new FormData();
            fd.append("file", blob, "image.jpeg");
            if ($('.user-avatars').hasClass('regist')) {
                sendAjax(fd, result, proUploadZDomain + '/upload/image2?uploadtype=registMemberFace');
            } else if($('.user-avatars').hasClass('team')){
                sendAjax(fd, result, proUploadZDomain + '/upload/image?uploadtype=zteamCover');
            }else {
                sendAjax(fd, result,  '/setting/avatar/upload?uploadtype=memberFace');
            }
        } else {
            pageToastFail("请上传图片");
        }
    }
    // 转化为base64转码
    function dataURLtoBlob(dataurl) {
        var arr = dataurl.split(','),
            mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]),
            n = bstr.length,
            u8arr = new Uint8Array(n);
        while (n--) {
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], {
            type: mime
        });
    }
    // 使用ajax发送上传图片
    function sendAjax(fd, result, url) {
        $xhrAjax = $.ajax({
            url: url,
            type: 'POST',
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            data: fd,
            cache: false,
            processData: false,
            contentType: false,
            success: function(data) {
                if (data.code !== 0) {
                    pageToastFail(data.msg)
                    $('.btn-submit .pop-confirm').removeAttr('disabled').val('确定').removeClass('cursor-default btn-disabled').addClass('btn-default-main');
                    return false;
                }
                var response = data
                $(".cursor-default").val( "上传中" + 100 +"%" );
                hideGlobalMaskLayer()
                $('.editor-portrait').addClass('hide');
                $('#coverPicker').removeClass('status-progress');
                $('.btn-default-main').find('img').remove();
                $('.user-avatars').find('img').show().attr('src', result.toDataURL());
                // $('.upload-normal-box').html('修改封面');
                // $('.upload-normal-box').css('color', '#fff');
                // $('.upload-normal-box').addClass('upload-yellow-icon');
                // $('#form-data-cover').css('background', 'rgba(0,0,0,0.5)');

                // $($('.work-verify')[5]).find('.error-prompt').addClass('hide');
                $('.btn-submit .pop-confirm').removeAttr('disabled').val('确定').removeClass('cursor-default btn-disabled').addClass('btn-default-main');
                var datas = {
                    'avatarPath': response.data
                   // 'faceName': response.data.name
                };
                if ($('.user-avatars').hasClass('team')) {
                    // var data = {
                    //     'filePath': response.path,
                    //     'fileName': response.name
                    // };
                    $('#teamAvatar').attr({'data-filepath': response.data.path, 'data-filename': response.data.name})
                    // saveImg(proMyZDomain + "/team/" + $('#form-data-id').attr('value') + "/uploadAvatar", datas);
                } else if ($('.user-avatars').hasClass('regist')) {
                    // 注册页
                    $('#form-data-cover').attr('data-coverName', response.data.name);
                    $('#form-data-cover').attr('data-coverPath', response.data.path);

                    console.log('点击完成收集信息提交数据！');
                } else {

                    saveImg("/setting/avatar", datas);
                }
            },
            xhr: function(){
                var xhr = $.ajaxSettings.xhr();
                if(onprogress && xhr.upload) {
                    xhr.upload.addEventListener("progress" , onprogress, false);
                    return xhr;
                }
                function onprogress(evt){
                    if(evt.lengthComputable){
                        var loaded = evt.loaded;     //已经上传大小情况
                        var tot = evt.total;      //附件总大小
                        var per = Math.floor(100*loaded/tot);  //已经上传的百分比
                        if(per == 100){
                            per = 99
                        }
                        $(".cursor-default").val( "上传中" + per +"%" ).addClass('btn-disabled').removeClass('btn-default-main');

                        $('#coverPicker').addClass('status-progress');
                    }
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(textStatus == 'error'){
                    // 网络错误
                    pageToastFail("网络错误")
                }
                $('.btn-submit .pop-confirm').removeAttr('disabled').val("确定").removeClass('cursor-default btn-disabled').addClass('btn-default-main');
            }
        })
    }

    function saveImg(url, data) {
        $.ajax({
            type: "post",
            url: url,
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            data: data,
            success: function(data) {
                if (data.code == 0) {
                    if (!$('.user-avatars').hasClass('regist')) {
                        location.reload();
                    }
                }else{
                    pageToastFail(data.msg)
                }
            }
        })
    }

    // Keyboard
    $(document.body).on('keydown', function(e) {

        if (!$image.data('cropper') || this.scrollTop > 300) {
            return;
        }
        if($('.editor-portrait').is(':visible') && $image.attr('src')){
            switch (e.which) {
                case 37:
                    e.preventDefault();
                    $image.cropper('move', -1, 0);
                    break;

                case 38:
                    e.preventDefault();
                    $image.cropper('move', 0, -1);
                    break;

                case 39:
                    e.preventDefault();
                    $image.cropper('move', 1, 0);
                    break;

                case 40:
                    e.preventDefault();
                    $image.cropper('move', 0, 1);
                    break;
            }
        }
    });


    // Import image
    var $inputImage = $('#inputImage');

    if (URL) {
        $inputImage.change(function() {
            // $('.fa-upload').hide()
            console.log($inputImage[0].files[0])
            var size = $inputImage[0].files[0].size / (1024 * 1024);
            var type = $inputImage[0].files[0].type;
            if (type != "image/gif" && type != "image/png" && type != "image/jpeg") {
                pageToastFail('图片格式仅支持gif、png、jpeg格式');
                $inputImage.val('');
            } else {
                var upImgW, upImgH;
                img($inputImage[0].files[0]);

                function img(fileObj) {
                    if (fileObj) {
                        //读取图片数据
                        var f = fileObj;
                        var reader = new FileReader();
                        reader.onload = function(e) {
                            var data = e.target.result;
                            //加载图片获取图片真实宽度和高度
                            var image = new Image();
                            image.onload = function() {
                                upImgW = image.width;
                                upImgH = image.height;
                                console.log(upImgW + '======' + upImgH + "=====" + f.size);
                                ajax();
                            };
                            image.src = data;
                        };
                        reader.readAsDataURL(f);
                    } else {
                        var image = new Image();
                        image.onload = function() {
                            upImgW = image.width;
                            upImgH = image.height;
                            var fileSize = image.fileSize;
                            console.log(upImgW + '======' + upImgH + "=====" + fileSize);
                            ajax();
                        }
                        image.src = input.value;
                    }
                }

                function ajax() {
                    if (size > 5) {
                        pageToastFail('图片大小不能超过5M');
                        $inputImage.val('');
                    } else {
                        $('.cover').remove();
                        $('.cover-preview').remove();
                        $('.rotate-area').show()
                        // $('.editor-portrait').hide()
                        var files = $inputImage[0].files;
                        var file;

                        if (!$image.data('cropper')) {
                            return;
                        }

                        if (files && files.length) {
                            file = files[0];

                            if (/^image\/\w+$/.test(file.type)) {
                                if (uploadedImageURL) {
                                    URL.revokeObjectURL(uploadedImageURL);
                                }

                                uploadedImageURL = URL.createObjectURL(file);
                                $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
                                $inputImage.val('');
                            } else {
                                window.alert('Please choose an image file.');
                            }
                        }
                    }
                }
            }
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }


    $('.edtior-crop-close,.btn-submit .btn-group-crop .btn-default-secondary ').on('click', function() {
        if($xhrAjax){
            $xhrAjax.abort();
            $('#coverPicker').removeClass('status-progress');
        }
        $('.editor-portrait').addClass('hide');
        hideGlobalMaskLayer()
        // $('.mask-layer-full-screen').addClass('hide');
    })


});

// $('.btn-reset').click(function() {
//     if(!$('.editor-portrait .pop-confirm').prop('disabled')){
//         $('.res-only').click();
//     }else{
//         pageToastFail(messagesWeb.uploadWorksOrArticles.uploading_text)
//     }
// })

// 注册头像、个人资料、团队资料、认证账号头像
$('.user-avatars').on('click', function() {
    if($(this).hasClass('regist')){
        $('.editor-portrait').removeClass('hide');
        showGlobalMaskLayer()
        $('.editor-portrait .upload-headimg').html("上传头像" + ' <i class="edtior-crop-close"></i>');
        !$('.register-head-priview').length && $('.editor-portrait .img-container').append('<div class="register-head-priview">' + "头像预览" + '</div>');
        $('.btn-submit .pop-confirm').removeAttr('disabled').val("确定").removeClass('cursor-default').addClass('btn-default-main');
    }else{
        showRemindBindLayer(userAvatarUnbindPhoneCb)
    }
    function userAvatarUnbindPhoneCb(){
        $('.editor-portrait').removeClass('hide');
        showGlobalMaskLayer()
        $('.editor-portrait .upload-headimg').html("上传头像" + ' <i class="edtior-crop-close"></i>');
        !$('.register-head-priview').length && $('.editor-portrait .img-container').append('<div class="register-head-priview">' + "头像预览" + '</div>');
        $('.btn-submit .pop-confirm').removeAttr('disabled').val("确定").removeClass('cursor-default').addClass('btn-default-main');
    }

})
$('.editor-portrait .upload-headimg').on('click', '.edtior-crop-close', function() {
    $('.editor-portrait').addClass('hide');
    hideGlobalMaskLayer()
})