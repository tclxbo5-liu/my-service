package com.elong.hotel.javabase.thread.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bobo
 * @date 2024/11/22 14:24
 **/
public class ReentrantLockImp implements Runnable{

    private static int count = 1000;
    public static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new ReentrantLockImp());
            thread.start();
        }
        System.out.println(count);
    }


    @Override
    public void run() {
        lock.lock();
        try {
            int count = ReentrantLockImp.count;
            if (count > 0) {
                ReentrantLockImp.count--;
                System.out.println(ReentrantLockImp.count);
            }
        } finally {
            lock.unlock();
        }
    }
}
