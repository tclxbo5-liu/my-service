package com.elong.hotel.hotelmy.utils.genericity;

import java.util.Collections;

/**
 * @author bobo
 * @date 2024/10/11 16:34
 **/
public class GenericityClass<T> {
    T key;

    public GenericityClass(T key) {
        this.key = key;
    }

    public static  <E>  void printArray(E[] inputArray){
        for (E e : inputArray) {
           System.out.printf("%s",e);
        }
    }


    public static void main(String[] args) {
        GenericityClass<Integer> integerGenericityClass = new GenericityClass<Integer>(12);
        // 创建不同类型数组：Integer, Double 和 Character
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = { "Hello", "World" };
        printArray( intArray  );
        System.out.println();
        printArray( stringArray  );
    }
}
