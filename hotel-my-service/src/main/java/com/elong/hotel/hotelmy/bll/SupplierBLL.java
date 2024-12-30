package com.elong.hotel.hotelmy.bll;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.hotelmy.aop.aspect.LogInvoke;
import com.elong.hotel.hotelmy.entity.SupplierChannelMerchantsRelation;
import com.elong.hotel.hotelmy.service.MyService;
import com.elong.hotel.javabase.thread.base.MyCallable;
import com.sun.org.apache.xpath.internal.functions.FuncTrue;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class SupplierBLL {

    private static  final Logger LOGGER= LoggerFactory.getLogger(SupplierBLL.class);

    @Autowired
    MyService myService;

    @LogInvoke(isController = false)
    public List<SupplierChannelMerchantsRelation> getSupplierChannelList(List<Long> idList){
        LOGGER.info("start getSupplierChannelList");
        List<SupplierChannelMerchantsRelation> relations = myService.getRelation(idList);
        LOGGER.info("end getSupplierChannelList");
        return relations;
    }

    public void postTest(){
//        myService.postTest();
//        myService.getAll("19435e3e");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future<List<SupplierChannelMerchantsRelation>> future = scheduledExecutorService.submit(new MyCallable("19435"));
        while (!future.isDone()){
            LOGGER.info("future is not done");
        }
        try {
            List<SupplierChannelMerchantsRelation> supplierChannelMerchantsRelations = future.get();
            LOGGER.info(JSON.toJSONString(supplierChannelMerchantsRelations));
            LOGGER.info("future is done");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用completableFuture等待线程执行完。结果
     */
    public void CompletableFutureTest(){
        //    thenApply: 转换结果并返回新的值
//    thenAccept: 消费结果但不返回值
//    thenRun: 不使用结果，只运行一个操作
        CompletableFuture<List<SupplierChannelMerchantsRelation>> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return myService.getAll("19435e3e");
        }).thenApply(relations->{
            for (SupplierChannelMerchantsRelation relation : relations) {
                relation.setSupplierId(123);
            }
            LOGGER.info(JSON.toJSONString(relations));
            return relations;
        }). exceptionally(e->{
            LOGGER.error("error",e);
            return Collections.emptyList();
        });
        //等待返回结果
        List<SupplierChannelMerchantsRelation> join = voidCompletableFuture.join();
        //有返回结果
        CompletableFuture<List<SupplierChannelMerchantsRelation>> listCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return myService.getAll("19435e3e");
        });

        //异步执行无返回结果并指定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture.runAsync(()->{
            LOGGER.info("runAsync");
        },executorService);
    }

    public static void combineFutureTest(){
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> "world");

        CompletableFuture<String> stringCompletableFuture2 = stringCompletableFuture.thenCombine(stringCompletableFuture1, (s1, s2) -> {
            return s1 + s2;
        });
        String join = stringCompletableFuture2.join();
        LOGGER.info(join);

        LOGGER.info((String) CompletableFuture.anyOf(stringCompletableFuture,stringCompletableFuture1).join());
        Void join1 = CompletableFuture.allOf(stringCompletableFuture, stringCompletableFuture1).join();

    }

    public static void main(String[] args) {
        testInvokeCompletableFuture();
    }



    class MyCallable implements Callable<List<SupplierChannelMerchantsRelation>> {

        String supplierId;

        public MyCallable(String supplierId) {
            this.supplierId = supplierId;
        }

        @Override
        public List<SupplierChannelMerchantsRelation> call() throws Exception {
            List<SupplierChannelMerchantsRelation> relation = myService.getAll(supplierId);
            return relation;
        }
    }

    public static void testInvokeCompletableFuture(){
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world");
            return "hello world";
        }).thenApplyAsync(s -> {
            log.info("这是第一次处理" + s);
            return "这是第一次处理" + s;
        }).thenApply(s -> {
            LOGGER.info("这是第二次处理" + s);
            return "这是第二次处理" + s;
        }).exceptionally(e -> {
            LOGGER.error("error", e);
            return "error";
        }).whenComplete((s, e) -> {
            LOGGER.info(s+"whenComplete");
        });
        stringCompletableFuture.join();
    }

    public void newThreadTask() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    }
}