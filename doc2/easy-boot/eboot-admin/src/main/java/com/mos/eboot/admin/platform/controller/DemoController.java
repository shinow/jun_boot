package com.mos.eboot.admin.platform.controller;


import cn.afterturn.easypoi.word.WordExportUtil;
import com.mos.eboot.admin.platform.api.ISysUserService;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.IDGen;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("demo")
public class DemoController {

    @Resource
    private ISysUserService sysUserService;


    @RequestMapping("index")
    public String index(Model model) {
        String textValue = "上士闻道，仅能行之；中士闻道，若存若亡；下士闻道，大笑之。" +
                "不笑不足以为道。" +
                "故建言有之：明道若昧；进道若退；夷道若颣（lei）；上德若谷，大白若辱，广德若不足，建德若偷，质真若渝；大方无隅；大器免成；大音希声；大象无形。" +
                "道隐无名。" +
                "夫唯道，善始且善成。";
        model.addAttribute("textValue", textValue);
        return "demo/demo";
    }

    @ResponseBody
    @RequestMapping("index2")
    public ResultModel test() {
        return new ResultModel(ResultStatus.SUCCESS, sysUserService.getByUsername("admin"));
    }

    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
        Map<String, Object> map = new HashMap<>();
        map.put("department", "Easypoi");
        map.put("auditPerson", "JueYue");
        map.put("time", format.format(new Date()));
        map.put("date", new Date());
        try {
            XWPFDocument doc = WordExportUtil.exportWord07("word/simple.docx", map);
            String filename = "E:/test/"+IDGen.genId()+".docx";
            FileOutputStream fos = new FileOutputStream(filename);
            doc.write(fos);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName="+filename);
            OutputStream out = response.getOutputStream();
            doc.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            delAllFile("E:/test/");
        }

    }

    private  boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    private  void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
