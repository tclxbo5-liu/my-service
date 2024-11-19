package com.elong.hotel.algorithm.str;

/**
 * @author bobo
 * @date 2024/11/19 16:07
 **/
public class StringT {
    public static void main(String[] args) {
        StringObject stringObject = new StringObject();
        stringObject.setName("123");
        changeName(stringObject);
        System.out.println(stringObject.getName());


        String str = "123";
//        即方法操作的是参数变量（也就是原型变量的一个值的拷贝）
        changeStringName(str);
        System.out.println(str);

    }

    public static void changeName(StringObject stringObject){
        stringObject.setName("456");
    }

    public static void changeStringName(String str){
        str = "456";
    }

    static class  StringObject{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
