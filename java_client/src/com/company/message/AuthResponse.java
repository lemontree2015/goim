package com.company.message;

import com.company.util.ByteUtils;

import java.io.DataInputStream;

public class AuthResponse {
    public byte code;
    public String account;

    public AuthResponse(byte co, String a) {
        this.code = co;
        this.account = a;
    }

    public static AuthResponse Decode(DataInputStream in) {
        AuthResponse resp = null;
        try {
            byte code = in.readByte();
            String body = in.readUTF();
            resp = new AuthResponse(code, body);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
