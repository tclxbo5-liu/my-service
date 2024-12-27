package com.elong.hotel.hotelmy;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.hotelmy.entity.SupplierChannelMerchantsRelation;
import com.elong.hotel.hotelmy.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
class HotelMyApplicationTests {

    @Autowired
    MyService myService;

    @MockBean
    MyService myServiceMock;

    @BeforeEach
    public void init() {
        log.info("init");
    }

    @AfterEach
    public void destroy() {
        log.info("destroy");
    }

    @Test
    void setMyService() {
        List<SupplierChannelMerchantsRelation> relation = myService.getRelation(Arrays.asList(1L, 2L));
        System.out.println(JSON.toJSONString(relation));
        log.info("relation:{}", relation);

        //模拟方法返回null
        when(myServiceMock.getRelation(Arrays.asList(1L, 2L))).thenReturn(null);

        //验证方法从未被调用
        verify(myServiceMock,never()).getRelation(Arrays.asList(1L, 2L));

        //任意参数
        when(myService.getRelation(any())).thenReturn(Arrays.asList(new SupplierChannelMerchantsRelation(), new SupplierChannelMerchantsRelation()));

        when(myServiceMock.getRelation(Arrays.asList(1L, 2L))).
                thenReturn(Arrays.asList(new SupplierChannelMerchantsRelation(), new SupplierChannelMerchantsRelation()));
        log.info(String.valueOf(myServiceMock.getRelation(Arrays.asList(1L, 2L)).toString()));
        //验证方法被调用两次
        verify(myServiceMock,times(2)).getRelation(Arrays.asList(1L, 2L));
        List<SupplierChannelMerchantsRelation> relation1 = myServiceMock.getRelation(Arrays.asList(1L, 2L));
        log.info("relation1:{}", relation1);
    }


    @Test
    public void test() {
        myService.getAll("19435");
    }
}
