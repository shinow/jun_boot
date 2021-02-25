package com.hulk.limitb;



import com.hulk.limitb.annotation.Limitb;
import com.hulk.limitb.annotation.LimitbKey;
import com.hulk.limitb.enums.LimitbStrategy;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class OrderController {



    @Limitb(limitStrategy = LimitbStrategy.LOCAL)
    @GetMapping(value = "/brush/{id}")
    public String  test1(@PathVariable("id") @LimitbKey String id) {
      log.info(id);
      return id;

    }

    @Limitb(keys = {"#order.name","#order.id"},limitStrategy = LimitbStrategy.LOCAL, replenish =2, capacity = 5)
    public String test2(Order order)throws Exception{
       // Thread.sleep(60*1000);
        return "success";
    }
}