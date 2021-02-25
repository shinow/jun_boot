package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import cn.iocoder.springboot.lab39.skywalkingdemo.dataobject.ESUserDO;
import cn.iocoder.springboot.lab39.skywalkingdemo.repository.ESUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private ESUserRepository userRepository;

    @GetMapping("/elasticsearch")
    public String mysql() {
        this.save(1);
        this.findById(1);
        return "elasticsearch";
    }

    public void save(Integer id) {
        ESUserDO user = new ESUserDO();
        user.setId(id);
        user.setUsername("username：" + id);
        user.setPassword("password：" + id);
        user.setCreateTime(new Date());
        userRepository.save(user);
    }

    public ESUserDO findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
