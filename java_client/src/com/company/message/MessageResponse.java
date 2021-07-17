package com.company.message;

import com.company.util.ByteUtils;

import java.io.DataInputStream;
import java.util.Arrays;

public class MessageResponse {
    public byte code;
    public String msgId;
    public byte msgType;
    public String content;

    public MessageResponse(byte co, String mid, byte mType, String c) {
        this.code = co;
        this.msgId = mid;
        this.msgType = mType;
        this.content = c;
    }

    public static MessageResponse Decode(DataInputStream in) {
        MessageResponse resp = null;
        try {
            byte code = in.readByte();
            String msgId = in.readUTF();
            byte msgType = in.readByte();
            String content = in.readUTF();
            resp = new MessageResponse(code, msgId, msgType, content);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
