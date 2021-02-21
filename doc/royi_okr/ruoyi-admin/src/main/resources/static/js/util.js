//本地Util工具
var pUtils = {
    isEmpty : function(param)  {
        if(param){
            let param_type = typeof(param);
            if(param_type == 'object'){
                //要判断的是【对象】或【数组】或【null】等
                if(typeof(param.length) == 'undefined'){
                    if(JSON.stringify(param) == "{}"){
                        return true;//空值，空对象
                    }
                }else if(param.length == 0){
                    return true;//空值，空数组
                }
            }else if(param_type == 'string'){
                //如果要过滤空格等字符
                var new_param = param.trim();
                if(new_param.length == 0){
                    //空值，例如:带有空格的字符串" "。
                    return true;
                }
            }else if(param_type == 'boolean'){
                if(!param){
                    return true;
                }
            }else if(param_type== 'number'){
                if(!param){
                    return true;
                }
            }
            return false;//非空值
        }else if(param != 0){
            //(1)数字0、00等，如果可以只输入0，则需要另外判断。
            return true;
        }else{
            //空值,例如：
            //(1)null
            //(2)可能使用了js的内置的名称，例如：var name=[],这个打印类型是字符串类型。
            //(3)空字符串''、""。
            return true;
        }
    }
};


//本地cookie处理
var  pCookie = {
    // 设置
    set : function(key, value, delay)  {
        //默认cookie为七天之后过期 3s 4m 5h 7d 秒 分 时 天
        var delay = delay?delay.toLowerCase():"7d".toLowerCase();
        var expireDate = new Date();
        var num = parseInt(delay);
        if( delay.indexOf("d") !== -1 ) {
            expireDate.setDate(expireDate.getDate() + num);
        } else if (delay.indexOf("h") !== -1) {
            expireDate.setHours(expireDate.getHours() + num);
        } else if (delay.indexOf("m") !== -1) {
            expireDate.setMinutes(expireDate.getMinutes() + num);
        } else if (delay.indexOf("s") !== -1) {
            expireDate.setSeconds(expireDate.getSeconds() + num);
        } else {
            expireDate.setDate(expireDate.getDate() + num);
        }
        if(typeof value == "object") {
            value = JSON.stringify(value);
        }
        value = escape(value);
        document.cookie = key + "=" + value + ";expires=" + expireDate.toGMTString()+";path=/";
        return this.get(key);
    },
    //获取
    get : function(key) {
        var objCookie = {};
        var cookie = document.cookie;
        var keyValueList = cookie.split(";");
        for (var index in keyValueList)  {
            var keyValue = keyValueList[index].split("=");
            var k = keyValue[0].trim();
            var v = keyValue[1];　　　　　　　　　v = unescape(v);
            v = this.decodeJson(v);
            objCookie[k] = v;
        }
        if(typeof key == "undefined")  {
            return objCookie;
        }
        return objCookie[key];
    },
    //删除
    del : function(key) {
        //删除所有cookie
        if(typeof key == "undefined")  {
            var cookieList = this.get();
            for(key in cookieList)
            {
                this.del(key);
            }
            return true;
        }  else {
            if(this.get(key) == "undefined") {
                return false;
            }  else  {
                return this.set(key,'',"0s");
            }
        }
    },
    decodeJson : function(value) {
        //数组转成的对象字符串
        var regAryStr = /^\[[\s|\S]*\]$/;
        //对象转成的对象字符串
        var regObjStr = /^\{([\"\s|\S]+\"\:\"[\s|\S]*)+\"\}$/;
        if(regAryStr.test(value)) {
            return eval("(" + value + ")");
        }
        if(regObjStr.test(value)) {
            return JSON.parse(value);
        }
        return value;
    }
};

//时间相关
var pTime = {
    getYear : function(variable){
        return this.getDate().getFullYear();
    },
    getMonth : function(){

    },
    getDay : function(){

    },
    getDate : function(time){
        if(time){
            //指定时间戳
            return new Date(time);
        }else{
            //当前时间戳
            return new Date();
        }
    }
};

//获取url参数
var query = {
    getQueryVariable : function(variable){
        let query = "";
        if(top.location!=self.location){
            query = window.top.location.search.substring(1);
        }else{
            query = window.location.search.substring(1);
        }
        let vars  = query.split("&");
        for (let i=0; i<vars.length; i++) {
            let pair = vars[i].split("=");
            if( pair[0] == variable ){ return pair[1]; }
        }
        return(false);
    },
    jsonOwn : function(jsonObj,index){
        if(typeof (langArray)  == "object"){
            for(var  key in langArray) {
                if(key == index || langArray[key] == index){
                   return key;
                }
            }
            return false;
        } else {
            return false;
        }
    }
};


//前端显示的语言
var langArray = {"en":"en_US","ja":"ja_JP","zh":"zh_CN"};


//获得语种
var getLanguage = function () {
    var language = query.getQueryVariable("language");
    if( language ) {
        //兼容处理
        if(language.indexOf("_") ){
            language = language.split("_")[0];
        }else if(language.indexOf("-")) {
            language = language.split("-")[0];
        }
        //是否存在，否就赋予默认值
        if(!query.jsonOwn(langArray,language)){
            language = "en";
        }
        pCookie.set('localSession', langArray[language],"365d" );
        return language;
    } else {
        //强制传换 zh_CN  => zh-CN
        let localLanguage  = pCookie.get('localSession');
        if(localLanguage){
            language = localLanguage.replace("_","-").split("-")[0];
        }else{
            language = "en";
        }
        return language;
    }
};
