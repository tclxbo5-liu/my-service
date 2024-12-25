package com.elong.hotel.javabase.thread.base;

/**
 * @author bobo
 * @date 2024/11/22 15:47
 **/
public class ThreadStatus {
    static Object lock = new Object();

    public void blockedToRunning() {
        Thread threadA = null;
        threadA = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread A run");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                System.out.println("thread B run");
            }
        });
        threadA.start();
        threadB.start();
        while (true) {
            if (threadB.getState() == Thread.State.BLOCKED) {
                continue;
            }
            System.out.println(threadB.getState());
            break;
        }
    }


    public void communicateThread() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+i);
                }
            }

        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("------->"+Thread.currentThread().getName()+i);
                    lock.notify();
                }

            }
        });
        threadB.start();
        threadA.start();
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadStatus threadStatus = new ThreadStatus();
//        threadStatus.blockedToRunning();
        threadStatus.communicateThread();
    }

}
