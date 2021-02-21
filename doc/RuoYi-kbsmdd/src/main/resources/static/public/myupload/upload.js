$(function () {
    $(".file-upload").on("change", function(e){
        console.log(e);
        var file = this.files[0];
        console.log(file);
        var _this = this;
        var reader = new FileReader();
        reader.onload = function(e){
            console.log( e.target.result);
            $.myOperate.jsonPostUploadIMG("/common/upload/img", file, function (res) {
                if (res.code == 0) {
                    var urlInput = _this.parentElement.getElementsByClassName("file-upload-url")[0];
                    urlInput.value = res.id
                    $(_this).parent().find(".file-upload-img").attr("src", e.target.result);
                } else {
                    console.log("上传失败")
                }

            })

        };
        reader.readAsDataURL(file);

    });

})