package com.elong.hotel.algorithm.str;

/**
 * @author bobo
 * @date 2024/11/18 19:15
 **/
public class StringRotation {

    public boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return (s1+s2).contains(s2);
    }

    public static void main(String[] args) {
        StringRotation sr = new StringRotation();
        String s1 = "AACD";
        String s2 = "ACDA";
        if (sr.isRotation(s1, s2)) {
            System.out.println("Strings are rotations of each other");
        } else {
            System.out.println("Strings are not rotations of each other");
        }
    }
}
