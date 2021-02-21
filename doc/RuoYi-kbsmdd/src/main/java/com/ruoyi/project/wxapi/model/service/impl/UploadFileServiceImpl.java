package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.UploadFile;
import com.ruoyi.project.wxapi.model.mapper.UploadFileMapper;
import com.ruoyi.project.wxapi.model.service.IUploadFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
@Service
public class UploadFileServiceImpl extends ServiceImpl<UploadFileMapper, UploadFile> implements IUploadFileService {

    @Override
    public Integer insertFile(String fileName, String url) {
        return this.getBaseMapper().insertFile(fileName, url);
    }

    @Override
    public UploadFile selectByFileName(String fileName) {
        return this.getBaseMapper().selectByFileName(fileName);
    }

    @Override
    public UploadFile selectById(Integer id) {
        return this.getBaseMapper().selectByMyId(id);
    }
}
