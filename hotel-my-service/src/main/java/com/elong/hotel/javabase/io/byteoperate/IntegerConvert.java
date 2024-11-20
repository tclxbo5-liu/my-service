package com.elong.hotel.javabase.io.byteoperate;

/**
 * @author bobo
 * @date 2024/11/19 19:15
 **/
public class IntegerConvert {
    public static void main(String[] args) {
        byte[] bytes = intConvertToByte(12321312);
        System.out.println(Integer.toBinaryString(bytes[0]));
        System.out.println(Integer.toBinaryString(bytes[1]));
        System.out.println(Integer.toBinaryString(bytes[2]));
        System.out.println(Integer.toBinaryString(bytes[3]));
        byte b = bytes[2];
        System.out.println(byteToBinary(b));
        System.out.println(Integer.toBinaryString(Byte.toUnsignedInt(bytes[2])));
        System.out.println(bytes[0] + "....."+bytes[1]+"....."+ bytes[2]+"....."+ bytes[3] + "...");
        System.out.println(byteConvertToInt(bytes));
    }

    public static byte[] intConvertToByte(int n){
        byte[] bytes=new byte[4];
        for (int i = 0; i < bytes.length ; i++) {
            bytes[i] = (byte) ((n>>(8*i))&0xff);
        }
        return bytes;
    }

    public static int byteConvertToInt(byte[] bytes){
        int length = bytes.length;
        int inta= 0;
        for (int i = 0; i < length; i++) {
            inta += ((bytes[i] & 0xff )<< i * 8);
        }
        return inta;
    }

    public static  String byteToBinary(byte byt){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 7; i >=0 ; i--) {
            stringBuilder.append(byt>>i&1);
        }
        return stringBuilder.toString();
    }
}
