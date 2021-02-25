package org.springrain.system.api;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.CaptchaUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.rpc.sessionuser.UserVO;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserRoleMenuService;
import org.springrain.system.service.IUserService;
import org.springrain.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.springrain.weixin.sdk.open.SnsApi;
import org.springrain.weixin.service.IWxMpConfigService;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST)
public class LoginController extends BaseController {

    private static final String redirect_uri = "";
    @Resource
    private IUserService userService;
    @Resource
    private IWxMpConfigService wxMpConfigService;
    @Resource
    private IUserRoleMenuService userRoleMenuService;

    /**
     * 健康检查
     *
     * @return
     */
    @RequestMapping(value = "/checkHealth", method = RequestMethod.GET)
    public ReturnDatas<String> checkHealth() {
        return ReturnDatas.getSuccessReturnDatas();
    }

    /**
     * 生成验证码
     *
     * @param captchaKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    public ReturnDatas<ConcurrentMap<String, String>> getCaptcha(String captchaKey)
            throws Exception {
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_JPEG);

        ReturnDatas<ConcurrentMap<String, String>> returnDatas = ReturnDatas
                .getSuccessReturnDatas();

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            StringBuilder code = new StringBuilder();
            BufferedImage image = CaptchaUtils.genRandomCodeImage(code);

            if (StringUtils.isNotBlank(captchaKey)) {
                userService.evictByKey(GlobalStatic.springranloginCaptchaKey, captchaKey);
            } else {
                captchaKey = SecUtils.getUUID();
            }
            userService.putByCache(GlobalStatic.springranloginCaptchaKey, captchaKey,
                    code.toString());

            ImageIO.write(image, "JPEG", os);
            String imageBase64 = String.format("data:image/jpeg;base64,%s",
                    SecUtils.encoderByBase64(os.toByteArray()));

            ConcurrentMap<String, String> concurrentMap = Maps.newConcurrentMap();
            concurrentMap.put("captchaKey", captchaKey);
            concurrentMap.put("imageBase64", imageBase64);
            returnDatas.setResult(concurrentMap);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

        return returnDatas;
    }

    /**
     * 退出后台登录用户
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/system/logout", method = RequestMethod.POST)
    public ReturnDatas<?> systemLogout() throws Exception {

        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }

        return ReturnDatas.getSuccessReturnDatas();
    }

    /**
     * 获取登陆二维码
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/system/qrcode", method = RequestMethod.POST)
    public ReturnDatas<String> qrcode(@RequestBody Map<String, Object> map) {
        ReturnDatas<String> returnObject = ReturnDatas.getSuccessReturnDatas();
        String appId = (String) map.get("appId");
        final IWxMpConfig config = wxMpConfigService.findWxMpConfigById(appId);
        String url = SnsApi.getQrConnectURL(config, redirect_uri);
        returnObject.setResult(url);
        return returnObject;
    }

//    {
//        "access_token":"ACCESS_TOKEN",
//            "expires_in":7200,
//            "refresh_token":"REFRESH_TOKEN",
//            "openid":"OPENID",
//            "scope":"SCOPE",
//            "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//    }

    @RequestMapping(value = "/system/qrcodeback", method = RequestMethod.POST)
    public ReturnDatas<?> qrcodeback(@RequestBody Map<String, Object> map) {
        ReturnDatas<?> returnObject = ReturnDatas.getSuccessReturnDatas();

        return returnObject;
    }

    /**
     * 处理后台用户登录
     *
     * @param userVO 请求的参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/system/login", method = RequestMethod.POST)
    public ReturnDatas<ConcurrentMap<String, String>> systemLoginPost(@RequestBody UserVO userVO) throws Exception {

        if (StringUtils.isBlank(userVO.getAccount())) {
            return ReturnDatas.getErrorReturnDatas("用户不能为空");
        }

        if (StringUtils.isBlank(userVO.getPassword())) {
            return ReturnDatas.getErrorReturnDatas("密码不能为空");
        }
        
        if(StringUtils.isBlank(userVO.getCaptchaKey())) {
        	return ReturnDatas.getErrorReturnDatas("验证码不能为空");
        }
        
        Cache captchaCache = userService.getCache(GlobalStatic.springranloginCaptchaKey);
        ValueWrapper captchaKey = captchaCache.get(GlobalStatic.projectKeyPrefix + userVO.getCaptchaKey());
        if(!StringUtils.equals(captchaKey.get().toString(), userVO.getCaptcha())) {
        	return ReturnDatas.getErrorReturnDatas("验证码错误");
        }
        
        
        ConcurrentMap<String, String> resutltMap = Maps.newConcurrentMap();
        // 处理密码错误缓存
        String errorLogincountKey = userVO.getAccount() + "_errorlogincount";
        Integer errorLogincount = userService.getByCache(GlobalStatic.springrainloginCacheKey,
                errorLogincountKey, Integer.class);

        if (errorLogincount != null && errorLogincount >= GlobalStatic.ERROR_LOGIN_COUNT) {// 密码连续错误10次以上

            String errorMessage = "密码连续错误超过" + GlobalStatic.ERROR_LOGIN_COUNT + "次,账号被锁定,请"
                    + GlobalStatic.ERROR_LOGIN_LOCK_MINUTE + "分钟之后再尝试登录!";
            Long endDateLong = userService.getByCache(GlobalStatic.springrainloginCacheKey,
                    userVO.getAccount() + "_endDateLong", Long.class);
            Long now = System.currentTimeMillis() / 1000;// 秒

            if (endDateLong == null) {
                endDateLong = now + GlobalStatic.ERROR_LOGIN_LOCK_MINUTE * 60;// 秒
                userService.putByCache(GlobalStatic.springrainloginCacheKey,
                        userVO.getAccount() + "_endDateLong", endDateLong);
                return ReturnDatas.getErrorReturnDatas(errorMessage);
            } else if (now > endDateLong) {// 过了失效时间
                userService.evictByKey(GlobalStatic.springrainloginCacheKey, errorLogincountKey);
                userService.evictByKey(GlobalStatic.springrainloginCacheKey,
                        userVO.getAccount() + "_endDateLong");

            } else {
                return ReturnDatas.getErrorReturnDatas(errorMessage);
            }
        }

        User user = userService.findLoginUser(userVO.getAccount(),
                SecUtils.encoderByMd5With32Bit(userVO.getPassword()), userVO.getUserType());

        if (user == null) {// 登录失败
            if (errorLogincount == null) {
                errorLogincount = 0;
            }
            errorLogincount = errorLogincount + 1;
            userService.putByCache(GlobalStatic.springrainloginCacheKey, errorLogincountKey,
                    errorLogincount);

            return ReturnDatas.getErrorReturnDatas("账号或密码错误");
        }

        String jwtToken = userService.wrapJwtTokenByUser(user);
        resutltMap.put(GlobalStatic.jwtTokenKey, jwtToken);

        // 设置 权限菜单数据
        // List<Menu> listMenu =
        // userRoleMenuService.findMenuTreeByUsreId(user.getId());
        // List<Map<String,Object>> listMap=new ArrayList<>();

        // List<String> roles = new ArrayList<>();

        // roles.add("admin");

        // 包装成Vue使用的树形结构
        // userRoleMenuService.wrapVueMenu(listMenu,listMap);

        // resutltMap.put("menus",listMap);
        // resutltMap.put("roles",roles);

        ReturnDatas<ConcurrentMap<String, String>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(resutltMap);

        // 登录成功,清空错误次数
        userService.evictByKey(GlobalStatic.springrainloginCacheKey, errorLogincountKey);
        userService.evictByKey(GlobalStatic.springrainloginCacheKey,
                userVO.getAccount() + "_endDateLong");

        return returnDatas;
    }

    /**
     * 处理前台用户登录
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ReturnDatas<?> userLoginPost(@RequestBody UserVO userVO) throws Exception {
        return systemLoginPost(userVO);
    }

    /**
     * 员工登录地址
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/login", method = RequestMethod.POST)
    public ReturnDatas<?> workLoginPost(@RequestBody UserVO userVO) throws Exception {
        return systemLoginPost(userVO);
    }

}
