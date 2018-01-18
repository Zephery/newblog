package com.myblog.kafka;

import com.myblog.websocket.KafkaWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author Zephery
 * @since 2018/1/13 19:09
 */
@Component
public class KafkaConsumer {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private WebSocketSession session;

    @KafkaListener(topics = "nginx-access-log")
    public void listen(String data) throws Exception {
        for (KafkaWebSocket socket : KafkaWebSocket.wbSockets) {
            socket.handleTextMessage(session, new TextMessage(data));
        }
    }
}