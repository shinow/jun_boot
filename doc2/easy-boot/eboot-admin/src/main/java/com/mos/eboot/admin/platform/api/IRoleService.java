package com.mos.eboot.admin.platform.api;

import com.mos.eboot.platform.entity.SysRole;
import com.mos.eboot.tools.result.ResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 小尘哥
 */
@FeignClient("boot-service")
public interface IRoleService {

    /**
     * 根据id获取角色
     *
     * @param id 角色id
     * @return 角色对象
     */
    @RequestMapping(value = "sys/role/get-by-id",method = RequestMethod.GET)
    ResultModel<SysRole> getById(@RequestParam("id") String id);


    /**
     * 根据id删除角色
     *
     * @param id 角色id
     * @return 删除结果
     */
    @RequestMapping(value = "sys/role/del-by-id",method = RequestMethod.POST)
    ResultModel<String> delById(@RequestParam("id") String id);

    /**
     * 根据条件查询所有角色
     *
     * @param sysRole 角色对象
     * @return
     */
    @RequestMapping(value = "sys/role/query-list",method = RequestMethod.POST)
    ResultModel<List<SysRole>> queryList(SysRole sysRole);

    /**
     * 添加或修改角色
     * @param sysRole 角色对象
     * @return
     */
    @RequestMapping(value = "sys/role/save-or-update",method = RequestMethod.POST)
    ResultModel<String> saveOrUpdate(SysRole sysRole);

	/**
	 * 绑定资源
	 * @param id
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "sys/role/bind-resource",method = RequestMethod.POST)
	ResultModel<String> bindResource(@RequestParam(name = "id")String id,String[] ids);
}
