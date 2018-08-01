package com.ldh.simple.unpacking.server;

import com.ldh.simple.unpacking.server.hander.ServerUnpackingHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author ldh
 */
public class UnpackingServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //以回车换行分隔 ByteToMessageDecoder
                            //ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //定长
                            //ch.pipeline().addLast(new FixedLengthFrameDecoder(23));
                            //指定分隔符
                            //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$$__".getBytes())));
                            //增加解码器
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast("unpacking",new ServerUnpackingHandler()
                            );
                        }
                    });
            ChannelFuture f = b.bind(8888).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}