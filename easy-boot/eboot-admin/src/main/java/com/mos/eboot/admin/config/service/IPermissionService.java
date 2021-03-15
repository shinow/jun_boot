package com.mos.eboot.admin.config.service;

import com.mos.eboot.tools.result.ResultModel;

import java.util.List;

/**
 * @author 小尘哥
 */
public interface IPermissionService {

    ResultModel<List<String>> getPermissions(String userId);
}
