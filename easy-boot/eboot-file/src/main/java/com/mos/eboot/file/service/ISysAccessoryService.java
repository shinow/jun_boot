package com.mos.eboot.file.service;

import com.mos.eboot.platform.entity.SysAccessory;
import com.mos.eboot.tools.result.ResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 小尘哥
 */
@FeignClient("boot-service")
public interface ISysAccessoryService {


    /**
     * 文件上传api接口
     * @param accessory 附件信息
     * @return String
     */
    @RequestMapping("sys/accessory/upload")
    ResultModel<String> upload(SysAccessory accessory);

    /**
     * 根据id获取附件
     * @param id 附件id
     * @return accessory
     */
    @RequestMapping("sys/accessory/open")
    ResultModel<SysAccessory> getById(@RequestParam("id")String id);
}
