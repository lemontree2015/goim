package com.company.message;

import java.io.DataInputStream;

public class EnterChatRoomNotify {
    public String roomId;
    public ChatUser enterUser;

    public EnterChatRoomNotify(String rid, ChatUser u) {
        this.roomId = rid;
        this.enterUser = u;
    }

    public static EnterChatRoomNotify Decode(DataInputStream in) {
        EnterChatRoomNotify resp = null;
        try {
            String roomId = in.readUTF();
            ChatUser u = ChatUser.Decode(in);
            System.out.println("u.Account = " + u.Account);
            resp = new EnterChatRoomNotify(roomId, u);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
