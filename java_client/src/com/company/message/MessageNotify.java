package com.company.message;

import java.io.DataInputStream;

public class MessageNotify {
    public String msgId;
    public byte msgType;
    public String content;
    public String roomId;
    public String from;
    public String to;
    public long sendTime;
    public long sendTimeAck;
    public String extend;

    public MessageNotify(String mid, byte mType, String c, String rId, String f, String t, long st,
                         long stAck, String ex) {
        this.msgId = mid;
        this.msgType = mType;
        this.content = c;
        this.roomId = rId;
        this.from = f;
        this.to = t;
        this.sendTime = st;
        this.sendTimeAck = stAck;
        this.extend = ex;
    }

    public static MessageNotify Decode(DataInputStream in) {
        MessageNotify resp = null;
        try {
            String msgId = in.readUTF();
            byte msgType = in.readByte();
            String content = in.readUTF();
            String rId = in.readUTF();
            String from = in.readUTF();
            String to = in.readUTF();
            long sendTime = in.readLong();
            long sendTimeAck = in.readLong();
            String extend = in.readUTF();

            resp = new MessageNotify(msgId, msgType, content, rId, from, to, sendTime, sendTimeAck,
                    extend);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
