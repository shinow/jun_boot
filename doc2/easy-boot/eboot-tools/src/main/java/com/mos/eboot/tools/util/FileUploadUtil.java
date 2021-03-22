package com.mos.eboot.tools.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

public class FileUploadUtil {

    public static String uploadMultiFile(MultipartFile file, String url, String bsnType,Map<String,Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            builder.addTextBody("type",bsnType.toString(), ContentType.create("text/plain", HttpClientUtil.CHARSET));
            setUploadParams(builder,params);
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName(HttpClientUtil.CHARSET));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 文件上传http
     * @param serverURL 上传接口url
     * @param localFilePath 文件路径
     * @param serverFieldName 服务端接收文件参数名
     * @param params 其他参数
     * @return
     */
    public static String uploadFile(String serverURL,String localFilePath,String serverFieldName,Map<String,Object> params){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(serverURL);
        String result = null;

        FileBody fileBody = new FileBody(new File(localFilePath));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        //设置以浏览器兼容模式发送，否则会出现中文文件名乱码问题
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(Charset.forName(HttpClientUtil.CHARSET));
        builder.addPart(serverFieldName,fileBody);
        setUploadParams(builder,params);
        HttpEntity httpEntity = builder.build();
        post.setEntity(httpEntity);

        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(), HttpClientUtil.CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static void setUploadParams(MultipartEntityBuilder builder,Map<String,Object> params){
        if (params == null || builder == null || params.isEmpty()){
            return;
        }
        Set<String> keys = params.keySet();
        for (String key :keys){
            ContentType strContent=ContentType.create("text/plain", HttpClientUtil.CHARSET);
            builder.addTextBody(key,params.get(key).toString(), strContent);
        }
    }

}
