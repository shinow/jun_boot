package com.hulk.limitb;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class LimitbTests {

    @Autowired
    OrderController  orderController;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    /**
     * 线程休眠50秒
     *
     * @throws Exception
     */
    @Test
    public void test1()  {
       String id ="1";
        while (true){
            String  resp = orderController.test1(id);
            Assert.assertEquals(resp, id);
        }


    }


}
