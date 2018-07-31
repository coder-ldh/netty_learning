package com.ldh.length.field.unpacking.client.hander;


import com.ldh.length.field.unpacking.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: ldh Created on 2018/7/31
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Message customMsg = new Message((byte)0xAB, (byte)0xCD, "Hello,Netty".length(), "Hello,Netty");
    ctx.writeAndFlush(customMsg);
  }
}