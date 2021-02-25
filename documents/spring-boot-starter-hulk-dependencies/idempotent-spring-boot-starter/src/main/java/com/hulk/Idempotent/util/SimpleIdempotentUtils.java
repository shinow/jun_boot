package com.hulk.Idempotent.util;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SimpleIdempotentUtils {

    private static Map<String, Object> idempotentMaps = new ConcurrentHashMap<String, Object>();
    // 1.什么idempotent（令牌） 表示是一个零时不允许有重复相同的值（临时且唯一）
    // 2.使用令牌方式防止idempotent重复提交。

    // 使用场景:在调用第API接口的时候，需要传递令牌,该Api接口 获取到令牌之后，执行当前业务逻辑，让后把当前的令牌删除掉。
    // 在调用第API接口的时候，需要传递令牌 建议15-2小时
    // 代码步骤：
    // 1.获取令牌
    // 2.判断令牌是否在缓存中有对应的数据
    // 3.如何缓存没有该令牌的话，直接报错（请勿重复提交）
    // 4.如何缓存有该令牌的话，直接执行该业务逻辑
    // 5.执行完业务逻辑之后，直接删除该令牌。

    // 获取令牌
    public static synchronized String getIdempotent() {
        // 如何在分布式场景下使用分布式全局ID实现
        String idempotent = "idempotent" + System.currentTimeMillis();
        // hashMap好处可以附带
        idempotentMaps.put(idempotent, idempotent);
        return idempotent;
    }

    // generateidempotent();

    public static boolean findIdempotent(String idempotentKey) {
        // 判断该令牌是否在idempotentMap 是否存在
        String idempotent = (String) idempotentMaps.get(idempotentKey);
        if (StringUtils.isEmpty(idempotent)) {
            return false;
        }
        // idempotent 获取成功后 删除对应idempotentMapsidempotent
        idempotentMaps.remove(idempotent);
        return true;
    }
}