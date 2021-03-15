package com.mos.eboot.file.controller;

import com.mos.eboot.file.config.MyPropsConstants;
import com.mos.eboot.platform.entity.SysAccessory;
import com.mos.eboot.file.service.ISysAccessoryService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.util.Constants;
import com.mos.eboot.tools.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author 小尘哥
 */
@RestController
@RequestMapping("accessory")
@CrossOrigin(origins = "*", maxAge = 3600)
@PropertySource("classpath:constants.properties")
public class FileController extends BaseController {

    @Resource
    private ISysAccessoryService sysAccessoryService;

    @Autowired
	private MyPropsConstants myPropsConstants;

    @RequestMapping("upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file, String userId, @RequestParam("category") String category) {

        String originalName = file.getOriginalFilename();
        long fileSize = file.getSize();
        String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
        String tmpName = genTmpFileName();
        String path = File.separator + category + File.separator + tmpName + "." + ext;
        try {
            File targetFile = new File(myPropsConstants.getUploadBasePath() +  path);
            if (!targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdirs();
            }
            file.transferTo(targetFile);
            SysAccessory accessory = getAcc(userId,originalName,Integer.parseInt(String.valueOf(fileSize)),path,category);
            return sysAccessoryService.upload(accessory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultModel.defaultError(null);
    }

    @RequestMapping("open")
    public void upload(@RequestParam("id") String id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        SysAccessory accessory = sysAccessoryService.getById(id).getData();
        if (accessory == null){
            return;
        }
        String path = myPropsConstants.getImageViewBasePath() + accessory.getPath();
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private SysAccessory getAcc(String userId, String originalName, Integer fileSize, String path, String category) {
        SysAccessory accessory = new SysAccessory();
        accessory.setCreateTime(new Date());
        accessory.setCreateUser(userId);
        accessory.setIsDel(Constants.NEGATIVE);
        accessory.setOriginalName(originalName);
        accessory.setFileSize(fileSize);
        accessory.setPath(path);
        accessory.setCategory(category);
        return accessory;
    }

    private String genTmpFileName() {
        return DateUtil.date_string(new Date(), "yyyyMMddHHmmssssss");
    }

}
