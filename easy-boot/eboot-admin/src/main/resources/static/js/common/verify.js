layui.use('form', function () {
    var form = layui.form;
    //自定义验证规则
    form.verify({
        username: function (value) {
            if (value.length < 6 || value.length > 12) {
                return '请输入6到12位的用户名';
            }
        }
        , password: function (value) {
            if (value.length < 4) {
                return '内容请输入至少4个字符';
            }
        }
        , phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']
        , email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']
        , rolename: function (value) {
            if (value.length > 128) {
                return '角色名不超过128个字符';
            }
        }
        , menuName: function (value) {
            if (value.length < 2 || value.length > 12) {
                return '请输入2到12位字符';
            }
        }
    });
});