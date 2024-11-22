package com.elong.hotel.javabase.functions;

import java.util.function.Function;

/**
 * @author bobo
 * @date 2024/11/21 20:11
 **/
public class FunctionTest {
    public static void main(String[] args) {
        Function<Integer, String> integerStringFunction = Integer::toBinaryString;
        System.out.println(applyFunction(10, integerStringFunction));
    }

    public static <T, U> U applyFunction(T input, Function<T, U> function) {
        return function.apply(input);
    }
}
