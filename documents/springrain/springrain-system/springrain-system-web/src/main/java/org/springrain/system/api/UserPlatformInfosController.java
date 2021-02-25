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
import org.springrain.system.entity.UserPlatformInfos;
import org.springrain.system.service.IUserPlatformInfosService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-26 17:52:13
 */
@RestController
@RequestMapping(value = "/api/system/userplatforminfos", method = RequestMethod.POST)
public class UserPlatformInfosController extends BaseController {
    @Resource
    private IUserPlatformInfosService userPlatformInfosService;

    /**
     * 列表数据
     *
     * @param userPlatformInfos
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<UserPlatformInfos>> list(UserPlatformInfos userPlatformInfos, Page<?> page)
            throws Exception {
        ReturnDatas<List<UserPlatformInfos>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询
        List<UserPlatformInfos> datas = userPlatformInfosService.queryForListByEntity(userPlatformInfos, page);
        //returnObject.setQueryBean(userPlatformInfos);
        returnObject.setPage(page);
        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 查看的Json格式数据
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<UserPlatformInfos> look(String id) throws Exception {
        ReturnDatas<UserPlatformInfos> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            UserPlatformInfos userPlatformInfos = userPlatformInfosService.findUserPlatformInfosById(id);
            returnObject.setResult(userPlatformInfos);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存 操作,返回json格式数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<UserPlatformInfos> save(@RequestBody UserPlatformInfos userPlatformInfos) {
        ReturnDatas<UserPlatformInfos> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            String id = userPlatformInfos.getId();
            if (StringUtils.isBlank(id)) {
                userPlatformInfos.setId(null);
            }
            userPlatformInfosService.save(userPlatformInfos);
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
    public ReturnDatas<UserPlatformInfos> update(@RequestBody UserPlatformInfos userPlatformInfos) {
        ReturnDatas<UserPlatformInfos> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            String id = userPlatformInfos.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            userPlatformInfosService.update(userPlatformInfos);


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
    public ReturnDatas<UserPlatformInfos> delete(@RequestBody String id) throws Exception {
        // 执行删除
        try {

            if (StringUtils.isNotBlank(id)) {
                userPlatformInfosService.deleteById(id, UserPlatformInfos.class);
                return new ReturnDatas<UserPlatformInfos>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas<UserPlatformInfos>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas<UserPlatformInfos>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

    /**
     * 删除多条记录
     */
    @RequestMapping(value = "/delete/more", method = RequestMethod.POST)
    public ReturnDatas<Object> deleteMore(@RequestBody String[] ids) {

        if (ids == null || ids.length < 1) {
            return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
        }
        try {
            List<String> listIds = Arrays.asList(ids);
            userPlatformInfosService.deleteByIds(listIds, UserPlatformInfos.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
        }
        return new ReturnDatas<Object>(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

    }

}