package com.ruoyi.web.controller.system;

import com.google.common.collect.Lists;
import com.ruoyi.cms.service.IEmailService;
import com.ruoyi.cms.service.SmsService;
import com.ruoyi.cms.util.CmsConstants;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.shiro.token.PhoneToken;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.front.zcool.domain.ZcoolUser;
import com.ruoyi.front.zcool.service.ZcoolUserService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台控制器
 */
@Controller
public class FrontController extends BaseController {

    @Autowired
    private ISysConfigService configService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    SmsService smsService;
    @Autowired
    ISysUserService userService;
    @Autowired
    ZcoolUserService zcoolUserService;
    @Autowired
    IEmailService emailService;

    @Value("${front.register.deptId}")
    public  String  registerUserDeptId;//前台用户注册赋予的默认部门id
    @Value("${front.register.roleId}")
    public  String registerUserRoleId;//前台用户注册赋予的默认角色id


    /**
     * 登录主题
     * @return
     */
    private String getFrontLoginTheme(){
        return configService.selectConfigByKey(CmsConstants.KEY_LOGIN_THEME_FRONT,false);
    }

    /**
     * 前台页面(主题)
     * @return
     */
    private String getFrontPlatform(){
        return configService.selectConfigByKey(CmsConstants.KEY_FRONT_PLATFORM,false);
    }

    /***************************************登录，注册，发送验证码，找回密码相关****************  start  **************************/
    /**
     * 前台登录页面
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        SysUser user = ShiroUtils.getSysUser();
        if(user!=null){
            return "redirect:/front/index";//前台首页
        }
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/login";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/login";//页面在ruoyi-login-theme模块下的templates/login/theme/front文件夹下
        }
    }

    /**
     * 前台登录页面iframe
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/login/frame")
    public String loginFrame(HttpServletRequest request, HttpServletResponse response){
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/login_frame";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/login_frame";
        }
    }

    /**
     *  短信登录发送手机验证码
     * @param request
     * @return
     */
    @GetMapping("/login/sms/send")
    @ResponseBody
    public AjaxResult frontSendSms(HttpServletRequest request, HttpSession session)
    {
        String phone =request.getParameter("phone");
        if(StringUtils.isNotEmpty(phone)){
            Map<String,String> params=new HashMap<>();
            TzCodeUtil util=new TzCodeUtil();
            String code =util.createCode();
            session.setAttribute(phone, code);
            params.put("#code#",code);
            AjaxResult result = smsService.sendByTemplate(CmsConstants.KEY_USER_SMS_LOGIN,phone,params);
            if(result.isSuccess()){
                return AjaxResult.success("验证码发送成功!");
            }else{
                return AjaxResult.error("验证码发送失败!");
            }
        }else{
            return AjaxResult.error("手机号不能为空!");
        }

    }

    /**
     * 前台用户登录账户检测
     * @param username
     * @return
     */
    @PostMapping("/checkAccount")
    @ResponseBody
    public AjaxResult checkAccount(String username)
    {
        boolean b=userService.checkAccountExist(username);
        if(b){
            return AjaxResult.success("账户已经注册!");
        }
        return AjaxResult.error("帐号未注册!");
    }


