package com.mos.eboot.admin.config.service.impl;

import com.mos.eboot.admin.config.service.IPermissionService;
import com.mos.eboot.admin.platform.api.ISysPermissionService;
import com.mos.eboot.tools.result.ResultModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("permissionService")
public class PermissionService implements IPermissionService {

    @Resource
    private ISysPermissionService sysPermissionService;

    @Override
    public ResultModel<List<String>> getPermissions(String userId) {
        return sysPermissionService.getPermissions(userId);
    }
}
