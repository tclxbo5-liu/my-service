package com.elong.hotel.javabase.reflection;

import java.lang.reflect.Method;

/**
 * @author bobo
 * @date 2024/11/20 10:37
 **/
public class ShowMethod {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.elong.hotel.javabase.reflection.ShowMethod");
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.toGenericString());
        }
    }
}