    /**
     * 前台用户登录手机号检测
     * @param phone
     * @return
     */
    @PostMapping("/checkPhone")
    @ResponseBody
    public AjaxResult checkPhone(String phone)
    {
        boolean b=userService.checkPhoneExist(phone);
        if(b){
            return AjaxResult.success("手机号已经注册!");
        }
        return AjaxResult.error("手机号未注册!");
    }
    /**
     * 检查手机号是否被绑定(phoneNumber,phoneFlag=1)
     * @param phone
     * @return
     */
    @PostMapping("/checkPhoneBind")
    @ResponseBody
    public AjaxResult checkPhoneBind(String phone)
    {
        SysUser user=userService.selectUserByPhoneNumber(phone);
        if(user!=null){
            return AjaxResult.success("手机号已被绑定!");
        }
        return AjaxResult.error("手机号未被绑定!");
    }
    /**
     * 检查邮箱是否被绑定
     * @param email
     * @return
     */
    @PostMapping("/checkEmailBind")
    @ResponseBody
    public AjaxResult checkEmailBind(String email)
    {
        SysUser user=userService.selectUserByEmail(email);
        if(user!=null){
            return AjaxResult.success("邮箱已被绑定!");
        }
        return AjaxResult.error("邮箱未被绑定!");
    }
    /**
     * 是否已经绑定手机(很多操作要求强制必须先绑定手机，所以有这个检查)
     * @return
     */
    @PostMapping("/nav/isBindPhone")
    @ResponseBody
    public AjaxResult isBindPhone()
    {
        SysUser user= ShiroUtils.getSysUser();
        boolean b=userService.isBindPhone(user);
        if(b){
            return AjaxResult.success("已经绑定手机号!");
        }
        return AjaxResult.error("未绑定手机号!");
    }
    @GetMapping("/nav/getUserInfo")
    @ResponseBody
    public AjaxResult getUserInfo(ModelMap mmap)
    {
        SysUser user= ShiroUtils.getSysUser();
        if(user!=null){
            Map data=new HashMap();
            data.put("pageUrl","/u/"+user.getUserId());
            data.put("avatar",getAvatarPath(user));
            data.put("username",user.getUserName());
            data.put("memberType","0");//个人用户
            data.put("uid",user.getUserId().toString());
            return AjaxResult.success("获取用户信息成功!",data);
        }
        return AjaxResult.error("获取用户信息失败!");
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
    /**
     * 前台用户手机短信登录
     * @param phone
     * @param code
     * @param rememberMe
     * @return
     */
    @PostMapping("/login/sms")
    @ResponseBody
    public AjaxResult frontLoginSms(String phone, String code, Boolean rememberMe, HttpSession session)
    {
        // 根据phone从session中取出发送的短信验证码，并与用户输入的验证码比较
        String sessionCode = (String) session.getAttribute(phone);
        if(StringUtils.isEmpty(sessionCode)){
            return AjaxResult.error("短信登录请先发送验证码!");
        }
        if(!sessionCode.equals(code)){
            return AjaxResult.error("验证码错误!");
        }
        PhoneToken token = new PhoneToken(phone);

        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            //ServletUtils.setSmsLoginCookieFront(phone, rememberMe);

            SysUser user = userService.selectUserByLoginName(phone);

            if (user == null) {
                user=userService.selectUserByPhoneNumber(phone);
                if (user == null) {
                    throw new BusinessException("手机号未注册!");
                }
            }
            ServletUtils.setCookieUid(user.getUserId().toString());
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "短信登录失败";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }
    /**
     * 前台用户登录
     * @param username
     * @param password
     * @param rememberMe
     * @return
     */
    @PostMapping("/login/front")
    @ResponseBody
    public AjaxResult frontLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            //ServletUtils.setLoginCookieFront(username, password, rememberMe);
            SysUser user= ShiroUtils.getSysUser();
            ServletUtils.setCookieUid(user.getUserId().toString());
            return success();
        }
        catch (Exception e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    /**
     *  前台首页
     * @param mmap
     * @return
     */
    @GetMapping("/front/index")
    public String index(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/index";
        }else{
            return "front/"+frontPlatform+"/index";
        }
    }

