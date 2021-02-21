package com.silence.weixin.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.material.*;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews.WxMpMaterialNewsArticle;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <pre>
 * 微信素材管理 <br />
 * 注 ： 封面图需要用永久、富文本中图片永久和临时都可以、群发图文如果临时用下就用临时素材，否则用永久素材(大部分选永久)
 * @author cao_wencao
 * @date 2018年8月8日
 * </pre>
 */
@Slf4j
@Component
public class WeChatUtils {
    @Autowired
    private WxMpService wxMpService;

    private static Map<String, Map<String, Object>> mediaIds = Maps.newLinkedHashMap();

    /**
     * <pre>
     * 上传群发用的图文消息，上传后才能群发图文消息.
     * 
     * @see #massGroupMessageSend(WxMpMassTagMessage) 按分组群发
     * @see #massOpenIdsMessageSend(WxMpMassOpenIdsMessage) 按openid群发
     * @author cao_wencao
     * @param news
     * @return
     * </pre>
     */
    public WxMpMassUploadResult massNewsUpload(WxMpMassNews news) {
        // 图文上传
        WxMpMassUploadResult massUploadResult = null;
        try {
            massUploadResult = wxMpService.getMassMessageService().massNewsUpload(news);
            if (null != massUploadResult) {
                log.info("\n【上传图文素材】成功:  {}", massUploadResult.getMediaId());
                return massUploadResult;
            }
        }
        catch (Exception e) {
            log.error("\n【上传图文素材】失败 :  {}", e.getMessage());
            e.printStackTrace();
        }
        return massUploadResult;

    }

