package com.ldh.marshalling.server.hander;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: ldh
 */
public class ServerMarshallingHander extends ChannelOutboundHandlerAdapter {

  @Override
  public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
    super.deregister(ctx, promise);
  }

  @Override
  public void read(ChannelHandlerContext ctx) throws Exception {
    super.read(ctx);
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
      throws Exception {
    super.write(ctx, msg, promise);
  }
}