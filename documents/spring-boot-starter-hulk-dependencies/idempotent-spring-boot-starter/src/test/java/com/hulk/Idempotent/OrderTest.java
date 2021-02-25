package com.hulk.Idempotent;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;



import com.hulk.Idempotent.constant.GlobalConstant;
import com.hulk.Idempotent.model.Order;




public class OrderTest {

    public static void main(String[] args) {
           String json = "{ \"id\": 1 ,\"name\": \"hulk\",\"address\": \"陆家嘴\"}" ;
            String url = "http://localhost:80/idempotentGenerator";
            String  idempotent =  HttpUtil.get(url);
            Console.log(idempotent);
            String res2 = HttpRequest.post("http://localhost:80/addIdempotent").header(GlobalConstant.IDEMPOTENT,idempotent)
            .body(json)
            .execute().body();
             Console.log(res2);

    }
}