    /**
     * <pre>
     * 上传临时多媒体文件 
     * 比如图文消息的封面图 图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式
     * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式 视频（video）：10MB，支持MP4格式
     * 缩略图（thumb）：64KB，支持JPG格式
     * 
     * @author cao_wencao
     * @param mediaType
     *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param fileType
     *            文件后缀
     * 
     * @param inputStream
     *            文件输入流
     * @return type,media_id,created_at
     * </pre>
     */
    public WxMediaUploadResult mediaUpload(String mediaType, String fileType, InputStream inputStream) {
        if (null == inputStream || null == mediaType || null == fileType) {
            return null;
        }
        WxMediaUploadResult res = null;
        try {
            res = this.wxMpService.getMaterialService().mediaUpload(mediaType, fileType, inputStream);
            log.info("\n【多媒体文件上传】成功MediaId : {} ", res.getMediaId());
            return res;
        }
        catch (Exception e) {
            log.error("\n【多媒体文件上传】失败 : {}", e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * <pre>
     * 上传图文消息内的图片获取URL(图文消息里面的图片)
     * 
     * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">新增永久素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param file
     *            上传的文件对象
     * @return WxMediaImgUploadResult 返回图片url
     * 
     */
    public WxMediaImgUploadResult uploadimg(File file) {
        if (null == file) {
            return null;
        }
        WxMediaImgUploadResult imageUploadResult = null;
        try {
            imageUploadResult = this.wxMpService.getMaterialService().mediaImgUpload(file);
            log.info("\n【上传图文消息内的图片】成功  : {}", imageUploadResult.getUrl());
            return imageUploadResult;
        }
        catch (Exception e) {
            log.error("\n【上传图文消息内的图片】失败 : {}", e.getMessage());
            e.printStackTrace();
        }
        return imageUploadResult;
    }

    /**
     * <pre>
     * 获取临时素材
     * 公众号可以使用本接口获取临时素材（即下载临时的多媒体文件）。请注意，视频文件不支持https下载，调用该接口需http协议。
     * 本接口即为原“下载多媒体文件”接口。
     * 根据微信文档，视频文件下载不了，会返回null
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738727&token=&lang=zh_CN">获取临时素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
     * </pre>
     *
     * @param mediaId
     *            媒体文件Id
     * @return 保存到本地的临时文件
     * @throws Exception
     */
    public File DownloadMaterial(String mediaId) {
        if (null == mediaId) {
            return null;
        }

        File file = null;
        try {
            file = this.wxMpService.getMaterialService().mediaDownload(mediaId);
            return file;
        }
        catch (Exception e) {
            log.error("\n【下载临时素材】失败 : {}", e.getMessage());
            e.printStackTrace();
        }
        return file;

    }

    // =======================================永久素材 :
    // 下载、上传、删除、更新====================================================

    /**
     * <pre>
     * 新增其他类型永久素材 非图文永久素材上传 
     * @author cao_wencao
     * @param mediaType
     *            媒体文件类型 ：分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     *            images支持 ：bmp/png/jpeg/jpg/gif格式
     * @param fileType
     * @param fileName
     * @return WxMpMaterialUploadResult : "media_id":MEDIA_ID,  "url":URL(永久素材类型为images才会返回url)
     * </pre>
     */
    public WxMpMaterialUploadResult uploadFilesToWeChat(String mediaType, String fileType, String fileName, InputStream inputStream) {
        if (null == mediaType || null == fileType || null == inputStream || null == fileName) {
            return null;
        }
        WxMpMaterialUploadResult uploadResult = null;
        try {
            File         tempFile   = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType);
            WxMpMaterial wxMaterial = new WxMpMaterial();
            wxMaterial.setFile(tempFile);
            wxMaterial.setName(fileName);
            // 视频类永久素材需要在构造时传入视频名title和简介introduction，目前已知视频支持mp4格式。
            if (WxConsts.MediaFileType.VIDEO.equals(mediaType)) {
                wxMaterial.setVideoTitle("title");
                wxMaterial.setVideoIntroduction("test video description");
            }
            // 上传素材至微信服务器
            uploadResult = wxMpService.getMaterialService().materialFileUpload(mediaType, wxMaterial);
            // 图片才会有url,其他均是media_id
            if (null != uploadResult && !StringUtils.isBlank(uploadResult.getMediaId())) {
                log.info("\n【上传素材mediaType】 : {},{}成功！", uploadResult.getUrl(), uploadResult.getMediaId());
                return uploadResult;
            }
        }
        catch (Exception e) {
            log.error("\n【上传素材mediaType】: {}失败 ", e.getMessage());
            e.printStackTrace();
        }
        return uploadResult;

    }

    /**
     * <pre>
     * 新增永久图文素材 
     * 方法名： uploadNewsToWeChat 
     * 参数： WxMpMaterialNews 
     * 返回值：
     * WxMpMaterialUploadResult 图文素材支持单图文和多图文，由类 WxMpMaterialNews 进行封装，图文的数据通过类
     * WxMpMaterialNews.WxMpMaterialNewsArticle 封装。 多图文消息在 WxMpMaterialNews 中调用
     * addArticle 方法添加多个 WxMpMaterialNewsArticle 对象即可。 接口返回 新增图文消息的media_id
     * 
     * @author cao_wencao
     * @param materialNews
     *            要上传的图文消息的素材
     * @return String
     * </pre>
     */
    public WxMpMaterialUploadResult uploadNewsToWeChat(WxMpMaterialNews materialNews) {
        String                   media_id     = null;
        WxMpMaterialUploadResult uploadResult = null;
        try {
            uploadResult = wxMpService.getMaterialService().materialNewsUpload(materialNews);
            if (null != uploadResult && !StringUtils.isBlank(uploadResult.getMediaId())) {
                media_id = uploadResult.getMediaId();
                log.info("\n【上传图文消息】成功：{}", media_id);
                return uploadResult;
            }
        }
        catch (Exception e) {
            log.error("\n【上传图文素材】失败 : {}", e.getMessage());
            e.printStackTrace();
        }
        return uploadResult;
    }

    /**
     * <pre>
     * 图文永久素材下载 根据 media_id 获取图文消息，
     * 结果由 WxMpMaterialNews 封装，该类的结构和微信服务器返回的 json,
     * 格式数据的结构保持一致。
     * 
     * @author cao_wencao
     * @param newsMediaId
     *            要下载的图文消息的id
     * @return WxMpMaterialNews
     * </pre>
     */
    public WxMpMaterialNews newsInfoDownload(String newsMediaId) {
        WxMpMaterialNews wxMpMaterialNews = null;
        try {
            wxMpMaterialNews = wxMpService.getMaterialService().materialNewsInfo(newsMediaId);
            log.info("\n【下载图文素材】成功 : {}", wxMpMaterialNews.toJson().toString());
            return wxMpMaterialNews;
        }
        catch (Exception e) {
            log.error("\n【下载图文素材】失败 ：{}", newsMediaId);
            e.printStackTrace();
        }
        return wxMpMaterialNews;
    }

    /**
     * <pre>
     * 永久素材删除 根据 media_id 删除素材
     * 
     * @author cao_wencao
     * @param mediaId
     *            要删除的图文消息的id
     * @return Boolean
     * </pre>
     */
    public Boolean DeleteMaterial(String mediaId) {
        Boolean result = false;
        try {
            result = wxMpService.getMaterialService().materialDelete(mediaId);
            log.info("\n【删除永久素材】成功 ： {} ;/n返回值 ： {} ", mediaId, result);
            return result;
        }
        catch (Exception e) {
            log.error("\n【删除永久素材】失败 ： {} ;/n返回值 ： {} ", mediaId, result);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <pre>
     * 获取图文素材列表 并且下载
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738734&token=&lang=zh_CN">获取素材列表</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN
     * @param offset
     *            从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count
     *            返回素材的数量，取值在1到20之间
     * </pre>
     */
    public WxMpMaterialNews materialNewsBatchGet(int offset, int count) throws Exception {
        if (offset < 0 || count <= 0) {
            return null;
        }
        WxMpMaterialNewsBatchGetResult newsBatchGetResult = null;
        WxMpMaterialNews               wxMpMaterialNews   = null;
        try {
            newsBatchGetResult = this.wxMpService.getMaterialService().materialNewsBatchGet(offset, count);
            if (newsBatchGetResult.getTotalCount() > 0 || newsBatchGetResult.getItemCount() > 0) {
                List<WxMaterialNewsBatchGetNewsItem> items = newsBatchGetResult.getItems();
                for (int i = 0; i < items.size(); i++) {
                    String mediaId = items.get(i).getMediaId();
                    if (StringUtils.isBlank(mediaId)) {
                        log.debug("【获取微信图文素材mediaId】失败！");
                        return null;
                    }
                    // 调取图文素材下载接口 ，参数 ：mediaId
                    wxMpMaterialNews = newsInfoDownload(mediaId);
                }
            }
            if (newsBatchGetResult.getTotalCount() == 0 || newsBatchGetResult.getItemCount() == 0) {
                log.info("【获取微信图文素材列表】为空！");
                return null;
            }
        }
        catch (Exception e) {
            log.error("\n【获取微信图文素材列表】失败 : {}", e.getMessage());
            e.printStackTrace();
        }
        return wxMpMaterialNews;
    }

    /**
     * <pre>
     * 图片或声音永久素材下载
     * 根据 media_id 获取声音或者图片的输入流 
     * 
     * @author cao_wencao
     * @param mediaId
     *            要下载的图文消息的id
     * @return InputStream
     * </pre>
     */
    public InputStream materialImageOrVoiceDownload(String mediaId) {
        if (null == mediaId) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = wxMpService.getMaterialService().materialImageOrVoiceDownload(mediaId);
            if (null != inputStream) {
                log.info("\n【下载图片或声音永久素材】成功 : {}", mediaId);
                return inputStream;
            }
        }
        catch (Exception e) {
            log.error("\n【下载图片或声音永久素材】失败 ：{} \n媒体ID : {} ", e.getMessage(), mediaId);
            e.printStackTrace();
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return inputStream;
    }

    /**
     * <pre>
     * 获取视频永久素材
     * 
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">获取永久素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param mediaId
     *            永久素材的id
     */
    public WxMpMaterialVideoInfoResult materialVideoInfo(String mediaId) {
        if (null == mediaId) {
            return null;
        }
        WxMpMaterialVideoInfoResult videoInfoResult = null;
        try {
            videoInfoResult = this.wxMpService.getMaterialService().materialVideoInfo(mediaId);
            if (null != videoInfoResult) {
                log.info("\n【下载视频永久素材】成功 : {}", mediaId);
                return videoInfoResult;
            }
        }
        catch (Exception e) {
            log.error("\n【下载取视频永久素材】失败 : {} \n媒体ID : mediaId", e.getMessage(), mediaId);
            e.printStackTrace();
        }
        return videoInfoResult;
    }

    /**
     * <pre>
     * 分页获取其他媒体素材列表
     * @author cao_wencao
     * @param type    素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset  从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count   返回素材的数量，取值在1到20之间
     * @return
     */
    public WxMpMaterialFileBatchGetResult materialFileBatchGet(String type, int offset, int count) {
        if (null == type || offset < 0 || count <= 0) {
            return null;
        }
        WxMpMaterialFileBatchGetResult fileBatchGetResult = null;
        try {
            fileBatchGetResult = this.wxMpService.getMaterialService().materialFileBatchGet(type, offset, count);
            if (fileBatchGetResult.getTotalCount() < 0 || fileBatchGetResult.getItemCount() < 0) {
                log.error("【获取微信图文素材列表】失败！");
            }
        }
        catch (Exception e) {
            log.error("\n【获取微信图文素材列表】失败 : {}", e.getMessage());
            e.printStackTrace();
        }
        return fileBatchGetResult;

    }

    /**
     * <pre>
     * 修改永久图文素材 根据更新图文对象更新图文素材，对于多图文消息，如需更新其中某一个，需要设置更新对象中的index属性
     * 
     * @author cao_wencao
     * @param newsMediaId
     *            要修改的图文消息的id
     * @param index
     *            要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param materialNews
     *            图文消息内容及相关
     * @return
     * </pre>
     */
    public boolean UpdateNewsInfo(String newsMediaId, int index, WxMpMaterialNews materialNews) {
        // 获取图文永久素材的信息
        WxMpMaterialNews wxMpMaterialNews = null;
        try {
            wxMpMaterialNews = wxMpService.getMaterialService().materialNewsInfo(newsMediaId);
            log.info("\n【获取图文永久素材】成功 ：{}", wxMpMaterialNews.toJson().toString());
        }
        catch (Exception e) {
            log.error("\n【获取永久图文素材】失败 ： {} ", newsMediaId);
            e.printStackTrace();
        }
        // 图文素材更新实体类
        WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate = new WxMpMaterialArticleUpdate();
        // dest article 目的article
        WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNewsArticle();
        // 更新一条单图文
        List<WxMpMaterialNewsArticle> newsArticleList = materialNews.getArticles();
        // source article 来源article2
        WxMpMaterialNews.WxMpMaterialNewsArticle article2 = new WxMpMaterialNewsArticle();
        for (int i = 0; i < newsArticleList.size(); i++) {
            article2 = newsArticleList.get(i);
            article.setContent(article2.getContent());
            article.setAuthor(article2.getAuthor());
            article.setContentSourceUrl(article2.getContentSourceUrl());
            article.setDigest(article2.getDigest());
            article.setShowCoverPic(article2.isShowCoverPic());
            article.setThumbMediaId(newsMediaId);
            article.setNeedOpenComment(article2.getNeedOpenComment());
            article.setOnlyFansCanComment(article2.getOnlyFansCanComment());
            article.setThumbUrl(article2.getThumbUrl());
            article.setTitle(article2.getTitle());
            article.setUrl(article2.getUrl());
            wxMpMaterialArticleUpdate.setMediaId(newsMediaId);
            wxMpMaterialArticleUpdate.setArticles(article);
            wxMpMaterialArticleUpdate.setIndex(index);
        }
        boolean updateResult = false;
        try {
            updateResult = wxMpService.getMaterialService().materialNewsUpdate(wxMpMaterialArticleUpdate);
            log.info("\n【修改永久图文素材】成功 ： {} ", updateResult);
            return updateResult;
        }
        catch (Exception e) {
            log.error("\n【修改永久图文素材】失败 ： {} ", updateResult);
            e.printStackTrace();
        }
        return updateResult;
    }

    /**
     * <pre>
     * 获取图文永久素材的信息
     * 
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738729&token=&lang=zh_CN">获取永久素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param mediaId
     *            永久素材的id
     */
    public WxMpMaterialNews getMaterialInfo(String mediaId) {
        WxMpMaterialNews wxMpMaterialNews = null;
        try {
            wxMpMaterialNews = wxMpService.getMaterialService().materialNewsInfo(mediaId);
            if (null != wxMpMaterialNews) {
                log.info("\n【从微信媒体库获取图文永久素材】成功 ：{}", wxMpMaterialNews.toJson().toString());
                return wxMpMaterialNews;
            }
        }
        catch (WxErrorException e) {
            log.error("\n【从微信媒体库获取永久图文素材】异常 ,媒体ID ：{}", mediaId);
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * <pre>
     * 获取各类素材总数
     * 开发者可以根据本接口来获取永久素材的列表，需要时也可保存到本地。
     * 请注意：
     *  1.永久素材的总数，也会计算公众平台官网素材管理中的素材
     *  2.图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000
     *  3.调用该接口需https协议
     * 
     * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738733&token=&lang=zh_CN">获取素材总数</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN
     *  "voice_count":COUNT, 语音总数量
     *  "video_count":COUNT, 视频总数量
     *  "image_count":COUNT, 图片总数量
     *  "news_count":COUNT   图文总数量
     * </pre>
     */
    public String materialCount(String materiaType) {
        WxMpMaterialCountResult resultCount = null; //素材总数返回类
        String                  newsCount   = null; // 图文素材数量
        String                  imageCount  = null;// 图片素材数量
        try {
            resultCount = wxMpService.getMaterialService().materialCount();
            JSONObject jsonObject = (JSONObject) JSON.toJSON(resultCount);
            if (null != resultCount && WxConsts.MaterialType.IMAGE.equals(materiaType)) {
                imageCount = jsonObject.getString("imageCount"); // 图片素材总数
                log.info("【获取图片总数成功,image_Count】:{}条", imageCount);
                return imageCount;
            }if (null != resultCount && WxConsts.MaterialType.NEWS.equals(materiaType)) {
                newsCount = jsonObject.getString("newsCount"); // 图文素材总数
                log.info("【获取图文总数成功,news_Count】:{}条", newsCount);
                return newsCount;
            }
        }
        catch (WxErrorException e) {
            log.error("\n【获取各类素材总数】异常 {}", e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

}
