package com.company.message;

import com.company.util.ByteUtils;

import java.io.IOException;

public class MessageRequest {
    public String msgId;
    public byte msgType;
    public String content;
    public String from;
    public String to;
    public String extend;

    public MessageRequest(String mid, byte mType, String ct, String f, String t, String ex) {
        this.msgId = mid;
        this.msgType = mType;
        this.content = ct;
        this.from = f;
        this.to = t;
        this.extend = ex;
    }

    public byte[] Encode() throws IOException {
        byte[] msgId_bytes = ByteUtils.StringToBytes(this.msgId);
        byte[] msgType_bytes = new byte[]{this.msgType};
        //        msgType_bytes[0] = this.msgType;
        byte[] content_bytes = ByteUtils.StringToBytes(this.content);
        byte[] from_bytes = ByteUtils.StringToBytes(this.from);
        byte[] to_bytes = ByteUtils.StringToBytes(this.to);
        byte[] extend_bytes = ByteUtils.StringToBytes(this.extend);

        byte[] bytes = ByteUtils.byteMerger(msgId_bytes, msgType_bytes, content_bytes, from_bytes, to_bytes,
                extend_bytes
        );
        return bytes;
    }
}
