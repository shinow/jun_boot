package org.springrain.weixin.service;

import org.springrain.frame.service.IBaseService;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.entity.WxMenu;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-02-06 17:23:12
 */
@RpcServiceAnnotation
public interface IWxMenuService extends IBaseService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    WxMenu findWxMenuById(String id) throws Exception;

    /**
     * 查找所有的顶级菜单
     *
     * @param siteId
     * @return
     * @throws Exception
     */
    List<WxMenu> findParentMenuList(String siteId) throws Exception;

    /**
     * 根据父级ID查找二级菜单
     *
     * @return
     * @throws Exception
     */
    List<WxMenu> findChildMenuListByPid(String pid) throws Exception;


}
