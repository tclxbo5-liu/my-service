package com.elong.hotel.hotelmy.utils.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户线程：由用户空间程序管理和调度的线程，运行在用户空间（专门给应用程序使用）。
 * 内核线程：由操作系统内核管理和调度的线程，运行在内核空间（只有内核程序可以访问）。
 * 顺便简单总结一下用户线程和内核线程的区别和特点：用户线程创建和切换成本低，但不可以利用多核。
 *                                         内核态线程，创建和切换成本高，可以利用多核。
 *   一句话概括 Java 线程和操作系统线程的关系：现在的 Java 线程的本质其实就是操作系统的线程。
 */
public class ThreadNew {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
    }
    
    public static void AtomicIntegerTest(){
        AtomicInteger atomicInteger = new AtomicInteger(1);
        int andIncrement = atomicInteger.getAndIncrement();
    }
}

//下面来思考这样一个问题：为什么程序计数器、虚拟机栈和本地方法栈是线程私有的呢？为什么堆和方法区是线程共享的呢？
//answer:
//字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理。
//在多线程的情况下，程序计数器用于记录当前线程执行的位置，从而当线程被切换回来的时候能够知道该线程上次运行到哪儿了。线程切换后能恢复到正确的执行位置


//虚拟机栈： 每个 Java 方法在执行之前会创建一个栈帧用于存储局部变量表、操作数栈、常量池引用等信息。
//        从方法调用直至执行完成的过程，就对应着一个栈帧在 Java 虚拟机栈中入栈和出栈的过程。
//        本地方法栈： 和虚拟机栈所发挥的作用非常相似，区别是：虚拟机栈为虚拟机执行 Java 方法 （也就是字节码）服务
//        ，而本地方法栈则为虚拟机使用到的 Native 方法服务。
//        在 HotSpot 虚拟机中和 Java 虚拟机栈合二为一。
//
//        所以，为了保证线程中的局部变量不被别的线程访问到，虚拟机栈和本地方法栈是线程私有的。
