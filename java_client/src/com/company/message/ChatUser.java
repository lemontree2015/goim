package com.company.message;

import java.io.DataInputStream;

public class ChatUser {
    public String Account;

    public ChatUser(String ac) {
        this.Account = ac;
    }

    public static ChatUser Decode(DataInputStream in) {
        ChatUser cu = null;
        try {
            short bufLen = in.readShort();
            String ac = in.readUTF();
            cu = new ChatUser(ac);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cu;
    }

    @Override
    public String toString() {
        return this.Account;
    }
}
