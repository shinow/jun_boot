package com.ruoyi.web.controller.okr;

import java.io.IOException;
import java.util.*;

import com.ruoyi.common.oss.OSSFactory;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OkrCommentMeta;
import com.ruoyi.system.service.IOkrCommentMetaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 *  评论附加信息 Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
@Controller
@RequestMapping("/comment/meta")
public class OkrCommentMetaController extends BaseController
{
    private String prefix = "comment";

    @Value("${oss.aliyunDomain}")
    private String aliyunDomain;

    @Autowired
    private IOkrCommentMetaService okrCommentMetaService;

    @Autowired
    private OSSFactory ossFactory;

    @GetMapping()
    public String meta()
    {
        return prefix + "/meta";
    }

    /**
     * 查询 评论附加信息 列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrCommentMeta okrCommentMeta)
    {
        startPage();
        List<OkrCommentMeta> list = okrCommentMetaService.selectOkrCommentMetaList(okrCommentMeta);
        return getDataTable(list);
    }

    /**
     * 新增 评论附加信息 
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存 评论附加信息 
     */
    @Log(title = " 评论附加信息 ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrCommentMeta okrCommentMeta)
    {
        return toAjax(okrCommentMetaService.insertOkrCommentMeta(okrCommentMeta));
    }

    /**
     * @desc: 文件上传
     * @params: files
     * return 信息List对象
     */
    @Log(title = " 评论附加信息文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/addFile")
    @ResponseBody
    public AjaxResult UploadCommentMetaFiles(@RequestParam(value = "commentMetaFiles") MultipartFile[] metaFiles) {
        LinkedList<Object> resultList = new LinkedList<>();
        Map<String,byte[]>  filesMap = new HashMap<>();
        try {
            for (MultipartFile metaFile : metaFiles) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put( "name", metaFile.getOriginalFilename() );
                resultMap.put( "type", FileUploadUtils.getImageOrFile(metaFile) );
                resultMap.put( "size", metaFile.getSize() );
                String url = ossFactory.build().getPath("comment/file",metaFile.getOriginalFilename()) ;
                filesMap.put(url,metaFile.getBytes());
                resultMap.put( "url",aliyunDomain + ""+ url );
                resultList.add( resultMap );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error("文件上传出错！");
        }

        if (filesMap.size() > 0) {
            //开启线程异步上传
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(Map.Entry<String, byte[]> entry : filesMap.entrySet()){
                        String file =  ossFactory.build().upload(entry.getValue(),entry.getKey());
                        System.out.println("异步上传文件成功:"+file);
                    }
                }
            }).start();
        }
        return success("文件上传成功！",resultList);
    }

    /**
     * 修改 评论附加信息 
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrCommentMeta okrCommentMeta = okrCommentMetaService.selectOkrCommentMetaById(id);
        mmap.put("okrCommentMeta", okrCommentMeta);
        return prefix + "/edit";
    }

    /**
     * 修改保存 评论附加信息 
     */
    @Log(title = " 评论附加信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrCommentMeta okrCommentMeta)
    {
        return toAjax(okrCommentMetaService.updateOkrCommentMeta(okrCommentMeta));
    }

    /**
     * 导出 评论附加信息 列表
     */
    @Log(title = " 评论附加信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrCommentMeta okrCommentMeta)
    {
        List<OkrCommentMeta> list = okrCommentMetaService.selectOkrCommentMetaList(okrCommentMeta);
        ExcelUtil<OkrCommentMeta> util = new ExcelUtil<OkrCommentMeta>(OkrCommentMeta.class);
        return util.exportExcel(list, "meta");
    }

    /**
     * 删除 评论附加信息 
     */
    @Log(title = " 评论附加信息 ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrCommentMetaService.deleteOkrCommentMetaByIds(ids));
    }
}
