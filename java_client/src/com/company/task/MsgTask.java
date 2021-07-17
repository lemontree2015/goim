package com.company.task;

import com.company.message.Header;
import com.company.message.MessageRequest;
import com.company.message.PingRequest;
import com.company.net.Skynet;
import com.company.util.Constant;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class MsgTask extends TimerTask {
    Skynet skynet;
    String from;
    String to;
    String str = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(str);

    public MsgTask(Skynet s, String f, String t) {
        this.skynet = s;
        this.from = f;
        this.to = t;
    }

    @Override
    public void run() {
        try {
            short code = Constant.MSG_MESSAGE_REQUEST;
            String now2 = this.dateFormat.format(System.currentTimeMillis());
            int sendTime = (int) (System.currentTimeMillis() / 1000);
            String msgId = "msg" + sendTime;
            byte msgType = 1;
            String content = "现在时间是" + now2;
            String from = this.from;
            String to = this.to;

            String extend = "no extends";
            MessageRequest msgReq = new MessageRequest(msgId, msgType, content, from, to, extend);
            byte[] msg_req_bytes = msgReq.Encode();
            Header msg_header = new Header(code);
            msg_header.payloadLength = msg_req_bytes.length;
            byte[] msgHeadBytes = msg_header.Encode();
            skynet.SendMsg(msgHeadBytes, msg_req_bytes);
            System.out.println(now2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
