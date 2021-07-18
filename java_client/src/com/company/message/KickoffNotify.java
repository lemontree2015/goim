package com.company.message;

import java.io.DataInputStream;

public class KickoffNotify {
    public byte code;

    public KickoffNotify(byte co) {
        this.code = co;
    }

    public static KickoffNotify Decode(DataInputStream in) {
        KickoffNotify resp = null;
        try {
            byte code = in.readByte();
            resp = new KickoffNotify(code);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
