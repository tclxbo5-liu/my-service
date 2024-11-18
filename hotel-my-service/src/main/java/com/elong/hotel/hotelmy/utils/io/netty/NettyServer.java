package com.elong.hotel.hotelmy.utils.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }
    
    public void start() throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventExecutors, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {

                socketChannel.pipeline().addLast(new StringDecoder());
                socketChannel.pipeline().addLast(new StringEncoder());
                socketChannel.pipeline().addLast(new SimplerServerHandler());
            }
        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
        //绑定端口开始接收连接
        ChannelFuture sync = serverBootstrap.bind(port).sync();
        System.out.println("Server started at port" + port);
        //等待服务器，socket关闭
        sync.channel().closeFuture().sync();
    }    
}
