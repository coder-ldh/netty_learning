package com.ldh.length.field.unpacking.client.hander;


import com.ldh.length.field.unpacking.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: ldh
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Message message = new Message(Message.USER_TYPE, Message.DATA_FLAG, "Hello,Netty".length(), "Hello,Netty");
    ctx.writeAndFlush(message);
  }
}