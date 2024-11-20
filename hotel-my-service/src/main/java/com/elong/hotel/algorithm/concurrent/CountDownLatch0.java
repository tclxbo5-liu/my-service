package com.elong.hotel.algorithm.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bobo
 * @date 2024/11/19 16:23
 **/
public class CountDownLatch0 {

    public final static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public final static int length = 100;


    public static void main(String[] args) throws InterruptedException {
        TaskData taskData = new TaskData();
        taskData.setData(length);
        CountDownLatch countDownLatch = new CountDownLatch(length);
        for (int j = 0; j <length ; j++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int data = taskData.getData();
                    taskData.setData(--data);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(taskData.getData());
    }

   static class TaskData{
        private int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
