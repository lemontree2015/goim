package com.company.message;

import com.company.util.ByteUtils;

import java.io.IOException;

public class AuthRequest {
    String Account;
    String Token = "token";
    String Version = "1.0";

    public AuthRequest(String account) {
        this.Account = account;
    }

    public byte[] Encode() throws IOException {
        byte[] account_bytes = ByteUtils.StringToBytes(this.Account);
        byte[] token_bytes = ByteUtils.StringToBytes(this.Token);
        byte[] version_bytes = ByteUtils.StringToBytes(this.Version);
        byte[] bytes = ByteUtils.byteMerger(account_bytes, token_bytes, version_bytes);
        return bytes;
    }
}
