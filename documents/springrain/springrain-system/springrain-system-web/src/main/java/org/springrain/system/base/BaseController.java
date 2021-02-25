package org.springrain.system.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springrain.frame.util.DateTypeEditor;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.DicData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 基础的Controller,所有的Controller必须继承此类
 *
 * @author springrain<9 iuorg @ gmail.com>
 * @version 2013-03-19 11:08:15
 * @see org.springrain.system.base.BaseController
 */
// @Controller
public class BaseController {
    public static final String messageurl = "/common/message";
    public static final String message = "message";
    public static final String redirect = "redirect:";
    public static final String forward = "forward:";
    public Logger logger = LoggerFactory.getLogger(getClass());

    // @Resource
    // private CacheManager shiroCacheManager;

    /**
     * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
     */

    // @ModelAttribute("")
    public void init(Model model) {
        // model.addAttribute("now", new Date());
    }

    /**
     * 初始化映射格式.
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class, new DateTypeEditor());

        /*
         * binder.registerCustomEditor(Date.class, new PropertyEditorSupport() { public
         * void setAsText(String value) { try { if (StringUtils.isNotBlank(value)) { try
         * { setValue(new SimpleDateFormat(DateUtils.DATETIME_FORMAT).parse(value)); }
         * catch (Exception e) { setValue(new
         * SimpleDateFormat(DateUtils.DATE_FORMAT).parse(value)); } } else {
         * setValue(null); } } catch (Exception e) { setValue(null);
         * logger.error(e.getMessage(), e); } }
         *
         * // public String getAsText() { return new //
         * SimpleDateFormat("yyyy-MM-dd").format((Date) getValue()); }
         *
         * });
         *
         *
         *
         *
         * binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
         * public void setAsText(String value) { try { if
         * (StringUtils.isNotBlank(value)) { setValue(Integer.valueOf(value)); } else {
         * setValue(null); } } catch (Exception e) { setValue(null);
         * logger.error(e.getMessage(), e); } } });
         * binder.registerCustomEditor(Long.class, new PropertyEditorSupport() { public
         * void setAsText(String value) { try { if (StringUtils.isNotBlank(value)) {
         * setValue(Long.valueOf(value)); } else { setValue(null); } } catch (Exception
         * e) { setValue(null); logger.error(e.getMessage(), e); } } });
         * binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
         * public void setAsText(String value) { try { if
         * (StringUtils.isNotBlank(value)) { setValue(Double.valueOf(value)); } else {
         * setValue(null); } } catch (Exception e) { setValue(null);
         * logger.error(e.getMessage(), e); } } });
         *
         * binder.registerCustomEditor(BigDecimal.class, new PropertyEditorSupport() {
         * public void setAsText(String value) { try { if
         * (StringUtils.isNotBlank(value)) { setValue(new BigDecimal(value)); } else {
         * setValue(null); } } catch (Exception e) { setValue(null);
         * logger.error(e.getMessage(), e); } } });
         *
         */

    }

    /*
     * @ExceptionHandler public String exp(HttpServletRequest request, Exception e)
     * { request.setAttribute("e", e); logger.error(e.getMessage(), e); return
     * "/error"; }
     */

    /**
     * 公共下载方法
     *
     * @param response
     * @param file     下载的文件
     * @param fileName 下载时显示的文件名
     * @return
     * @throws Exception
     */
    public HttpServletResponse downFile(HttpServletResponse response, File file, String fileName, boolean delFile)
            throws Exception {
        response.setContentType("application/x-download");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        OutputStream out = null;
        InputStream in = null;
        // 下面一步不可少
        fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);// 设定输出文件头

        try {
            out = response.getOutputStream();
            in = new FileInputStream(file);
            int len = in.available();
            byte[] b = new byte[len];
            in.read(b);
            out.write(b);
            out.flush();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception("下载失败!");
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (delFile) {
                file.delete();
            }
        }
        return response;
    }

    /**
     * 获取分页 page 对象
     *
     * @param request
     * @return
     */
    public Page<?> newPage(HttpServletRequest request) {
        // ==获取分页信息
        return newPage(request, null, null);
    }

    /**
     * 指定默认的排序字段和方法 返回page分页对象
     *
     * @param request
     * @param defaultOrder
     * @param defaultSort
     * @return
     */
    public Page<?> newPage(HttpServletRequest request, String defaultOrder, String defaultSort) {

        if (request == null) {
            Page<?> page = new Page<>(1);
            //page.setOrder("id");
            //page.setSort("asc");
            return page;
        }

        // ==获取分页信息
        String str_pageIndex = request.getParameter("pageNo");
        String order = request.getParameter("order");
        String sort = request.getParameter("sort");

        if (StringUtils.isBlank(str_pageIndex)) {
            str_pageIndex = "1";
        }

        int pageIndex = NumberUtils.toInt(str_pageIndex, 1);

        if (StringUtils.isBlank(order)) {
            order = defaultOrder;
        }
        if (StringUtils.isBlank(sort)) {
            sort = defaultSort;
        }

        Page<?> page = new Page<>(pageIndex);
        page.setOrder(order);
        page.setSort(sort);
        return page;
    }
}