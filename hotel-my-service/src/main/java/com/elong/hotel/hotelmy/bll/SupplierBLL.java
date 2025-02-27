package com.elong.hotel.hotelmy.bll;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.hotelmy.entity.SupplierChannelMerchantsRelation;
import com.elong.hotel.hotelmy.mybatis.SupplierChannelMerchantsRelationMapper;
import com.elong.hotel.hotelmy.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class SupplierBLL {

    private static  final Logger LOGGER= LoggerFactory.getLogger(SupplierBLL.class);

    @Autowired
    MyService myService;

    public List<SupplierChannelMerchantsRelation> getSupplierChannelList(List<Long> idList){
        LOGGER.info("helloworld");
        List<SupplierChannelMerchantsRelation> relations = myService.getRelation(idList);
        System.out.println(JSON.toJSONString(relations));
        return relations;
    }

    public void postTest(){
        myService.postTest();
    }

    public void newThreadTask() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    }
}