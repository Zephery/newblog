//package com.myblog.config;
//
//import com.myblog.websocket.ChartWSHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//public class WebSocketConfig implements WebSocketConfigurer {
//    @Autowired
//    private ChartWSHandler chartWSHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(chartWSHandler, "/wscpu").setAllowedOrigins("*");
//    }
//
//}