package com.ldh.length.field.unpacking.client.hander;


import com.ldh.length.field.unpacking.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.nio.charset.Charset;

/**
 * @Author: ldh Created on 2018/7/31
 */
public class ClientFieldEncoder extends  MessageToByteEncoder<Message> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
    if(null == msg){
      throw new Exception("msg is null");
    }
    String body = msg.getBody();
    byte[] bodyBytes = body.getBytes(Charset.forName("utf-8"));
    out.writeByte(msg.getType());
    out.writeByte(msg.getFlag());
    out.writeInt(bodyBytes.length);
    out.writeBytes(bodyBytes);
  }
}