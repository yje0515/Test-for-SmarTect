package com.smartect.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    // Websocket 세션 저장 리스트
    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session)throws Exception{
        sessions.add(session);
        System.out.println("WebSocket 연결 성공! session Id : "+session.getId());
    }
    // 소켓 메세지 처리
    @Override
    public void  handleTextMessage(WebSocketSession session, TextMessage message){
        System.out.println("session Id : "+session.getId()+" 클라이언트 메세지 : "+message.getPayload());
    }
    //소켓 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)throws Exception{
        sessions.remove(session);
        session.sendMessage(new TextMessage(("WebSocket 연결 종료 status : "+status)));
    }

    // 스프링이 MQTT로 받은 이벤트를 WebSocket 클라이언트에게 발송할 때 호출됨
    public void broadcast(String message)throws Exception{
        for(WebSocketSession session : sessions){
            session.sendMessage(new TextMessage(message));
        }
    }


}
