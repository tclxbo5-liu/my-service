package com.elong.hotel.hotelmy.entity;

import lombok.Data;

@Data
public class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
