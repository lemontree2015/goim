package com.company.message;

import java.io.DataInputStream;
import java.util.List;
import java.util.ArrayList;

public class EnterChatRoomResponse {
    public byte code;
    public List<ChatUser> users = new ArrayList<>();

    public EnterChatRoomResponse(byte co) {
        this.code = co;
    }

    public static EnterChatRoomResponse Decode(DataInputStream in) {
        EnterChatRoomResponse resp = null;
        try {
            byte code = in.readByte();
            short userCount = in.readShort();
            resp = new EnterChatRoomResponse(code);
            for (int i = 0; i < userCount; i++) {
                ChatUser u = ChatUser.Decode(in);
                resp.users.add(u);
//                resp.users[i] = u;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
