package com.elong.hotel.hotelmy.common.model;

import com.elong.hotel.hotelmy.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyThread  extends Thread{
    public final static Logger LOGGER= LoggerFactory.getLogger(MyThread.class);
    private String name;

    private Integer count;

    private People people;
    public MyThread(String name,People people,Integer count) {
        this.name = name;
        this.people = people;
        this.count = count;
    }

    @Override
    public void run() {
        count = count - 1;
        LOGGER.info("Thread " + currentThread() + " is running" + "count id:" + count);
        people.setAge(people.getAge() - 1);
    }
}
