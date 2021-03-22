package com.hulk.limitb.constant;


import com.hulk.limitb.enums.LimitbStrategy;
import com.hulk.limitb.enums.LimitbType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author  hulk
 * 锁基本信息
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LimitbInfo {

    // 限流标识
    private  String limitName;

    //每秒生产令牌数量
    private int replenish;

    //令牌桶的容量，允许在一秒钟内完成的最大请求数
    private  int capacity;

    private  LimitbType limitType ;

    private LimitbStrategy limitStrategy ;


}
