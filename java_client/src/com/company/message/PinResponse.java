package com.company.message;

import java.io.DataInputStream;

public class PinResponse {
    public byte code;

    public PinResponse(byte co) {
        this.code = co;
    }

    public static PinResponse Decode(DataInputStream in) {
        PinResponse resp = null;
        try {
            byte code = in.readByte();
            resp = new PinResponse(code);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
