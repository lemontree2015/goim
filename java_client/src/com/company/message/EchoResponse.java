package com.company.message;

import com.company.util.ByteUtils;

import java.io.DataInputStream;

public class EchoResponse {
    public byte code;
    public String body;

    public EchoResponse(byte co, String s) {
        this.code = co;
        this.body = s;
    }

    public static EchoResponse Decode(DataInputStream in) {
        EchoResponse resp = null;
        try {
            byte code = in.readByte();
            String body = in.readUTF();
            resp = new EchoResponse(code, body);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
