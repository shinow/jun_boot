package com.hulk.Idempotent.ctl;


import com.hulk.Idempotent.annotation.IdempotentBarrier;
import com.hulk.Idempotent.enums.IdempotentType;
import com.hulk.Idempotent.model.Order;

import com.hulk.Idempotent.util.RedisIdempotentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class OrderController {

    @Autowired
    RedisIdempotentUtils  redisIdempotentUtils ;

    // 从redis中获取Idempotent
    @RequestMapping("/idempotentGenerator")
    public String IdempotentGenerator() {
      String idempotent =  redisIdempotentUtils.getIdempotent();
        log.info(idempotent);
        return idempotent;
    }

    // 验证Idempotent
    @RequestMapping(value = "/addIdempotent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @IdempotentBarrier(value = IdempotentType.IDEMPOTENT_HEAD)
    public String  AddIdempotent(@RequestBody Order order) {
      log.info(order.toString());
      return "success";

    }
}