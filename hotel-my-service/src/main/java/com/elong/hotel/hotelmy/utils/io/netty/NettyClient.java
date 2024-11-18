package com.elong.hotel.hotelmy.utils.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
    private final String host;
    private final int port;


    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioServerSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new StringEncoder());
                }
            });
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            System.out.println("connected to server as " + host + ":" + port);
            sync.channel().closeFuture().sync();

        }finally {
            eventExecutors.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8080);
        nettyClient.start();
    }
}
