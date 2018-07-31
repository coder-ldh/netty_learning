package com.ldh.length.field.unpacking.message;

import java.io.Serializable;

/**
 * @Author: ldh Created on 2018/7/31
 */
public class Message implements Serializable{

  private byte type;

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