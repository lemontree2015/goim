package com.company;

import java.util.Timer;

import com.company.message.EnterChatRoomRequest;
import com.company.task.MsgTask;
import com.company.task.PingTask;
import com.company.message.Header;
import com.company.message.AuthRequest;
import com.company.net.Skynet;
import com.company.task.ReadThread;
import com.company.task.RoomPingTask;
import com.company.util.Constant;

public class Client {

    //函数入口
    public static void main(String[] args) {
        try {
            short echo_c = 4099;
            short auth_c = Constant.MSG_AUTH_REQUEST;
//            String Msg = "需要服务器的正确的IP地址和端口号";
//            String Msg = "需要服务器的正确的IP地址和端口号";
//            EchoRequest echo = new EchoRequest(Msg);
            String account = "test01";
            AuthRequest auth = new AuthRequest(account);
//
            byte[] req_bytes = auth.Encode();
            Header header = new Header(auth_c);
            header.payloadLength = req_bytes.length;
            byte[] headBytes = header.Encode();

            Skynet skynet = new Skynet("127.0.0.1", 7777);
            skynet.SendMsg(headBytes, req_bytes);

            String roomId = "r0001";
            short enter_c = Constant.MSG_ENTER_CHATROOM_REQUEST;
            EnterChatRoomRequest enterReq = new EnterChatRoomRequest(roomId);
            byte[] enterBytes = enterReq.Encode();
            Header enterHeader = new Header(enter_c);
            enterHeader.payloadLength = enterBytes.length;
            byte[] enterHeadBytes = enterHeader.Encode();
            skynet.SendMsg(enterHeadBytes, enterBytes);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new PingTask(skynet), 3000, 3000);//单位是毫秒
            timer.schedule(new RoomPingTask(skynet), 3000);//单位是毫秒
//            timer.scheduleAtFixedRate(new MsgTask(skynet, "test01", "test02"), 2000, 4000);
            ReadThread readThread = new ReadThread(skynet);
            readThread.start();
            // skynet.socket.close();
        } catch (Exception e) {
//            System.out.println(e);
        }
    }
}
