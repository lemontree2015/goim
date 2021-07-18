package com.company.message;

import com.company.util.ByteUtils;

import java.io.IOException;

public class AuthRequest {
    String Account;
    String Token;
    long Timestamp;
    String Version = "1.0";

    public AuthRequest(String account, String tk) {
        this.Account = account;
        this.Token = tk;
        this.Timestamp = (long) (System.currentTimeMillis() / 1000);
    }

    public byte[] Encode() throws IOException {
        byte[] account_bytes = ByteUtils.StringToBytes(this.Account);
        byte[] token_bytes = ByteUtils.StringToBytes(this.Token);
        byte[] time_bytes = ByteUtils.longToBytes(this.Timestamp);
        byte[] version_bytes = ByteUtils.StringToBytes(this.Version);
        byte[] bytes = ByteUtils.byteMerger(account_bytes, token_bytes, time_bytes, version_bytes);
        return bytes;
    }
}
