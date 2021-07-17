package com.company.message;

import com.company.util.ByteUtils;

import java.io.IOException;

public class RoomPingRequest {
    String RoomId;

    public RoomPingRequest(String rid) {
        this.RoomId = rid;
    }

    public byte[] Encode() throws IOException {
        byte[] d_bytes = ByteUtils.StringToBytes(this.RoomId);
        return d_bytes;
    }
}
