package com.ruoyi.cms.controller;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *百度图片上传
 *@author 林钊全
 *@date 2020/9/5
 */
@Controller
@RequestMapping("/ajax/libs/ueditor/1.4.3")
public class UeditorController {

    /**
     *这个接口对应 ueditor.config.js 中的，服务器统一请求接口路径
     *
     * */
    @GetMapping("/init/config")
    public String initConfig(){
        return "redirect:/ajax/libs/ueditor/1.4.3/config.json";
    }

    @PostMapping("/init/config")
    @ResponseBody
    public String upfile(String action,MultipartFile upfile,HttpServletRequest request){
        //图片
        String uploadimage="uploadimage";
        //视频
        String uploadscrawl="uploadvideo";
        if (action.equals(uploadimage)){
            // 图片上传文件
            try {
                String pathUrl = FileUploadUtils.upload(Global.getProfile(), upfile);
                // 上传成功后返回的json数据
                /*
                 * {"state": "SUCCESS","original": "Hydrangeas.jpg","size": "595284","title":
                 * "1551927256870045443.jpg","type": ".jpg","url":
                 * "/upload/image/20190307/1551927256870045443.jpg"}
                 */
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("state", "SUCCESS") ;
                map.put("original", upfile.getName()) ;
                map.put("size", upfile.getSize()) ;
                map.put("title", upfile.getName()) ;
                map.put("type", upfile.getContentType()) ;
                map.put("url", pathUrl) ;
                String result = JSON.toJSONString(map);
                System.out.println("json : " + result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (action.equals(uploadscrawl)){
            // 视频上传文件
            try {
                String pathUrl = FileUploadUtils.upload(Global.getProfile(), upfile);
                // 上传成功后返回的json数据
                /*
                *"state": "SUCCESS",
                *"url": "upload/demo.mp4",
                *"title": "demo.mp4",
                *"original": "demo.mp4"
                 */
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("state", "SUCCESS") ;
                map.put("original", upfile.getName()) ;
                map.put("title", upfile.getName()) ;
                map.put("url", pathUrl) ;
                String result = JSON.toJSONString(map);
                System.out.println("json : " + result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传失败";
    }

}


