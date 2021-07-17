package com.company.task;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

import com.company.message.Header;
import com.company.message.PingRequest;
import com.company.net.Skynet;
import com.company.util.Constant;

public class PingTask extends TimerTask {
    Skynet skynet;
    String str = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(str);

    public PingTask(Skynet s) {
        this.skynet = s;
    }

    @Override
    public void run() {
        try {
            String now2 = this.dateFormat.format(System.currentTimeMillis());
//            System.out.println("该起床了" + now2);
            short ping_c = Constant.MSG_PING_REQUEST;
            PingRequest ping = new PingRequest();
            byte[] req_bytes = ping.Encode();
            Header header = new Header(ping_c);
            header.payloadLength = req_bytes.length;
            byte[] headBytes = header.Encode();
            skynet.SendMsg(headBytes, req_bytes);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
