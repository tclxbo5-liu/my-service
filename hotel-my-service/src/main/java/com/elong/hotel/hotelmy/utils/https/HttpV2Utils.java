package com.elong.hotel.hotelmy.utils.https;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author bobo
 * @date 2024/12/27 15:17
 **/
public class HttpV2Utils {

    // 创建HttpClient
    public static CloseableHttpClient getClient(){
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(5000).
                setMaxRedirects(4).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        CloseableHttpClient aDefault = HttpClients.createDefault();

        return httpClient;
    }

    // 创建请求
    public static HttpUriRequest getHttpRequest(String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(new BasicHeader("Content-type","application/json"));

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(new BasicHeader("Content-type","application/json"));
        httpPost.setEntity(new StringEntity("{\"key\":\"value\"}",
                ContentType.APPLICATION_JSON));

        return httpPost;
    }

    // 从HttpResponse中获取数据
    public static void handleResponse(HttpResponse httpResponse) throws IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Header[] headers = httpResponse.getHeaders("Content-Type");
        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
    }

    public static void main(String[] args) {
        CloseableHttpClient client = getClient();
        HttpUriRequest httpRequest = getHttpRequest("http://www.baidu.com");
        try(CloseableHttpResponse response = client.execute(httpRequest)) {
            String string = EntityUtils.toString(response.getEntity());
            System.out.println(string);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public <T> T Post(String url, String requestJson, Class<T> clazz) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        return null;
    }
}
