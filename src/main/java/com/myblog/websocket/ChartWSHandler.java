package com.myblog.websocket;

import com.myblog.jmx.JMXClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

/**
 * @author Zephery
 * @since 2017/11/23 17:16
 */
public class ChartWSHandler implements WebSocketHandler {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(ChartWSHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String aa = JMXClient.getInstance().getCpuUsage();
        TextMessage message = new TextMessage(aa);
        webSocketSession.sendMessage(message);
    }

    @Override
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
        int i = 0;
        while (i < 1000) {
            String aa = JMXClient.getInstance().getCpuUsage();
            TextMessage message = new TextMessage(aa);
            wss.sendMessage(message);
            i++;
        }
        logger.info("关闭websocket" + wss.getRemoteAddress());
        wss.close();
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}