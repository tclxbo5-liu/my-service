package com.elong.hotel.javabase.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bobo
 * @date 2024/11/21 20:27
 **/
public class FunctionImpl {
    public static void main(String[] args) {
        Function<Integer,String> function = Integer::toBinaryString;
        Function<String,Integer> lengthFunction =  s->s.length();

        Function<Integer, Integer> integerIntegerFunction = function.andThen(lengthFunction);
        System.out.println(integerIntegerFunction.apply(12));

        Function<String, String> compose = function.compose(lengthFunction);
        System.out.println(compose.apply("12312"));

        List<String> arrays= Arrays.asList("apple","banana","peer");
        //使用 identity，不改变流中的数据
        arrays.stream().map(Function.identity()).peek(System.out::println).forEach(word->word.length());

        //不进行任何转换的情况下将元素收集到集合中
        System.out.println(arrays.stream().collect(Collectors.toMap(Function.identity(), Function.identity())));

        //当你需要一个函数接口实现但不需要对输入进行任何修改时
        Function<String,String> function1=Function.identity();

        String hel ="ekwhe";
        System.out.println(function1.apply(hel));
    }
}
