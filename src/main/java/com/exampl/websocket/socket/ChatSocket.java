package com.exampl.websocket.socket;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chatroom")
@Component
public class ChatSocket {

	private Session session;
    private static CopyOnWriteArraySet<ChatSocket> set = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        set.add(this);
        System.out.println("socket open");
    }

    @OnClose
    public void onClose() {
        set.remove(this);
        System.out.println("socket close");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("from client:" + message);
        for (ChatSocket socket : set) {
            try {
                socket.sendMessage(message);
            } catch (IOException e) {
            }
        }
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("error occurred");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
