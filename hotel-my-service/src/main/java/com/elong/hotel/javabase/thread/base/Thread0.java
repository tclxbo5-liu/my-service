package com.elong.hotel.javabase.thread.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bobo
 * @date 2024/11/22 10:21
 **/
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Thread0 extends Thread {

    private Person person;

    @Override
    public void run() {
        this.person.setAge(this.getPerson().getAge()-1);
        System.out.println(this.person.getAge());
//        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Runnable0 runnable0 = new Runnable0();
        Thread thread = new Thread(runnable0);
        thread.start();
        Thread thread2 =new Thread(()-> {
            try {
                //当前线程将被阻塞直到线程thread完成其任务。
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello");
        });
        thread2.start();
        Person person = new Person(1000);
        for (int i = 0; i < 1000; i++) {
            Thread0 threadTemp = new Thread0(person);
            threadTemp.start();
        }
//        System.out.println("end--->>"+person.getAge());
    }

}
