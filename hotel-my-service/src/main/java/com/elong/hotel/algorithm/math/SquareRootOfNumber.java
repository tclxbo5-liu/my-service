package com.elong.hotel.algorithm.math;

import java.util.Queue;

/**
 * @author bobo
 * @date 2024/11/19 13:55
 **/
public class SquareRootOfNumber {
    public static double sqrt(int number) {
        double t;
        double squareRoot = number / 2;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);
        return squareRoot;
    }


    public static double sqrtByMath(int number){
        return Math.sqrt(number);
    }
    //牛顿迭代法 x方-2 = 0的根

    public static double sqrtByMe(int number) {
        double squrtRoot;
        double xn1 = number / 2d;

        do {
            squrtRoot = xn1;
            xn1 = (squrtRoot + number / squrtRoot) / 2;
        } while (xn1 - squrtRoot != 0);
        return xn1;
    }


    public static void main(String[] args) {
        System.out.println("Square root of 2: " + sqrt(2));
        System.out.println("square root2 of 2:" + sqrtByMath(2));
        System.out.println("square root2 of 2:" + sqrtByMe(2));
    }
}
