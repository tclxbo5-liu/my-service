package com.elong.hotel.hotelmy.utils.io;


import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.client.utils.DateUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import sun.security.provider.MD5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 同步非阻塞，通过不断地进行I/o系统调用轮巡，耗费cpu资源
 */
public class NioUtils {
    //I/o多路复用
    //线程首先发起 select 调用，询问内核数据是否准备就绪，
    // 等内核把数据准备好了，用户线程再发起 read 调用。read 调用的过程（数据从内核空间 -> 用户空间）还是阻塞的
//    select 调用：内核提供的系统调用，它支持一次查询多个系统调用的可用状态。几乎所有的操作系统都支持
//    。epoll 调用：linux 2.6 内核，属于 select 调用的增强版本，优化了 IO 的执行效率。

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
//        CharBuffer allocate = CharBuffer.allocate(10);
//        allocate.put('A');
//        allocate.put('B');
//        printState(allocate);
//
//        allocate.flip();
//        System.out.println("调用flip()方法后的状态：");
//        printState(allocate);

//        channelTest();

        Date date = DateUtils.parseDate("2024-07-13 00:00:00", new String[]{"yyyy-mm-dd hh:MM:ss"});
        Date date2 = DateUtils.parseDate("2024-07-03 00:00:00", new String[]{"yyyy-mm-dd hh:MM:ss"});
        System.out.println(date.getTime());
        System.out.println(date2.getTime());



        System.out.println(date);
        System.out.println(date+"date");
        MessageDigest md = MessageDigest.getInstance("MD5");
        MD5 md5 = new MD5();
        System.out.println(MD5Encoder.encode(date.toString().getBytes()));
        System.out.println(Md5Crypt.apr1Crypt(date.toString()));
        System.out.println(Md5Crypt.apr1Crypt(date2.toString()));



    }
//【基于 Buffer】
//    传统的 I/O 是面向字节流或字符流的，以流式的方式顺序地从一个 Stream 中读取一个或多个字节, 因此也就不能随意改变读取指针的位置。
//    在 NIO 中，抛弃了传统的 I/O 流，而是引入了 Channel 和 Buffer 的概念。在 NIO 中，
//    只能从 Channel 中读取数据到 Buffer 中或将数据从 Buffer 中写入到 Channel。
//                     基于 Buffer 操作不像传统 IO 的顺序操作，NIO 中可以随意地读取任意位置的数据。
    // 打印buffer的capacity、limit、position、mark的位置
    private static void printState(CharBuffer buffer) {
        System.out.print("capacity: " + buffer.capacity());
        System.out.print(", limit: " + buffer.limit());
        System.out.print(", position: " + buffer.position());
        System.out.print(", mark 开始读取的字符: " + buffer.mark());
        System.out.println("\n");
    }

    public static void channelTest() throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        //新接连池
        List<SocketChannel> socketChannels = new ArrayList<>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8110));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        while (true) {
            //探测新连接，由于设置了非阻塞，这里即使没有新连接也不会阻塞，而是直接返回null
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("新链接进入");
                socketChannel.configureBlocking(false);
                socketChannels.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = socketChannels.iterator();
            while (iterator.hasNext()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                SocketChannel next = iterator.next();
                //读取客户端数据 当客户端数据没有写入完成的时候也不会阻塞，长度为0
                int read = next.read(byteBuffer);
                if (read > 0) {
                    byte[] array = byteBuffer.array();
                    String s = new String(array);
                    System.out.println(s);
                } else if (read == -1) {
                    iterator.remove();
                    System.out.println("连接断开");
                }
            }
        }
    }


    public static void channelSelectTest() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //定义个serverSocketChannel
        serverSocketChannel.bind(new InetSocketAddress(4555));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        //将serverSocketChannel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 轮巡,阻塞等待需要处理的事件发生
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将该客户端连接注册进选择器 并关注读事件
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("accept Conecttion from " + socketChannel.getRemoteAddress());
                } else if (selectionKey.isReadable()) {
                    ByteBuffer allocate = ByteBuffer.allocate(128);
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    int read = channel.read(allocate);
                    if (read > 0) {
                        System.out.println(new String(allocate.array()));
                    } else if (read == -1) {
                        System.out.println("断开连接");
                        channel.close();
                    }
                }
                iterator.remove();
            }
        }
    }


}
