package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.UploadFile;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface UploadFileMapper extends BaseMapper<UploadFile> {

    Integer insertFile(String fileName, String url);

    UploadFile selectByFileName(String fileName);

    UploadFile selectByMyId(Integer id);
}
