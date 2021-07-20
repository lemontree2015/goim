package com.company.message;

import java.io.DataInputStream;

public class LeaveChatRoomResponse {
    public byte code;
    public String roomId;

    public LeaveChatRoomResponse(byte co, String rId) {
        this.code = co;
        this.roomId = rId;
    }

    public static LeaveChatRoomResponse Decode(DataInputStream in) {
        LeaveChatRoomResponse resp = null;
        try {
            byte code = in.readByte();
            String roomId = in.readUTF();
            resp = new LeaveChatRoomResponse(code, roomId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
