<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
</head>
<body>
<form action="/user/login"  method="post">
    <div><label> 邮箱 : <input type="email" name="email"/> </label></div>
    <div><label> 密 码 : <input type="password" name="password"/> </label></div>
    <h1 style="color: red;">${CodeMsg!}</h1>
    <div><input type="submit" value="登录"/></div>
    <div><input type="button" value="注册" onclick="toRegister()"/></div>
</form>
<script>
    function toRegister() {
        window.location.href="toRegister";
    }
</script>
</body>
</html>