package com.mos.eboot.tools.util;

import com.mos.eboot.tools.exception.NormalException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * httpclient工具类
 *
 * @author 小尘哥
 */
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager poolManager;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;
    public static final String CHARSET = "UTF-8";

    static {
        poolManager = new PoolingHttpClientConnectionManager();
        poolManager.setMaxTotal(100);
        poolManager.setDefaultMaxPerRoute(poolManager.getMaxTotal());
        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        requestBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestBuilder.setConnectTimeout(MAX_TIMEOUT);
        requestBuilder.setSocketTimeout(MAX_TIMEOUT);

        requestConfig = requestBuilder.build();
    }

    /**
     * 无参数的et请求
     * @param url 请求地址
     * @return
     */
    public static String get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * 带参数的Get请求
     * @param url 请求路径
     * @param params 请求参数
     * @return
     */
    public static String get(String url,Map<String,String> params) {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        CloseableHttpResponse response = null;
        try {
            List <NameValuePair> nvps = new ArrayList<>();
            for(Map.Entry<String, String> entry : params.entrySet()){
                NameValuePair nvp = new BasicNameValuePair(entry.getKey(),entry.getValue());
                nvps.add(nvp);
            }
            URI uri = new URIBuilder(url).setParameters(nvps).build();
            HttpGet httpget = new HttpGet(uri);
            response = httpclient.execute(httpget);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e){
            throw new NormalException(e.getMessage());
        }finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 无参数的Post请求
     * @param url 请求地址
     * @return
     */
    public static String post(String url) {
        return post(url, new HashMap<>());
    }

    /**
     * 带参数的Post请求
     * @param url  请求地址
     * @param params 参数
     * @return
     */
    public static String post(String url, Map<String, Object> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            post.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue() == null ? "" :entry
                        .getValue().toString());
                pairList.add(pair);
            }
            post.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CHARSET)));
            response = client.execute(post);
            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    /**
     * post提交，json格式
     *
     * @param url  请求路径
     * @param json json对象
     * @return 请求结果字符串
     */
    public static String doPost(String url, Object json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), CHARSET);
            stringEntity.setContentEncoding(CHARSET);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    public static void main(String[] args) {
        String getURL = "http://localhost:8805/demo/get?params=张三";
        String postURL = "http://localhost:8805/demo/post?params=张三";
        String postFileURL = "http://localhost:8805/demo/postFile?params=张三";
        Map<String,String> params = new HashMap<>();
        params.put("siteId","afsafsa");
        params.put("unionName","张三1");

        System.out.println(get("http://localhost:8805/api/platform/union/get-by-site",params));

    }
}
