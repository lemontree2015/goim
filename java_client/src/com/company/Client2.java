package com.company;

import com.company.message.AuthRequest;
import com.company.message.EnterChatRoomRequest;
import com.company.message.Header;
import com.company.net.Skynet;
import com.company.task.MsgTask;
import com.company.task.PingTask;
import com.company.task.ReadThread;
import com.company.task.RoomPingTask;
import com.company.util.ByteUtils;
import com.company.util.Constant;

import java.util.Timer;

public class Client2 {
    //函数入口
    public static void main(String[] args) {
        try {
            short auth_c = 4102;
            String account = "test02";
            long timestamp = (long) (System.currentTimeMillis() / 1000);
            String authStr = account + timestamp + Constant.AUTH_SECRET;
            String authToken = ByteUtils.Md5(authStr);
            AuthRequest auth = new AuthRequest(account, authToken);
            byte[] req_bytes = auth.Encode();
            Header header = new Header(auth_c);
            header.payloadLength = req_bytes.length;
            byte[] headBytes = header.Encode();

            Skynet skynet = new Skynet("127.0.0.1", 7778);
            skynet.SendMsg(headBytes, req_bytes);

            String roomId = "r0001";
            short enter_c = 4127;
            EnterChatRoomRequest enterReq = new EnterChatRoomRequest(roomId);
            byte[] enterBytes = enterReq.Encode();
            Header enterHeader = new Header(enter_c);
            enterHeader.payloadLength = enterBytes.length;
            byte[] enterHeadBytes = enterHeader.Encode();
            skynet.SendMsg(enterHeadBytes, enterBytes);

            Timer timer = new Timer();
            //延迟3秒后执行任务
            timer.scheduleAtFixedRate(new PingTask(skynet), 3000, 3000);
            timer.scheduleAtFixedRate(new RoomPingTask(skynet), 3000, 6000);//单位是毫秒
//            timer.scheduleAtFixedRate(new MsgTask(skynet, "test02", "test01"), 4000, 8000);
            ReadThread readThread = new ReadThread(skynet);
            readThread.start();
        } catch (Exception e) {
//            System.out.println(e);
        }
    }
}
