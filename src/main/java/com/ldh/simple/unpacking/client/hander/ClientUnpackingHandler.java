package com.ldh.simple.unpacking.client.hander;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ldh
 */
public class ClientUnpackingHandler extends ChannelInboundHandlerAdapter  {

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接受的消息:"+msg);
    }

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String str="Hello Netty[1][2][3][4][5][6][7][8][9][10]\n";
        int length = 200;
        for(int j=0;j<length;j++){
            ctx.writeAndFlush(str);
        }
        ctx.fireChannelActive();
    }

  /**
   * 异常处理
   * @param ctx
   * @param cause
   */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    }
}