package com.zigbee.webSocket;

import com.zigbee.common.ServerResponse;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Administrator on 2017/9/6.
 */

@ServerEndpoint("/websocket/zigbee")
@Component
public class ZigbeeWebSocket {
    private static int onlineCount = 0;
    private Session session;
    private static CopyOnWriteArraySet<ZigbeeWebSocket> zigbeeWebSocketSet = new CopyOnWriteArraySet<ZigbeeWebSocket>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        zigbeeWebSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }


    @OnMessage
    public void onMessage(String message, Session session) {
    }

    @OnClose
    public void onClose() {
        zigbeeWebSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    public void sendMessage(String message) throws IOException {
        System.out.println("发送消息:" + message);
        this.session.getBasicRemote().sendText(message);
    }
    public void sendMessage(ServerResponse response) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(response);
    }
    // 遍历发消息
    public static void bordercastMessage(String message) throws IOException {
        System.out.println("当前socket池人数:" + zigbeeWebSocketSet.size());
        //  遍历websoketSet
        for (ZigbeeWebSocket session : zigbeeWebSocketSet){
            session.sendMessage(message);
        }
    }
    // 遍历发消息
    public static void bordercastMessage(ServerResponse response) throws IOException, EncodeException {
        System.out.println("当前socket池人数:" + zigbeeWebSocketSet.size());
        //  遍历websoketSet
        for (ZigbeeWebSocket session : zigbeeWebSocketSet){
            session.sendMessage(response);
        }
    }
    public static synchronized CopyOnWriteArraySet getZigbeeWebSocketSet() {
        return zigbeeWebSocketSet;
    }
    public static synchronized int getOnlineCount() {
        return ZigbeeWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ZigbeeWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ZigbeeWebSocket.onlineCount--;
    }
}
