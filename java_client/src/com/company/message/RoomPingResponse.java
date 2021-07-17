package com.company.message;

import java.io.DataInputStream;

public class RoomPingResponse {
    public byte code;

    public RoomPingResponse(byte co) {
        this.code = co;
    }

    public static RoomPingResponse Decode(DataInputStream in) {
        RoomPingResponse resp = null;
        try {
            byte code = in.readByte();
            resp = new RoomPingResponse(code);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
