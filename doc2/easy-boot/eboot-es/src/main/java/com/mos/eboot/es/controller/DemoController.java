package com.mos.eboot.es.controller;

import com.mos.eboot.es.Entity;
import com.mos.eboot.es.service.IDemoService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 朱建柯
 * Date: 2019/2/13
 * Time: 9:38
 */
@RestController
@RequestMapping("/es/demo")
public class DemoController extends BaseController {

    @Resource
    private IDemoService demoService;

    /**
     * 保存
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/save")
    public ResultModel save(@RequestParam("id") long id,
                            @RequestParam("name") String name) {
        Entity entity = new Entity();
        entity.setId(id);
        entity.setName(name);
        return demoService.save(entity);
    }

    /**
     * 搜索
     *
     * @param searchContent
     * @return
     */
    @RequestMapping("/search")
    public ResultModel search(@RequestParam String searchContent) {
        return demoService.search(searchContent);
    }


}
