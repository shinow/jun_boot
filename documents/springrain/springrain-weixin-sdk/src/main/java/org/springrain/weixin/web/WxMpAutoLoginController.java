package org.springrain.weixin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.springrain.weixin.service.IWxMpConfigService;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/mp/mpautologin/{siteId}")
public class WxMpAutoLoginController {
    // @Resource
    // IWxMpService wxMpService;
    @Resource
    IWxMpConfigService wxMpConfigService;

    // @Resource
    // IWxMpUserService wxMpUserService;


    /**
     * 跳转到微信认证页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/oauth2")
    public String oauth2(@PathVariable String siteId) throws Exception {
        //String url = payRequest.getParameter("url");
        //if(StringUtils.isBlank(url)||StringUtils.isBlank(siteId)){
        //	return null;
        //}


        IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);


        //String _url=RequestURLUtils.getBaseURL(payRequest)+"/mp/mpautologin/"+siteId+"/callback?url=" + url;

        //String oauthUrl = wxMpService.oauth2buildAuthorizationUrl(wxmpconfig,_url, WxConsts.OAUTH2_SCOPE_BASE, null);

        //return BaseController.redirect + oauthUrl;
        return "";
    }

    /**
     * 获取微信用户信息（openid）
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/callback")
    public String callback(@PathVariable String siteId) throws Exception {
        //WxMpUser wxMpUser = new WxMpUser();
        //String url = payRequest.getParameter("url");
        //String code = payRequest.getParameter("code");

        //if(StringUtils.isBlank(url)||StringUtils.isBlank(code)||StringUtils.isBlank(siteId)){
        //	return null;
        //}


        IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
        //try {
        // 获取OpenId
        //WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(wxmpconfig, code);
//			 wxMpUser=wxMpService.oauth2getUserInfo(wxmpconfig,accessToken,"zh_CN");
//			WxMpUser wxMpUser = wxMpUserService.userInfo(wxmpconfig, accessToken.getOpenId());
        //payRequest.getSession().setAttribute("openId", accessToken.getOpenId());
        //payRequest.getSession().setAttribute("unionId", accessToken.getUnionId());

        //} catch (WxErrorException e) {
        //logger.error(e.getMessage(),e);
        //}
        //url = StringUtils.replace(url, "---", "&");
        //return BaseController.redirect + url;
        return "";
    }

    /**
     private void autoLogin(HttpServletRequest req, HttpServletResponse rep, User user) {
     ShiroUser shiroUser = new ShiroUser();
     shiroUser.setAccount(user.getAccount());
     shiroUser.setEmail(user.getEmail());
     shiroUser.setId(user.getId());
     shiroUser.setUserName(user.getUserName());
     shiroUser.setSex(user.getSex());
     shiroUser.setUserType(user.getUserType());
     SimplePrincipalCollection principals = new SimplePrincipalCollection(
     shiroUser, GlobalStatic.authorizingRealmName);
     WebSubject.Builder builder = new WebSubject.Builder(req, rep);
     builder.principals(principals);
     builder.authenticated(true);
     WebSubject subject = builder.buildWebSubject();
     ThreadContext.bind(subject);
     }
     **/
}
