package com.hulk.lockd;


import com.hulk.lockd.annotation.Lockd;
import com.hulk.lockd.annotation.LockdKey;
import org.springframework.stereotype.Service;

/**
 * Created by hulk on 2018/12/29.
 */
@Service
public class TestService {

    @Lockd(acquireTimeout = 1000, expireTime = 6000, keys = {"#param"})
    public String getValue(String param) throws Exception {
      //  if ("sleep".equals(param)) {//线程休眠或者断点阻塞，达到一直占用锁的测试效果
            Thread.sleep(1000*3);
        //}
        return "success";
    }

    @Lockd(keys = {"#userId"})
    public String getValue(String userId,@LockdKey int id)throws Exception{
        Thread.sleep(60*1000);
        return "success";
    }

    @Lockd(keys = {"#user.name","#user.id"})
    public String getValue(User user)throws Exception{
        Thread.sleep(60*1000);
        return "success";
    }

}
