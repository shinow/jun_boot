package me.zhyd.springboot.restdocs.controller;

import me.zhyd.springboot.restdocs.entity.User;
import me.zhyd.springboot.restdocs.vo.ResponseVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * springboot
 * Created by yadong.zhang on com.zyd.restdocs.controller
 *
 * @Author: yadong.zhang
 * @Date: 2017/9/1 17:16
 */
@RestController
public class RestdocsController {

    @GetMapping(value = "/index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> index() {
        return Collections.singletonMap("message", "Hello World");
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseVO get(@PathVariable("id") int id) {
        return new ResponseVO<User>(200, "Success", getUserById(id));
    }

    @PostMapping(value = "/listUsers")
    public ResponseVO listUsers(User user) {
        return new ResponseVO<List>(200, "Success", getUsersByParam(user));
    }

    @GetMapping(value = "/removeUser/{id}")
    public ResponseVO removeUser(@PathVariable("id") int id) {
        return new ResponseVO(200, "Success", null);
    }

    private List<User> getUsersByParam(User paramUser) {
        List<User> users = getUsers();
        if (paramUser != null) {
            List<User> userList = new ArrayList<>();
            boolean isAgeEquals = false;
            boolean isNameEquals = false;
            for (User user : users) {
                if (paramUser.getAge() != null) {
                    if (user.getAge().equals(paramUser.getAge())) {
                        isAgeEquals = true;
                    }
                } else {
                    isAgeEquals = true;
                }
                if (paramUser.getName() != null) {
                    if (user.getName().equals(paramUser.getName())) {
                        isNameEquals = true;
                    }
                } else {
                    isNameEquals = true;
                }
                if (isAgeEquals && isNameEquals) {
                    userList.add(user);
                }
            }
            return userList;
        }
        return users;
    }

    private User getUserById(int id) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private List<User> getUsers() {
        String[] names = new String[]{"张三", "李四", "王二麻子", "赵一", "钱一", "孙一", "诸葛孔明", "刘备", "张飞", "曹操"};
        int forMaxIndex = new Random().nextInt(50);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < forMaxIndex; i++) {
            users.add(new User(i, names[i % 10], 20, new Date()));
        }
        return users;
    }

}
