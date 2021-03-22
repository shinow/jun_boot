package org.springrain.system.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.Org;
import org.springrain.system.service.IOrgService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 部门信息管理controller
 *
 * @author springrain<Auto generate>
 * @version 2019-07-27 16:10:11
 */
@RestController
@RequestMapping(value = "/api/system/org")
public class OrgController extends BaseController {
    @Resource
    private IOrgService orgService;

    /**
     * 列表数据
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    public ReturnDatas<List<Org>> lists(@RequestBody Page<Org> page) throws Exception {
        ReturnDatas<List<Org>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询
        List<Org> datas = orgService.queryForListByEntity(page.getData(), page);
        // returnObject.setQueryBean(page.getData());
        returnObject.setPage(page);
        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 列表数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<Org>> list() throws Exception {
        ReturnDatas<List<Org>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询

        List<Org> datas = orgService.findTreeOrg();

        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 查看的Json格式数据
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<Org> look(String id) throws Exception {
        ReturnDatas<Org> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            Org org = orgService.findOrgById(id);
            returnObject.setResult(org);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存 操作,返回json格式数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<Org> save(@RequestBody Org org) {
        ReturnDatas<Org> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            orgService.saveOrg(org);

            returnObject.setResult(org);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }

    /**
     * 修改 操作,返回json格式数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<Org> update(@RequestBody Org org) {
        ReturnDatas<Org> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            String id = org.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            orgService.updateOrg(org);

            returnObject.setResult(org);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;

    }

    /**
     * 删除操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<Org> delete(@RequestBody Map<?, ?> map) throws Exception {
        // 执行删除
        try {
            String id = (String) map.get("id");
            if (StringUtils.isNotBlank(id)) {
                orgService.deleteOrgById(id);

                return new ReturnDatas<Org>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas<Org>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas<Org>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

    @RequestMapping(value = "treeselect", method = RequestMethod.GET)
    public ReturnDatas<List<Map<String, Object>>> treeSelect(HttpServletRequest request) {
        ReturnDatas<List<Map<String, Object>>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Map<String, Object>> treeList = null;
        try {
            treeList = orgService.findOrgTreeVoList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("查询失败");
        }
        returnDatas.setResult(treeList);
        return returnDatas;
    }


}
