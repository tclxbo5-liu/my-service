package com.elong.hotel.hotelmy.utils.base;

/**
 * @author bobo
 * @date 2024/07/25 11:13
 **/
public class BitUtils {
    public static void main(String[] args) {
        int i=  2147483647;
        int j= -2147483648;
        int k = -10;
        System.out.println(Integer.toBinaryString(i).length());
        System.out.println(Integer.toBinaryString(i+j));
        System.out.println(Integer.toBinaryString(-1 >> 10));

    }
}
