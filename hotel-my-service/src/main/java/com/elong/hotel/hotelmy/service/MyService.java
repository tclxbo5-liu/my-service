package com.elong.hotel.hotelmy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elong.hotel.hotelmy.bll.SupplierBLL;
import com.elong.hotel.hotelmy.common.model.MyThread;
import com.elong.hotel.hotelmy.common.model.resp.ResponseBase;
import com.elong.hotel.hotelmy.entity.People;
import com.elong.hotel.hotelmy.entity.SupplierChannelMerchantsRelation;
import com.elong.hotel.hotelmy.mybatis.SupplierChannelMerchantsRelationMapper;
import com.elong.hotel.hotelmy.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class MyService   {

    public final static Logger LOGGER= LoggerFactory.getLogger("specialLog");

    @Autowired
    SupplierChannelMerchantsRelationMapper supplierChannelMerchantsRelationMapper;

    public List<SupplierChannelMerchantsRelation> getRelation(List<Long> idList) {
        LOGGER.info("helloworjeiwjoi");
        return supplierChannelMerchantsRelationMapper.selectByPrimaryKey(idList);
    }


    public void thread2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        People jon = new People("jon", 4);
        for (int i = 0; i < 5; i++) {
            MyThread thread = new MyThread("A",jon ,5);
            threadPoolExecutor.execute(thread);
        }
    }

    public void postTest(){
        String url  ="http://jproduct-service-product-qa.vip.elong.com//rest/product/supplierSettlement/getSupplierChannelMerchants";
        String request = "{\"requestIp\":\"10.161.116.180\",\"channel\":\"test\",\"realRequest\":{\"page\":1}}";
        ResponseBase responseBase = HttpUtils.postObjectWithHttpClient(url, request,1000);
        JSONObject jsonObject = JSONObject.parseObject(responseBase.getData().toString());
        JSONArray jsonArray = jsonObject.getJSONArray("supplierChannelMerchantsRelationList");
        List<SupplierChannelMerchantsRelation> supplierChannelMerchantsRelationList= jsonArray.toJavaList(SupplierChannelMerchantsRelation.class);
        Object supplierChannelMerchantsRelationList1 = jsonObject.get("supplierChannelMerchantsRelationList");
        List<SupplierChannelMerchantsRelation> supplierChannelMerchantsRelations = JSON.parseArray(supplierChannelMerchantsRelationList1.toString(), SupplierChannelMerchantsRelation.class);
        Object data = responseBase.getData();
    }
}
