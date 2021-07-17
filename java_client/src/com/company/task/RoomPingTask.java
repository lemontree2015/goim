package com.company.task;

import com.company.message.Header;
import com.company.message.PingRequest;
import com.company.message.RoomPingRequest;
import com.company.net.Skynet;
import com.company.util.Constant;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class RoomPingTask extends TimerTask {
    Skynet skynet;
    String str = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(str);

    public RoomPingTask(Skynet s) {
        this.skynet = s;
    }

    @Override
    public void run() {
        try {
            short code = Constant.MSG_TOUCH_CHATROOM_REQUEST;
            RoomPingRequest ping = new RoomPingRequest("r0001");
            byte[] req_bytes = ping.Encode();
            Header header = new Header(code);
            header.payloadLength = req_bytes.length;
            byte[] headBytes = header.Encode();
            skynet.SendMsg(headBytes, req_bytes);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
