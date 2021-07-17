package com.company.message;

import com.company.util.ByteUtils;

import java.io.IOException;

public class EchoRequest {
    String data;

    public EchoRequest(String d) {
        this.data = d;
    }

    public byte[] Encode() throws IOException {
        byte[] d_bytes = ByteUtils.StringToBytes(this.data);
        return d_bytes;
    }
}
