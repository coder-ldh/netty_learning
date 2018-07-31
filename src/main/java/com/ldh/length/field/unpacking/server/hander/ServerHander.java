package com.ldh.length.field.unpacking.server.hander;

import com.ldh.length.field.unpacking.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ldh
 */
public class ServerHander extends SimpleChannelInboundHandler{

  private  final Logger logger = LoggerFactory.getLogger(ServerHander.class);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof Message){
      Message message = (Message) msg;
      logger.info("服务器接收到的信息{}"+ message.toString());
    }
  }
}