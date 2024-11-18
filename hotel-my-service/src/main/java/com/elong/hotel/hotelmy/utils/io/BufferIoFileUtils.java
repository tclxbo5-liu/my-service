package com.elong.hotel.hotelmy.utils.io;

import java.io.*;

public class BufferIoFileUtils {

    /**
     * BufferedInputStream 从源头（通常是文件）读取数据（字节信息）到内存的过程中不会一个字节一个字节的读取
     * ，而是会先将读取到的字节存放在缓存区，并从内部缓冲区中单独读取字节。这样大幅减少了 IO 次数，提高了读取效率。
     * BufferedInputStream 内部维护了一个缓冲区，这个缓冲区实际就是一个字节数组，通过阅读
     * @param filePath
     * @param targetFilePath
     *
     * BufferedOutputStream（字节缓冲输出流）BufferedOutputStream 将数据（字节信息）写入到目的地（通常是文件）
     * 的过程中不会一个字节一个字节的写入，而是会先将要写入的字节存放在缓存区，并从内部缓冲区中单独写入字节。
     * 这样大幅减少了 IO 次数，提高了读取效率
     */
    public static void copyWithBuffer(String filePath, String targetFilePath) {
        long start = System.currentTimeMillis();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(targetFilePath))) {
            byte[] bytes =new byte[100];
            int i;

//            while ((i = bufferedInputStream.read(bytes)) != -1) {
//                bufferedOutputStream.write(bytes,0,i);
//            }
            while ((i=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        不需要显式调用close
//        由于 try-with-resources 已经自动处理了资源的关闭，你不需要显式地调用 close 方法。
//        这不仅简化了代码，还减少了由于忘记关闭资源而导致的资源泄漏问题。
        //释放资源的好处
//        1、释放系统资源
//        通常依赖于底层系统资源，如文件句柄或者网络连接。如果这些资源不被及时释放，可能会导致资源耗尽，进而导致程序或系统无法分配新的资源。
//        2、避免文件锁定
//        如果你正在读取一个文件，但在不关闭流的情况下尝试删除或修改该文件，可能会遇到文件锁定问题。关闭流会解除对文件的锁定，使得文件可以被其他操作访问。
//        3. 保证数据完整性
//        在某些情况下，特别是涉及到网络流或者压缩流（如GZIPInputStream），关闭流可以确保所有数据都被正确读取和解压缩，避免数据丢失或损
        System.out.println(("缓冲区消耗时间：" + (System.currentTimeMillis() - start)));
    }

    public static void copyWithNoBuffer(String originPath, String targetPath) {
        Long start =System.currentTimeMillis();
        try (FileInputStream fileInputStream = new FileInputStream(originPath);
             FileOutputStream fileOutputStream = new FileOutputStream(targetPath);
        ) {
            int i ;
            byte[] content =new byte[100];
//            while (   (i=fileInputStream.read(content))!=-1){
//                fileOutputStream.write(content,0,i);
//            }
            while ((i=fileInputStream.read())!=-1){
                fileOutputStream.write(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(("没有缓冲区的耗时" + (System.currentTimeMillis() - start)));
    }

    public static void main(String[] args) {
        String originPath="/Users/bobo/Downloads/七周七语言 (1).pdf";
        String targetPath="/Users/bobo/Downloads/七周七语言 (2).pdf";
        copyWithBuffer(originPath,targetPath);
        copyWithNoBuffer(originPath,targetPath);
    }

}
