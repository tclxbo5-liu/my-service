package com.elong.hotel.javabase.reflection;

import com.elong.hotel.javabase.scheduler.ScheduleTest;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author bobo
 * @date 2024/11/20 10:37
 **/
@Data
public class ShowMethod {
    private String name="hello";

    private String home;
    private Integer age;
    private ScheduleTest shceduleTest;

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.elong.hotel.javabase.reflection.ShowMethod");
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.toGenericString());
        }
    }

    private int add(int a,int b){
        return a+b;
    }
}
