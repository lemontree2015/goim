package com.company.message;

import com.company.util.ByteUtils;

import java.io.IOException;

public class EnterChatRoomRequest {
    String RoomId;

    public EnterChatRoomRequest(String rid) {
        this.RoomId = rid;
    }

    public byte[] Encode() throws IOException {
        byte[] d_bytes = ByteUtils.StringToBytes(this.RoomId);
        return d_bytes;
    }
}
