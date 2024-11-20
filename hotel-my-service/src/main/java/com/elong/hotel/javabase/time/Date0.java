package com.elong.hotel.javabase.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author bobo
 * @date 2024/11/19 16:42
 **/
public class Date0 {
    public static void main(String[] args) {
        Date date =new Date();
        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();
        ZonedDateTime now2 = ZonedDateTime.now();
        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
    }
}
