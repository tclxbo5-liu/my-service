package com.elong.hotel.javabase.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author bobo
 * @date 2024/11/20 10:32
 **/
public class ScheduleTest implements Runnable {
    public static  final ExecutorService excutors = Executors.newScheduledThreadPool(5);

    @Override
    public void run() {
        System.out.println("running");
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ScheduleTest(),1,1, TimeUnit.SECONDS);
    }
}
