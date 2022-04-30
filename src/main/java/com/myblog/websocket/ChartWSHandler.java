//package com.myblog.websocket;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.metrics.MetricsEndpoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import javax.annotation.PostConstruct;
//import java.util.Objects;
//
///**
// * @author Zephery
// * @since 2017/11/23 17:16
// */
//@Slf4j
//@Component
//public class ChartWSHandler extends TextWebSocketHandler {
//    private static final String METRIC_NAME = "system.cpu.usage";
//
//    @Autowired
//    private MetricsEndpoint metricsEndpoint;
//
//    @PostConstruct
//    public void aa() {
//        Double systemCpuUsage = metricsEndpoint.metric(METRIC_NAME, null)
//                .getMeasurements()
//                .stream()
//                .filter(Objects::nonNull)
//                .findFirst()
//                .map(MetricsEndpoint.Sample::getValue)
//                .filter(Double::isFinite)
//                .orElse(0.0D);
//        log.info("");
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info("");
//        // The WebSocket has been closed
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // The WebSocket has been opened
//        // I might save this session object so that I can send messages to it outside of this method
//        log.info("");
//        // Let's send the first message
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        Double systemCpuUsage = metricsEndpoint.metric(METRIC_NAME, null)
//                .getMeasurements()
//                .stream()
//                .filter(Objects::nonNull)
//                .findFirst()
//                .map(MetricsEndpoint.Sample::getValue)
//                .filter(Double::isFinite)
//                .orElse(0.0D);
//        log.info("systemCpuUsage:{}", systemCpuUsage);
//        session.sendMessage(new TextMessage(String.valueOf(systemCpuUsage * 100)));
//    }
//}