package com.ruoyi.web.controller.common;

import cn.hutool.extra.servlet.ServletUtil;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.util.BaiduBosUtils;
import com.ruoyi.framework.util.FileUploadUtils;
import com.ruoyi.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 * @author pmc
 */
@Controller
@Api(value = "通用接口",description = "通用接口")
public class CommonController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 文件上传路径
     */
    public static final String UPLOAD_PATH = "/profile/upload/";

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
        try
        {
            String filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + setFileDownloadHeader(request, realFileName));
            if (Global.isNetFile()){
                InputStream in = FileUploadUtils.downWebFile(fileName);
                ServletUtil.write(response,in);
                if (delete) FileUploadUtils.deleteWebFile(fileName);
            }else{
                FileUtils.writeBytes(filePath, response.getOutputStream());
                if (delete) FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
    /**
     *检查文件是否存在
     * @param fileName 文件名带后缀
     * @param dir 文件目录
     * @return
     */
    @GetMapping("common/hasFile")
    @ResponseBody
    @ApiOperation(value = "检查文件是否存在",notes = "")
    public AjaxResult hasFile(String fileName,String dir)
    {
        try {
            if (Global.isNetFile()) return success(BaiduBosUtils.hasFile(fileName));
            String path = Global.getProfile()+dir+fileName;
            return success(FileUtils.isNotEmpty(new File(path)));
        } catch (Exception e) {
            log.error("", e);
            return error();
        }
    }
/**
 * 通用上传请求
 */
@PostMapping("/common/upload")
@ResponseBody
public AjaxResult uploadFile(MultipartFile file) throws Exception
{
    try
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = serverConfig.getUrl() + UPLOAD_PATH + fileName;
        AjaxResult ajax = AjaxResult.success();
        ajax.put("fileName", fileName);
        ajax.put("url", url);
        return ajax;
    }
    catch (Exception e)
    {
        return AjaxResult.error(e.getMessage());
    }
}
    public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
