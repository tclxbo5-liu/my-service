package com.elong.hotel.javabase.thread.base;

/**
 * @author bobo
 * @date 2024/11/22 10:27
 **/
public class Runnable0 implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("running 0 ");
    }
}
