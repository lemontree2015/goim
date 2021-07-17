package com.company.message;

import com.company.util.ByteUtils;

import java.io.DataInputStream;

public class Header {
    public int magic = 2147483647;
    public short version = 1;
    public short command = 0;
    public int payloadLength = 0;

    public Header(int m, short v, short c, int p) {
        this.magic = m;
        this.version = v;
        this.command = c;
        this.payloadLength = p;
    }

    public Header(short c) {
        this.command = c;
    }

    public byte[] Encode() {
        byte[] m_bytes = ByteUtils.IntToByte4(this.magic);
        byte[] v_bytes = ByteUtils.ShortToByte2(this.version);
        byte[] c_bytes = ByteUtils.ShortToByte2(this.command);
        byte[] p_bytes = ByteUtils.IntToByte4(this.payloadLength);
        byte[] head_1 = ByteUtils.byteMerger(m_bytes, v_bytes, c_bytes, p_bytes);
        return head_1;
    }

    public static Header Decode(DataInputStream in) {
        try {
            int m = in.readInt();
            short v = in.readShort();
            short c = in.readShort();
            int pl = in.readInt();
            Header head = new Header(m, v, c, pl);
            return head;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