    /**
     * 注册跳转
     * @param modelMap
     * @param request
     * @return
     */
    @GetMapping("/register")
    public String register(ModelMap modelMap, HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        String u=request.getParameter("u");//链接携带的推荐人用户
        if(StringUtils.isNotEmpty(u)){
            modelMap.addAttribute("invite_user_id",u);
        }
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/register";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/register";
        }
    }
    /**
     * 前台注册用户
     * @param request
     * @param session
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    @Transactional
    public AjaxResult registUser(HttpServletRequest request, HttpSession session){
        String phone=request.getParameter("phone");
        String code=request.getParameter("code");
        String password=request.getParameter("password");

        if(StringUtils.isEmpty(phone)){
            return AjaxResult.error("手机号不能为空!");
        }
        if(StringUtils.isEmpty(password)){
            return AjaxResult.error("密码不能为空!");
        }

        String sessionCode=(String)session.getAttribute(phone);
        if(StringUtils.isEmpty(sessionCode)){
            return AjaxResult.error("请先发送验证码!");
        }
        if(!code.equals(sessionCode)){
            return AjaxResult.error("验证码不正确!");
        }

        SysUser user=new SysUser();
        user.setLoginName(phone);
        user.setUserName(phone);
        user.setPhonenumber(phone);
        user.setPhoneFlag(1);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), password, user.getSalt()));
        user.setLoginIp(IpUtils.getIpAddr(request));

        user.setSex("1");
        user.setStatus("0");
        user.setDelFlag("0");
        Long[] roleIds={Long.valueOf(registerUserRoleId)};//前台用户注册赋予注册用户的角色和部门
        user.setRoleIds(roleIds);
        user.setDeptId(Long.valueOf(registerUserDeptId));
        int n = userService.insertUser(user);
        if(n>0){
            session.removeAttribute(phone);
            String invite_user_id=request.getParameter("invite_user_id");
            if(StringUtils.isNotEmpty(invite_user_id)){
                String ip= IpUtils.getIpAddr(request);
                userService.insertUserInvite(user.getLoginName(),invite_user_id,ip);//插入用户邀请记录表
            }
            return  AjaxResult.success("注册成功!");
        }else{
            return  AjaxResult.error("注册失败!");
        }
    }

    /**
     * 用户注册发送手机验证码
     * @param request
     * @param session
     * @return
     */
    @PostMapping("/register/sendSms")
    @ResponseBody
    public AjaxResult sendSms(HttpServletRequest request, HttpSession session){

        String phone=request.getParameter("phone");
        Map<String,String> params=new HashMap<>();
        TzCodeUtil util=new TzCodeUtil();
        String code =util.createCode();
        session.setAttribute(phone, code);
        params.put("#code#",code);
        AjaxResult result = smsService.sendByTemplate(CmsConstants.KEY_USER_REGISTER,phone,params);

        if(result.isSuccess()){
            return AjaxResult.success("验证码发送成功!");
        }else{
            return AjaxResult.error("验证码发送失败!");
        }
    }

    /**
     * 忘记密码(重置密码)跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/resetpwd")
    public String resetpwd(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/resetpwd";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/resetpwd";
        }
    }

    /**
     * 忘记密码(重置密码)发送验证码
     * @return
     */
    @PostMapping("/resetpwd/sendCode")
    @ResponseBody
    public AjaxResult resetpwdSendCode(HttpServletRequest request, HttpSession session)
    {
        String account=request.getParameter("account");
        String type=request.getParameter("type");//验证码类型:email和sms
        if(StringUtils.isEmpty(account)){
            return AjaxResult.error("手机号/邮箱不能为空!");
        }
        if(StringUtils.isEmpty(type)){
            return AjaxResult.error("参数type不能为空!");
        }

        TzCodeUtil util=new TzCodeUtil();
        String code =util.createCode();
        session.setAttribute(account, code);
        Map<String,String> params=new HashMap<>();
        params.put("#code#",code);

        if("email".equals(type)){
            String[] toEmails={account};
            boolean flag=emailService.sendEmailByTemplate(CmsConstants.KEY_USER_RESET_PWD,toEmails,params);
            if(flag){
                return AjaxResult.success("验证码发送成功!");
            }
        }
        if("sms".equals(type)){
            AjaxResult result = smsService.sendByTemplate(CmsConstants.KEY_USER_RESET_PWD_SMS,account,params);
            if(result.isSuccess()){
                return AjaxResult.success("验证码发送成功!");
            }
        }
        return AjaxResult.error("验证码发送失败!");
    }

    /**
     * 找回密码
     * @param request
     * @param session
     * @return
     */
    @PostMapping("/resetpwd")
    @ResponseBody
    public AjaxResult resetpwdPost(HttpServletRequest request, HttpSession session){
        String account=request.getParameter("account");
        String code=request.getParameter("code");//验证码
        String password=request.getParameter("password");
        if(StringUtils.isEmpty(account)){
            return AjaxResult.error("手机号/邮箱不能为空!");
        }
        if(StringUtils.isEmpty(password)){
            return AjaxResult.error("密码不能为空!");
        }
        String sessionCode=(String)session.getAttribute(account);
        if(StringUtils.isEmpty(sessionCode)){
            return AjaxResult.error("请先发送验证码!");
        }
        if(!code.equals(sessionCode)){
            return AjaxResult.error("验证码不正确!");
        }
        SysUser user=userService.selectUserByLoginName(account);
        if(user==null){
            user=userService.selectUserByPhoneNumber(account);
        }
        if(user==null){
            return AjaxResult.error("手机号/邮箱未注册!");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), password, user.getSalt()));
        int n =userService.resetUserPwd(user);
        if(n>0){
            session.removeAttribute(account);
            return AjaxResult.success("密码重置成功!");
        }else{
            return AjaxResult.error("密码重置失败!");
        }
    }

    /**
     * 修改手机跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/modifyphone_view")
    public String modifyphone_view(HttpServletRequest request, ModelMap modelMap)
    {

        String type=request.getParameter("type");

        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());
            modelMap.addAttribute("phone",user.getPhonenumber());
        }
        if("2".equals(type)){
            modelMap.addAttribute("title","修改绑定手机");
            modelMap.addAttribute("type","2");
            modelMap.addAttribute("oldephone",user.getPhonenumber());
        }else{
            modelMap.addAttribute("title","绑定手机");
            modelMap.addAttribute("type","1");
        }
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/modifyphone_view";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/modifyphone_view";
        }
    }
    /**
     * 修改手机-发送验证码
     * @return
     */
    @PostMapping("/modifyPhone/sendCode")
    @ResponseBody
    public AjaxResult modifyPhoneSendCode(HttpServletRequest request, HttpSession session)
    {
        String phone=request.getParameter("phone");
        String type=request.getParameter("type");
        if(StringUtils.isEmpty(phone)){
            return AjaxResult.error("手机号不能为空!");
        }
        TzCodeUtil util=new TzCodeUtil();
        String code =util.createCode();
        session.setAttribute(phone, code);
        Map<String,String> params=new HashMap<>();
        params.put("#code#",code);
        AjaxResult result;
        if("2".equals(type)){
            result = smsService.sendByTemplate(CmsConstants.KEY_USER_MODIFY_PHONE,phone,params);
        }else{
            result = smsService.sendByTemplate(CmsConstants.KEY_USER_BIND_PHONE,phone,params);
        }
        if(result.isSuccess()){
            return AjaxResult.success("验证码发送成功!");
        }
        return AjaxResult.error("验证码发送失败!");
    }
    @PostMapping("/modifyPhone")
    @ResponseBody
    public AjaxResult modifyPhone(HttpServletRequest request, HttpSession session){
        String phone=request.getParameter("phone");
        String code=request.getParameter("code");//验证码
        String type=request.getParameter("type");

        if(StringUtils.isEmpty(phone)){
            return AjaxResult.error("2".equals(type)?"新手机号不能为空!":"手机号不能为空!");
        }

        String sessionCode=(String)session.getAttribute(phone);
        if(StringUtils.isEmpty(sessionCode)){
            return AjaxResult.error("请先发送验证码!");
        }
        if(!code.equals(sessionCode)){
            return AjaxResult.error("验证码不正确!");
        }
        SysUser user= ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());

        user.setPhonenumber(phone);
        user.setPhoneFlag(1);
        int n =userService.updateUserInfo(user);
        if(n>0){
            session.removeAttribute(phone);
            return AjaxResult.success("2".equals(type)?"修改绑定手机成功!":"绑定手机成功!");
        }else{
            return AjaxResult.error("2".equals(type)?"修改绑定手机失败!":"绑定手机失败!");
        }
    }


    /**
     * 修改手机跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/modifypwd_view")
    public String modifypwd_view(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());
            modelMap.addAttribute("phone",user.getPhonenumber());
        }
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/modifypwd_view";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/modifypwd_view";
        }
    }
    /**
     * 修改密码
     * @param request
     * @param session
     * @return
     */
    @PostMapping("/modifyPwd")
    @ResponseBody
    public AjaxResult modifyPwd(HttpServletRequest request, HttpSession session){
        String oldpwd=request.getParameter("oldpwd");
        String password=request.getParameter("password");
        if(StringUtils.isEmpty(oldpwd)){
            return AjaxResult.error("旧密码不能为空!");
        }
        if(StringUtils.isEmpty(password)){
            return AjaxResult.error("新密码不能为空!");
        }

        SysUser user = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(password) && passwordService.matches(user, oldpwd))
        {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), password, user.getSalt()));
            if (userService.resetUserPwd(user) > 0)
            {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
                return AjaxResult.success("密码修改成功!");
            }
            return AjaxResult.error("修改密码失败!");
        }
        else
        {
            return error("修改密码失败，旧密码错误");
        }
    }
    /**
     * 身份证校验
     * @param modelMap
     * @return
     */
    @GetMapping("/idcard_view")
    public String idcard_view(HttpServletRequest request, ModelMap modelMap)
    {

        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());
            modelMap.addAttribute("phone",user.getPhonenumber());
        }

        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/idcard_view";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/idcard_view";
        }
    }

    /**
     * 绑定或修改邮箱跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/modifyemail_view")
    public String modifyemail_view(HttpServletRequest request, ModelMap modelMap)
    {
        String type=request.getParameter("type");

        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());
            modelMap.addAttribute("phone",user.getPhonenumber());
        }
        if("2".equals(type)){
            modelMap.addAttribute("title","修改邮箱账号");
            modelMap.addAttribute("type","2");
            modelMap.addAttribute("oldemail",user.getEmail());
        }else{
            modelMap.addAttribute("title","绑定邮箱账号");
            modelMap.addAttribute("type","1");
        }

        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/modifyemail_view";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/modifyemail_view";
        }
    }
    /**
     * 修改邮箱-发送验证码
     * @return
     */
    @PostMapping("/modifyEmail/sendCode")
    @ResponseBody
    public AjaxResult modifyEmailSendCode(HttpServletRequest request, HttpSession session)
    {
        String email=request.getParameter("email");
        String type=request.getParameter("type");//1:绑定邮箱  2：修改邮箱
        if(StringUtils.isEmpty(email)){
            return AjaxResult.error("邮箱不能为空!");
        }

        TzCodeUtil util=new TzCodeUtil();
        String code =util.createCode();
        session.setAttribute(email, code);
        Map<String,String> params=new HashMap<>();
        params.put("#code#",code);
        boolean flag=false;
        String[] toEmails={email};
        if("2".equals(type)){
            flag=emailService.sendEmailByTemplate(CmsConstants.KEY_USER_MODIFY_EMAIL,toEmails,params);
        }else{
            flag=emailService.sendEmailByTemplate(CmsConstants.KEY_USER_BIND_EMAIL,toEmails,params);
        }
        if(flag){
            return AjaxResult.success("验证码发送成功!");
        }
        return AjaxResult.error("验证码发送失败!");
    }
    @PostMapping("/modifyEmail")
    @ResponseBody
    public AjaxResult modifyEmail(HttpServletRequest request, HttpSession session){
        String email=request.getParameter("email");
        String code=request.getParameter("code");//验证码
        String type=request.getParameter("type");
        if(StringUtils.isEmpty(email)){
            return AjaxResult.error("2".equals(type)?"新邮箱不能为空!":"邮箱不能为空!");
        }

        String sessionCode=(String)session.getAttribute(email);
        if(StringUtils.isEmpty(sessionCode)){
            return AjaxResult.error("请先发送验证码!");
        }
        if(!code.equals(sessionCode)){
            return AjaxResult.error("验证码不正确!");
        }
        SysUser user= ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());

        user.setEmail(email);
        user.setEmailFlag(1);
        int n =userService.updateUserInfo(user);
        if(n>0){
            session.removeAttribute(email);
            return AjaxResult.success("2".equals(type)?"邮箱修改成功!":"邮箱绑定成功!");
        }else{
            return AjaxResult.error("2".equals(type)?"邮箱修改失败!":"邮箱绑定失败!");
        }
    }


    /**
     * 解绑手机号跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/unbindphone_view")
    public String unbindphone_view(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());
            modelMap.addAttribute("phone",user.getPhonenumber());
        }
        String frontLoginTheme=getFrontLoginTheme();
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/unbindphone_view";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/unbindphone_view";
        }
    }
    /**
     * 解绑手机-发送验证码
     * @return
     */
    @PostMapping("/unbindPhone/sendCode")
    @ResponseBody
    public AjaxResult unbindPhoneSendCode(HttpServletRequest request, HttpSession session)
    {
        SysUser user= ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        String phone=user.getPhonenumber();
        TzCodeUtil util=new TzCodeUtil();
        String code =util.createCode();
        session.setAttribute(phone, code);
        Map<String,String> params=new HashMap<>();
        params.put("#code#",code);
        AjaxResult result=smsService.sendByTemplate(CmsConstants.KEY_USER_UNBIND_PHONE,phone,params);

        if(result.isSuccess()){
            return AjaxResult.success("验证码发送成功!");
        }
        return AjaxResult.error("验证码发送失败!");
    }

    @PostMapping("/unbindPhone")
    @ResponseBody
    public AjaxResult unbindPhone(HttpServletRequest request, HttpSession session){

        String code=request.getParameter("code");//验证码

        SysUser user= ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        String phone=user.getPhonenumber();

        String sessionCode=(String)session.getAttribute(phone);
        if(StringUtils.isEmpty(sessionCode)){
            return AjaxResult.error("请先发送验证码!");
        }
        if(!code.equals(sessionCode)){
            return AjaxResult.error("验证码不正确!");
        }

        user.setPhonenumber("");
        user.setPhoneFlag(0);
        int n =userService.updateUserInfo(user);
        if(n>0){
            session.removeAttribute(phone);
            return AjaxResult.success("解绑手机成功!");
        }else{
            return AjaxResult.error("解绑手机失败!");
        }
    }
    /**
     * 个人设置跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/setting")
    public String setting(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){

            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());

            modelMap.addAttribute("email",user.getEmail());
            modelMap.addAttribute("phone",user.getPhonenumber());

            modelMap.addAttribute("avatar",getAvatarPath(user));
            modelMap.addAttribute("createTime", DateUtils.dateTime(user.getCreateTime()));
            modelMap.addAttribute("sex",user.getSex());
            modelMap.addAttribute("description",user.getDescription());
            ZcoolUser userExtend=zcoolUserService.selectByUserId(user.getUserId());
            if(userExtend!=null){
                modelMap.addAttribute("user",userExtend);
                //学校highSchool
                String highSchool=userExtend.getHighSchool();
                if(highSchool.contains("#")){
                    String[] highSchoolArr=highSchool.split("#");
                    modelMap.addAttribute("highSchool",highSchoolArr[0]);
                    modelMap.addAttribute("highSchoolId",highSchoolArr[1]);
                }
                String homeCity=userExtend.getHomeCity();
                String nowCity=userExtend.getNowCity();
                if(StringUtils.isNotEmpty(homeCity)){
                    String[] homeCityArr=homeCity.split("#");
                    if(homeCityArr!=null&&homeCityArr.length>=3){
                        modelMap.addAttribute("homeProvinceValue",homeCityArr[0]);
                        modelMap.addAttribute("homeCityValue",homeCityArr[1]);
                        modelMap.addAttribute("homeDistrictValue",homeCityArr[2]);
                    }
                }
                if(StringUtils.isNotEmpty(homeCity)){
                    String[] nowCityArr=nowCity.split("#");
                    if(nowCityArr!=null&&nowCityArr.length>=3){
                        modelMap.addAttribute("nowProvinceValue",nowCityArr[0]);
                        modelMap.addAttribute("nowCityValue",nowCityArr[1]);
                        modelMap.addAttribute("nowDistrictValue",nowCityArr[2]);
                    }
                }
                //标签
                String str=userExtend.getLabel();
                String[] arr=str.split("#");
                List<String> tags= Lists.newArrayList();
                for(String s:arr){
                    if(StringUtils.isNotEmpty(s)){
                        tags.add(s);
                    }
                }
                modelMap.addAttribute("tags",tags);
            }
        }
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/setting";
        }else{
            return "front/"+frontPlatform+"/setting";
        }
    }
    @PostMapping("/setting/avatar/upload")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);

                return AjaxResult.success("头像上成功!",avatar);
            }
            return error();
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
    @PostMapping("/setting/avatar")
    @ResponseBody
    public AjaxResult avatar(String avatarPath)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        try
        {
            if (StringUtils.isNotEmpty(avatarPath))
            {

                currentUser.setAvatar(avatarPath);
                if (userService.updateUserInfo(currentUser) > 0)
                {
                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return success("头像保存成功!");
                }
            }
            return error("头像保存成功失败!参数不能为空!");
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
    @PostMapping("/setting/basic")
    @ResponseBody
    public AjaxResult basic(HttpServletRequest request)
    {
        String sex=request.getParameter("sex");
        String homeCity=request.getParameter("homeCity");
        String nowCity=request.getParameter("nowCity");
        String job=request.getParameter("job");
        String description=request.getParameter("description");
        SysUser user= ShiroUtils.getSysUser();
        user.setSex(sex);
        user.setDescription(description);
        userService.updateUserInfo(user);
        ZcoolUser zcoolUser=zcoolUserService.selectByUserId(user.getUserId());
        zcoolUser.setJob(job);
        zcoolUser.setHomeCity(homeCity);
        zcoolUser.setNowCity(nowCity);
        zcoolUserService.updateUserInfo(zcoolUser);
        return AjaxResult.success("保存成功!");
    }
    @PostMapping("/setting/detail")
    @ResponseBody
    public AjaxResult detail(HttpServletRequest request)
    {
        String school=request.getParameter("school");
        String height=request.getParameter("height");
        String weight=request.getParameter("weight");
        String education=request.getParameter("education");
        String minzhu=request.getParameter("minzhu");
        String xuexing=request.getParameter("xuexing");
        String marriage=request.getParameter("marriage");
        String salary=request.getParameter("salary");
        String children=request.getParameter("children");
        SysUser user= ShiroUtils.getSysUser();
        ZcoolUser zcoolUser=zcoolUserService.selectByUserId(user.getUserId());
        zcoolUser.setHighSchool(school);
        if(StringUtils.isNotEmpty(height)){
            zcoolUser.setHeight(Integer.valueOf(height));
        }
        if(StringUtils.isNotEmpty(weight)){
            zcoolUser.setWeight(Integer.valueOf(weight));
        }

        zcoolUser.setEducation(education);
        if(StringUtils.isNotEmpty(minzhu)){
            zcoolUser.setMinzhu(Integer.valueOf(minzhu));
        }


        if(StringUtils.isNotEmpty(marriage)){
            zcoolUser.setMarriage(Integer.valueOf(marriage));
        }

        if(StringUtils.isNotEmpty(salary)){
            zcoolUser.setSalary(Integer.valueOf(salary));
        }

        zcoolUser.setChildren(Integer.valueOf(children));
        zcoolUserService.updateUserInfo(zcoolUser);
        return AjaxResult.success("保存成功!");
    }
    @PostMapping("/setting/contact")
    @ResponseBody
    public AjaxResult contact(HttpServletRequest request)
    {
        String qq=request.getParameter("qq");
        String qqPrivate=request.getParameter("qqPrivate");
        String wechat=request.getParameter("wechat");
        String wechatPrivate=request.getParameter("wechatPrivate");
        SysUser user= ShiroUtils.getSysUser();
        ZcoolUser zcoolUser=zcoolUserService.selectByUserId(user.getUserId());
        zcoolUser.setQq(qq);
        zcoolUser.setQqPrivate(Integer.valueOf(qqPrivate));
        zcoolUser.setWx(wechat);
        zcoolUser.setWxPrivate(Integer.valueOf(wechatPrivate));
        zcoolUserService.updateUserInfo(zcoolUser);
        return AjaxResult.success("保存成功!");
    }

    @PostMapping("/setting/addTag")
    @ResponseBody
    public AjaxResult addTag(HttpServletRequest request)
    {
        String tag=request.getParameter("tag");
        SysUser user= ShiroUtils.getSysUser();
        ZcoolUser zcoolUser=zcoolUserService.selectByUserId(user.getUserId());
        String str=zcoolUser.getLabel();
        str+=(str.endsWith("#")?"":"#")+tag;
        zcoolUser.setLabel(str);
        zcoolUserService.updateUserInfo(zcoolUser);
        return AjaxResult.success("添加标签成功!");
    }

    @PostMapping("/setting/delTag")
    @ResponseBody
    public AjaxResult delTag(HttpServletRequest request)
    {
        String tag=request.getParameter("tag");
        SysUser user= ShiroUtils.getSysUser();
        ZcoolUser zcoolUser=zcoolUserService.selectByUserId(user.getUserId());
        String str=zcoolUser.getLabel();
        String[] arr=str.split("#");
        String newStr="";
        for(String s:arr){
            if(!s.equals(tag)){
                newStr+=s+"#";
            }
        }
        zcoolUser.setLabel(newStr);
        zcoolUserService.updateUserInfo(zcoolUser);
        return AjaxResult.success("删除标签成功!");
    }


    /**
     * 账号安全跳转
     * @param modelMap
     * @return
     */
    @GetMapping("/accountSafe")
    public String accountSafe(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        if(user!=null){
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("userId",user.getUserId().toString());
            modelMap.addAttribute("userName",user.getUserName());

            modelMap.addAttribute("email",user.getEmail());
            modelMap.addAttribute("phone",user.getPhonenumber());

            modelMap.addAttribute("emailFlag",user.getEmailFlag().toString());//邮箱绑定标志 1已绑定 0未绑定
            modelMap.addAttribute("phoneFlag",user.getPhoneFlag().toString());//手机绑定标志 1已绑定 0未绑定
        }
        String frontLoginTheme=getFrontLoginTheme();
        if("zcool".equals(frontLoginTheme)){
            ZcoolUser zcoolUser=zcoolUserService.selectByUserId(user.getUserId());
            if(zcoolUser!=null){
                modelMap.addAttribute("id_card",zcoolUser.getId_card());
            }
        }
        if(StringUtils.isEmpty(frontLoginTheme)){
            return "login/theme/front/zcool/accountSafe";
        }else{
            return "login/theme/front/"+frontLoginTheme+"/accountSafe";
        }
    }

    /***************************************登录，注册，发送验证码，找回密码相关****************  end  **************************/

    /***************************************关于我们，用户协议，联系我们，帮助等协议相关****************  start  **************************/

    /**
     * 关于我们
     * @return
     */
    @GetMapping("/aboutUs")
    public String aboutUs(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/aboutUs";
        }else{
            return "front/"+frontPlatform+"/pages/aboutUs";
        }
    }
    /**
     * 用户协议
     * @return
     */
    @GetMapping("/userAgreement")
    public String userAgreement(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/userAgreement";
        }else{
            return "front/"+frontPlatform+"/pages/userAgreement";
        }
    }
    /**
     * 关于我们
     * @return
     */
    @GetMapping("/contactUs")
    public String contactUs(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/contactUs";
        }else{
            return "front/"+frontPlatform+"/pages/contactUs";
        }
    }

    /**
     * 帮助中心
     * @return
     */
    @GetMapping("/help")
    public String help(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/help";
        }else{
            return "front/"+frontPlatform+"/pages/help";
        }
    }

    /**
     * 侵权申诉
     * @return
     */
    @GetMapping("/violationClaim")
    public String violationClaim(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/violationClaim";
        }else{
            return "front/"+frontPlatform+"/pages/violationClaim";
        }
    }
    /**
     * 隐私政策
     * @return
     */
    @GetMapping("/policy")
    public String policy(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/policy";
        }else{
            return "front/"+frontPlatform+"/pages/policy";
        }
    }

    /**
     * 站点规则
     * @return
     */
    @GetMapping("/rule")
    public String rule(){
        String frontPlatform=getFrontPlatform();
        if(StringUtils.isEmpty(frontPlatform)){
            return "front/zcool/pages/rule";
        }else{
            return "front/"+frontPlatform+"/pages/rule";
        }
    }

    /***************************************关于我们，用户协议，联系我们，帮助等协议相关****************  end  **************************/
}
