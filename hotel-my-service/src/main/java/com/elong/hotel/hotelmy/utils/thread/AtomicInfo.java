package com.elong.hotel.hotelmy.utils.thread;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AtomicInfo {
    public volatile static int inc=0;

    public  void increase(){
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(BigDecimal.valueOf(0).compareTo(BigDecimal.valueOf(-1)));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        AtomicInfo atomicInfo = new AtomicInfo();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int j = 0; j <5 ; j++) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 500; i++) {
                        atomicInfo.increase();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(inc);
        threadPoolExecutor.shutdown();
    }
}
