package com.company.message;

import java.io.DataInputStream;

public class LeaveChatRoomNotify {
    public String roomId;
    public String account;

    public LeaveChatRoomNotify(String rId, String ac) {
        this.roomId = rId;
        this.account = ac;
    }

    public static LeaveChatRoomNotify Decode(DataInputStream in) {
        LeaveChatRoomNotify resp = null;
        try {
            String roomId = in.readUTF();
            String account = in.readUTF();
            resp = new LeaveChatRoomNotify(roomId, account);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
