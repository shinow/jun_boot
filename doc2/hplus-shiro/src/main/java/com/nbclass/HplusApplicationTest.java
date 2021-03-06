package com.nbclass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nbclass.modules.sys.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HplusApplication.class)
public class HplusApplicationTest {
	
	@Autowired
	private UserService userService;

    @Test
    public void test() throws Exception {
    	
    	System.out.println(userService.getUserByUserId(1L).getUsername());
    }
}