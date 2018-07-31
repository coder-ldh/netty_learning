package com.ldh.simple.unpacking.server.hander;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ldh
 */
public  class ServerUnpackingHandler extends SimpleChannelInboundHandler {

    private  final Logger logger = LoggerFactory.getLogger(ServerUnpackingHandler.class);

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body=(String)msg;
        logger.info("服务端收到："+body+"，次数:"+ ++counter);
    }

  /**
   * 异常处理
   * @param context
   * @param cause
   */
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
    }
}