package com.elong.hotel.hotelmy.common.model.resp;

import lombok.Data;

import java.nio.charset.Charset;

/**
 * @author bobo
 */
@Data
public class ResponseBase<T>{
    int responseCode;
    String message;
    T data;


    public static void main(String[] args) {
        String str = "Árha Especial A, SHN Q. 1 Bloco F - Asa Norte, Brasília PRÉDIO DO HOTEL VISION";
        byte[] gb2312s = str.getBytes(Charset.forName("GB2312"));
        byte[] gbk = str.getBytes(Charset.forName("GBK"));
        String strs = new String(gb2312s, Charset.forName("GB2312"));
        String gbks = new String(gbk, Charset.forName("GBK"));
        System.out.println(strs);
        System.out.println(gbks);
        System.out.println(str.length());
    }
}
