package com.elong.hotel.hotelmy.utils;


import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类MyDemo.java的实现描述：向手机群发短信，中国网建：http://sms.webchinese.cn/default.shtml
 *
 * @author admin Nov 4, 2015 5:02:29 PM
 */
public class MobileMessageUtils {
    public static final String uid="boboliu";
    public static final String token="FFBBDC9DB56CD518EBEC6947D39E32DE";
    public static final String url="http://utf8.api.smschinese.cn/";
    public static final String number="13001278080";



    public static void main(String[] args) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post =new HttpPost("http://tf8.api.smschinese.cn");
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).build();
        // 在头文件中设置转码
        sendSms(url,uid,token,number,"这是测试短信");
//        post.setHeader("Content-Type",
//                "application/x-www-form-urlencoded;charset=utf8");
//        post.setConfig(config);
//        post.setEntity(new UrlEncodedFormEntity(Arrays.asList(data),"UTF-8"));
//        try(CloseableHttpResponse execute = client.execute(post)){
//            Header[] allHeaders = execute.getAllHeaders();
//            for (Header allHeader : allHeaders) {
//                System.out.println(allHeader);
//            }
//            StatusLine statusLine = execute.getStatusLine();
//            System.out.println(statusLine);
//            HttpEntity entity = execute.getEntity();
//            System.out.println(EntityUtils.toString(entity));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        client.close();


    }


    public static void sendSms(String apiUrl,String uid,String key,String numberMobile,String message) throws Exception {
        CloseableHttpClient aDefault = HttpClients.createDefault();

        HttpPost post = new HttpPost(apiUrl);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).build();
        post.setConfig(config);
        // 设置请求的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("Uid", uid));
        params.add(new BasicNameValuePair("Key", key));
        params.add(new BasicNameValuePair("smsMob", numberMobile));
        params.add(new BasicNameValuePair("smsText", message));

        // 将参数编码为URL编码格式并设置为请求实体
        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        try(CloseableHttpResponse execute = aDefault.execute(post)) {
            String s = EntityUtils.toString(execute.getEntity());
            System.out.println(s);
            Header[] allHeaders = execute.getAllHeaders();
            for (Header allHeader : allHeaders) {
                System.out.println(allHeader);
            }
            System.out.println(execute.getStatusLine().getStatusCode());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageWithTextMagic(){

    }
}