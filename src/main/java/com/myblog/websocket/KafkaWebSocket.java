//package com.myblog.websocket;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import javax.websocket.Session;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//
///**
// * @author Zephery
// * @since 2018/1/15 19:44
// */
//public class KafkaWebSocket extends TextWebSocketHandler {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(KafkaWebSocket.class);
//    public static CopyOnWriteArraySet<KafkaWebSocket> wbSockets = new CopyOnWriteArraySet<>(); //此处定义静态变量，以在其他方法中获取到所有连接
//    private Session session;
//
//    //接收文本消息，并发送出去
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        while (true) {
//            super.handleTextMessage(session, message);
//        }
//    }
//}