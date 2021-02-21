package com.ruoyi.web.controller.system;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.enums.JwtType;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.JwtUtils;
import com.ruoyi.framework.util.ServletUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
@Api(value="登录接口", description="登录接口")
public class SysLoginController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger(SysLoginController.class);
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录",notes = "jwt登录参数isjwt=true,返回token位于header")
    public AjaxResult ajaxLogin(String username, String password, @RequestParam(defaultValue = "false") Boolean rememberMe, @RequestParam(defaultValue = "false") Boolean isjwt, HttpServletResponse response)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            if (isjwt){
                //jwt登录
                Map<String, Object> jwtUserInfo = ShiroUtils.getJwtUserInfo();
                String jwtUserInfoTokentoken = ShiroUtils.getJwtUserInfoToken();
                response.setHeader("token",jwtUserInfoTokentoken);
                return AjaxResult.jwtResult(JwtType.loginSuccess,jwtUserInfo);
            }
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "/error/unauth";
    }

    //刷新jwt token
    @PostMapping("/jwt/refreshToken")
    @ResponseBody
    @ApiOperation(value = "刷新token",notes = "header带上参数有效token")
    public AjaxResult refreshToken(HttpServletResponse response) {
        String token = ServletUtils.getRequest().getHeader("token");
        SysUser sysUser = ShiroUtils.getSysUserByJwtToken(token);
        try {
            if (sysUser!=null){
                String newToken = JwtUtils.createToken(JSON.toJSONString(sysUser), Global.getJwtOutTime());
                response.setHeader("token",newToken);
                return success();
            }else {
                return error(JwtType.permissionNo);
            }
        } catch (Exception e) {
            logger.error("刷新jwt token");
            return error("系统错误");
        }

    }
}
