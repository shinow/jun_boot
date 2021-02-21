package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.UploadFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface IUploadFileService extends IService<UploadFile> {

    Integer insertFile(String fileName, String url);

    UploadFile selectByFileName(String fileName);

    UploadFile selectById(Integer logoImageId);
}
