package com.elong.hotel.algorithm.str;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bobo
 * @date 2024/11/18 19:17
 **/
public class StringReverse {

    public static String reverseString(String str){
        if (StringUtils.isEmpty(str)){
            return str;
        }
        //字符串反转，用builder的reverse方法，因为有些unicode字符需要用两个字表示，
        // ，，简称代理对，，，高代理和低代理，，，，，所以这些要单独处理
//        高代理（High Surrogate）：范围是 U+D800 到 U+DBFF。
//        低代理（Low Surrogate）：范围是 U+DC00 到 U+DFFF。
//        一个代理对由一个高代理和一个低代理组成，用于表示一个补充字符。
        StringBuilder stringBuilder = new StringBuilder(str);
        StringBuilder reverse = stringBuilder.reverse();
        return reverse.toString();
    }

    public static void main(String[] args) {
        String str=null;
        System.out.println(reverseString(str));
    }
}
