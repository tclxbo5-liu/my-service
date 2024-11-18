package com.elong.hotel.hotelmy.utils.io;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class IoFileUtils {

    public static String readFromFileWithInStream(String filePath) {
        String returnValue = "";
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[50];
            System.out.println(new String(bytes));
            StringBuilder builder = new StringBuilder();
            int available = fileInputStream.available();
            long skip = fileInputStream.skip(2);
            int i = 0;
            while ((i = fileInputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, "UTF-8"));
                builder.append(new String(bytes));
            }
            fileInputStream.close();
            returnValue = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static String readWithBufferStream(String filePath) {
        String returnValue = "";
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i;
            byte[] bytes = new byte[50];
            System.out.println(bufferedInputStream.available());
            StringBuilder stringBuilder = new StringBuilder();
            while ((i = bufferedInputStream.read(bytes)) != -1) {
                String string = new String(bytes, 0, i);
                stringBuilder.append(string);
            }
            returnValue = stringBuilder.toString();
            System.out.println(returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    public static void writeWithStream(String filePath, String content) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(filePath));

            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 字符流读取
     * @param filePath
     * @return
     */
    public static String readWithReader(String filePath) {
        String returnValue = "";
        try (FileReader fileReader = new FileReader(new File(filePath)); ){

            int i;
            char[] chars = new char[2];
            StringBuilder stringBuilder = new StringBuilder();
            while ((i = fileReader.read(chars)) != -1) {
                String temp = new String(chars,0,i);
                System.out.println(temp);
                stringBuilder.append(temp);
            }
            returnValue = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(returnValue);
        return returnValue;
    }

    /**
     * 字符流写入
     * @param filePath
     * @param writeContent
     */
    public static  void writeWithWriter(String filePath,String writeContent){
        FileWriter fileWriter =null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(writeContent);
            fileWriter.write('c');
//            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileWriter!=null){
                try {
                    fileWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * RandomAccessFile 的实现依赖于 FileDescriptor (文件描述符) 和 FileChannel （内存映射文件）
     * 从文件指定位置开始读取
     * @param filePath
     * @return
     */
    public static String readFileRadon(String filePath){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(filePath), "rw");
            randomAccessFile.seek(3);
            byte[] bytes = new byte[9];
            int i = 0;
            while ((i= randomAccessFile.read(bytes))!=-1){
                String string = new String(bytes,0 ,i);
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从指定位置插入数据
     * @param filePath
     * @param writeContent
     */
    public static void writeWithRandomAccessFile(String filePath,String writeContent){
        RandomAccessFile randomAccessFile=null;
        try {
            randomAccessFile  = new RandomAccessFile(new File(filePath), "rw");
            System.out.println(randomAccessFile.length());
            randomAccessFile.seek(3);
            System.out.println(randomAccessFile.getFilePointer());
            byte[] bytes = writeContent.getBytes(StandardCharsets.UTF_8);
            randomAccessFile.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (randomAccessFile!=null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testRandomAccessFile(String filePath) throws FileNotFoundException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(filePath), "rw");

    }

    public static void main(String[] args) {
        String path = "/Users/bobo/Downloads/name.txt";
//        readWithBufferStream(path);
//        writeWithStream("/Users/bobo/Downloads/name.txt", "");
//        readWithReader(path);
//        writeWithWriter(path,"这是写入的内容");
//        String s = readFileRadon(path);
        writeWithRandomAccessFile(path,"这是要插入的数据");
    }

}
