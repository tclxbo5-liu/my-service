package com.elong.hotel.javabase.thread.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bobo
 * @date 2024/11/22 13:56
 **/
public class AtomicUse {

    private static AtomicInteger atomicInteger;
    public static void main(String[] args) {
        atomicInteger = new AtomicInteger(1);
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.getAndIncrement());
    }
}
