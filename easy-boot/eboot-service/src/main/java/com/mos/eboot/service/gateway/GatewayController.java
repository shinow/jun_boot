package com.mos.eboot.service.gateway;

import com.mos.eboot.tools.result.ResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 朱建柯
 * Email: hautxxxyzjk@163.com
 * Date: 2019-03-31
 * Time: 14:50
 * Description:
 */
@RestController
public class GatewayController {

    @RequestMapping("/api/user/demo")
    public ResultModel userDemo() {
        return ResultModel.defaultSuccess("/api/user/demo");
    }

    @RequestMapping("/api/user/demo/1")
    public ResultModel userDemo1() {
        return ResultModel.defaultSuccess("/api/user/demo/1");
    }

    @RequestMapping("/api/admin/demo")
    public ResultModel adminDemo() {
        return ResultModel.defaultSuccess("/api/admin/demo");
    }
}
