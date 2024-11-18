package com.elong.hotel.hotelmy.utils;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.hotelmy.common.model.req.RequestBase;
import com.elong.hotel.hotelmy.common.model.resp.ResponseBase;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {


    public static ResponseBase post(String requestUrl, RequestBase requestBase) throws IOException {
        String str = "";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoInput(true);

            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] intput = str.getBytes(StandardCharsets.UTF_8);
                os.write(intput, 0, intput.length);
            }
            int responseCode = httpURLConnection.getResponseCode();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                System.out.println("response" + stringBuilder.toString());
            }
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static  String postWithHttpClients(String url, String json) {
        String responseStr="";
        CloseableHttpClient defaultHttpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(json, "UTF-8"));

        try(CloseableHttpResponse response = defaultHttpclient.execute(httpPost)) {
            responseStr = EntityUtils.toString(response.getEntity());
            System.out.println("response: " + responseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return responseStr;
    }

    public static ResponseBase postObjectWithHttpClient(String url, String request,int timeOut){
        ResponseBase responseBase = new ResponseBase();


        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");
        try (CloseableHttpClient aDefault = HttpClients.createDefault();){
            httpPost.setEntity(new StringEntity(request));

            try (CloseableHttpResponse execute = aDefault.execute(httpPost)) {
                HttpEntity entity = execute.getEntity();
                String s = EntityUtils.toString(entity);
                responseBase = JSON.parseObject(s, ResponseBase.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBase;
    }
}
