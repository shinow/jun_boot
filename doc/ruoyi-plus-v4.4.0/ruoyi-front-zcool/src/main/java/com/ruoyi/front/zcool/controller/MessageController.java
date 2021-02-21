package com.ruoyi.front.zcool.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.front.zcool.domain.Message;
import com.ruoyi.front.zcool.service.MessageService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 通知、评论、私信控制器
 */
@Controller
public class MessageController extends BaseController {

    @Autowired
    ISysUserService userService;

    @Autowired
    MessageService messageService;

    public static String frontLoginTheme="zcool";

    /**
     * 通知
     * @param modelMap
     * @return
     */
    @GetMapping("/messages/notice")
    public String notice(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());
            modelMap.addAttribute("avatar",getAvatarPath(user));
        }

        return "front/"+frontLoginTheme+"/message/notice";
    }

    @PostMapping("/messages/getMessages")
    @ResponseBody
    public TableDataInfo getMessages(ModelMap modelMap, HttpServletRequest request)
    {
        SysUser user=ShiroUtils.getSysUser();
        String msgType=request.getParameter("msgType");
        if("all".equals(msgType)){
            //全部通知，各种类型只去第一条
            startPage();
            List<Message> list=messageService.selectByAllType(user.getUserId().toString());
            //messageService.updateReadFlag(list);
            return getDataTable(list);
        }else {
            startPage();
            List<Message> list=messageService.selectByMsgType(msgType,user.getUserId().toString());
            //messageService.updateReadFlag(list);
            return getDataTable(list);
        }
    }
    @ResponseBody
    @GetMapping("/messages/countMessages")
    public AjaxResult countMessages(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        Map map=messageService.countMessages(user.getUserId().toString());
        return AjaxResult.success(map);
    }
    public String getAvatarPath(SysUser user){
        if(StringUtils.isEmpty(user.getAvatar())){
            if("1".equals(user.getSex())){
                return "/front/zcool/images/boy.png";
            }else{
                return "/front/zcool/images/girl.png";
            }
        }
        return user.getAvatar();
    }
}
