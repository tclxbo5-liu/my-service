package com.elong.hotel.javabase.io.serializableT;


//import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author bobo
 * @date 2024/11/19 19:41
 **/
public class SerializableT {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
//            AObjcet aObjcet = new AObjcet();
//            ByteOutputStream byteOutput= new ByteOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutput);
//            objectOutputStream.writeObject(aObjcet);
//            objectOutputStream.close();
//            byteOutput.close();
//            String s = byteOutput.toString();
//            System.out.println(s);
        }
    }
}

class AObjcet implements Serializable {
    private String a = "bysocket";
    private String b = "likes";
    private String c = "java";
    private String d = "world";

    private int i = 100;
    private int j = 10;
    private long m = 100L;

    private boolean isA = true;
    private boolean isB = false;
    private boolean isC = false;

    private BObject aObject = new BObject();
    private BObject bObject = new BObject();
    private BObject cObject = new BObject();
    private BObject dObject = new BObject();

}
class BObject implements Serializable {

}