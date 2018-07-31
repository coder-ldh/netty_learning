package com.ldh.length.field.unpacking.server.hander;

import com.ldh.length.field.unpacking.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: ldh Created on 2018/7/31
 * 此 hander 将数据帧解码为对象Message
 */
public class ServerFieldHandler extends LengthFieldBasedFrameDecoder {

  /**
   * 判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是 byte+byte+int = 1+1+4 = 6
   */
  private static final int HEADER_SIZE = 6;

  private byte type;

  private byte flag;

  private int length;

  private String body;

  public ServerFieldHandler(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
      int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
    super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment,
        initialBytesToStrip,
        failFast);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    if (in == null) {
      return null;
    }
    if (in.readableBytes() < HEADER_SIZE) {
      throw new Exception("可读信息段比头部信息都小，你在逗我？");
    }
    //注意在读的过程中，readIndex的指针也在移动
    type = in.readByte();
    flag = in.readByte();
    length = in.readInt();

    if (in.readableBytes() < length) {
      throw new Exception("body字段你告诉我长度是"+length+",但是真实情况是没有这么多，你又逗我？");
    }
    ByteBuf buf = in.readBytes(length);
    byte[] req = new byte[buf.readableBytes()];
    buf.readBytes(req);
    body = new String(req, "UTF-8");
    Message message = new Message(type,flag,length,body);
    return message;
  }
}