package com.company.net;

import com.company.message.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.company.util.Constant;

import java.util.Arrays;

public class Skynet {
    //定义一个Socket对象
    Socket socket = null;

    public Skynet(String host, int port) {
        try {
            //需要服务器的IP地址和端口号，才能获得正确的Socket对象
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);
            socket.setSoTimeout(3);
            Header head = Header.Decode(in);
            System.out.println("head.command = " + head.command + "body length = " + head.payloadLength);
            if (0 > head.command) {
                return;
            }
//            System.out.printf("m = %d ,v = %d, c= %d, pl = %d", head.command, head.version, head.command, head.payloadLength);
            switch (head.command) {
                case Constant.MSG_ECHO_RESPONSE:
                    EchoResponse echo = EchoResponse.Decode(in);
                    System.out.printf("code = %d, body = %s", echo.code, echo.body);
                    break;
                case Constant.MSG_PING_RESPONSE:
                    PinResponse ping = PinResponse.Decode(in);
//                    System.out.printf("ping code = %d", ping.code);
                    break;
                case Constant.MSG_KICKOFF_NOTIFY:
                    AuthResponse auth = AuthResponse.Decode(in);
                    System.out.printf("auth code = %d, account = %s", auth.code, auth.account);
                    break;
                case Constant.MSG_AUTH_RESPONSE:
//                    byte[] bodyBytes = new byte[head.payloadLength];
//                    in.read(bodyBytes, 0, head.payloadLength);
//                    System.out.printf(Arrays.toString(bodyBytes));
                    KickoffNotify kickoffNotify = KickoffNotify.Decode(in);
                    System.out.printf("您的账号已经在其它设备登陆 code = %d", kickoffNotify.code);
                    break;
                case Constant.MSG_MESSAGE_RESPONSE:
                    MessageResponse msg = MessageResponse.Decode(in);
                    System.out.printf("msg code = %d, msgId = %s, content = %s", msg.code, msg.msgId, msg.content);
                    break;
                case Constant.MSG_MESSAGE_NOTIFY:
                    MessageNotify msgNotify = MessageNotify.Decode(in);
                    System.out.printf("msgNotify roomId=%s,from = %s, to = %s, content = %s", msgNotify.roomId, msgNotify.from, msgNotify.to, msgNotify.content);
                    break;
                case Constant.MSG_ENTER_CHATROOM_RESPONSE:
//                    byte[] bodyBytes = new byte[head.payloadLength];
//                    in.read(bodyBytes, 0, head.payloadLength);
//                    System.out.printf(Arrays.toString(bodyBytes));
                    EnterChatRoomResponse enterChatResp = EnterChatRoomResponse.Decode(in);
                    System.out.printf("enterChatResp code = %d, users = %s", enterChatResp.code, enterChatResp.users);
                    System.out.println(enterChatResp.users);
                    break;
                case Constant.MSG_ENTER_CHATROOM_NOTIFY:
//                    byte[] bodyBytes = new byte[head.payloadLength];
//                    in.read(bodyBytes, 0, head.payloadLength);
//                    System.out.printf(Arrays.toString(bodyBytes));
                    EnterChatRoomNotify enterNotify = EnterChatRoomNotify.Decode(in);
                    System.out.printf("enterNotify...%s", enterNotify.enterUser.Account);
                    break;
                case Constant.MSG_TOUCH_CHATROOM_RESPONSE:
//                    byte[] bodyBytes = new byte[head.payloadLength];
//                    in.read(bodyBytes, 0, head.payloadLength);
//                    System.out.printf(Arrays.toString(bodyBytes));
                    RoomPingResponse roomPingResponse = RoomPingResponse.Decode(in);
                    System.out.printf("roomPingResponse...%d", roomPingResponse.code);
                    break;
                case Constant.MSG_LEAVE_CHATROOM_NOTIFY:
//                    byte[] bodyBytes = new byte[head.payloadLength];
//                    in.read(bodyBytes, 0, head.payloadLength);
//                    System.out.printf(Arrays.toString(bodyBytes));
                    LeaveChatRoomNotify LeaveNotify = LeaveChatRoomNotify.Decode(in);
                    System.out.printf("用户account=%s 离开了房间roomId=%s", LeaveNotify.account, LeaveNotify.roomId);
                    break;
                default:
                    System.out.println("no match ...");
            }
//            int first = inputStream.read();
//            if(first==-1){
//                return;
//            }
        } catch (Exception e) {
//            System.out.println(e);
        }
    }

    public void SendMsg(byte[] headBytes, byte[] bodyBytes) {
        try {
            DataOutputStream outStream = new DataOutputStream(this.socket.getOutputStream());
            outStream.write(headBytes);
            outStream.write(bodyBytes);
            outStream.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
