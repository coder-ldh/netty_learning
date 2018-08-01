package com.ldh.length.field.unpacking.message;

import java.io.Serializable;

/**
 * @Author: ldh
 */
public class Message implements Serializable{

  /**
   *   +----------+------+---------+--------+
   *    |   类型    |   头    |    长度   |    内容    |
   *   |  type    |  flag  |  length |   body   |
   *+-------+-------+--------+--------+
   */

  /**
   * 用户系统
   */
  public static byte USER_TYPE = 0xA;

  /**
   * pingpong机制处理心跳包
   */
  public static byte PING_PONG_FLAG = 0x0;

  /**
   * 超时包
   */
  public static byte TIME_OUT_FLAG = 0x1;

  /**
   * 一般业务数据
   */
  public static byte DATA_FLAG = 0x2;

  /**
   * 类型  系统编号 0xA 表示用户系统，0xB 表示B系统
   */
  private byte type;

  /**
   * 信息标志  0x0 表示心跳包    0x1 表示超时包  0x2 业务信息包
   */
  private byte flag;

  private int length;

  private String body;

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public byte getFlag() {
    return flag;
  }

  public void setFlag(byte flag) {
    this.flag = flag;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Message(byte type, byte flag, int length, String body) {
    this.type = type;
    this.flag = flag;
    this.length = length;
    this.body = body;
  }

  @Override
  public String toString() {
    return "Message{" +
        "type=" + type +
        ", flag=" + flag +
        ", length=" + length +
        ", body='" + body + '\'' +
        '}';
  }
}