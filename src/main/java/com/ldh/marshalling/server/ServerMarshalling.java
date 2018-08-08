package com.ldh.marshalling.server;

import com.ldh.marshalling.server.hander.ServerMarshallingHander;
import com.ldh.marshalling.util.MarshallingCodeFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: ldh
 */
public class ServerMarshalling {

  public void start(int port) throws Exception {
    // 配置NIO线程组
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workGroup = new NioEventLoopGroup();
    try {
      // 服务器辅助启动类配置
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 128)
          .option(ChannelOption.SO_RCVBUF, 32 * 1024)
          .option(ChannelOption.SO_SNDBUF, 32 * 1024)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              // 添加Jboss的序列化Marshalling编解码工具
              ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
              ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
              //自定义的 hander
              ch.pipeline().addLast(new ServerMarshallingHander());
            }
          });
      // 绑定端口 同步等待绑定成功
      ChannelFuture f = b.bind(port).sync();
      // 等到服务端监听端口关闭
      f.channel().closeFuture().sync();
    } finally {
      // 优雅释放线程资源
      workGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}