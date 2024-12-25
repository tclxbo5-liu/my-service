package com.elong.hotel.javabase.thread.base;

/**
 * @author bobo
 * @date 2024/11/22 17:05
 **/
public class TreadChange {
    public static class WorkThread extends Thread{
        private final ThreadLocal<String> context;

        public WorkThread(ThreadLocal<String> context) {
            this.context = context;
        }

        @Override
        public void run() {
            System.out.println(context.get());
            context.set(Thread.currentThread().getName());
            System.out.println(context.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> context = new ThreadLocal<>();
        WorkThread workThread1 = new WorkThread(context);
        WorkThread workThread2 = new WorkThread(context);
        workThread1.start();
        workThread2.start();
        System.out.println(context.get());
    }
}
