package com.elong.hotel.javabase.thread.base;

import java.util.concurrent.*;

/**
 * @author bobo
 * @date 2024/11/22 10:50
 **/
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("thread now call");
        return "Thread is running";
    }
}

class Main {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(futureTask.isDone());

        Callable<Person> callableTask = ()->{
            Person build = Person.builder().age(123).build();
            return build;
        };
        FutureTask<Person> personFutureTask = new FutureTask<>(callableTask);
        Thread thread1 = new Thread(personFutureTask);
        thread1.start();
        try {
            Person person = personFutureTask.get();
            System.out.println(person.getAge());
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //代表一个异步的计算结果
        Future<Person> futureResult = scheduledExecutorService.submit(callableTask);
        System.out.println(futureResult.isCancelled());


    }
}
