//package com.myblog.log;
//
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import com.myblog.util.IPUtils;
//
///**
// * @author wenzhihuai
// * @since 2018/4/29 16:18
// */
//public class JsonFormatter implements Formatter {
//    private static final String QUOTE = "\"";
//    private static final String COLON = ":";
//    private static final String COMMA = ",";
//    private static final String ip = IPUtils.getServerIp();
//
//    private boolean expectJson = false;
//
//    private static void fieldName(String name, StringBuilder sb) {
//        quoto(name, sb);
//        sb.append(COLON);
//    }
//
//    private static void quoto(String value, StringBuilder sb) {
//        sb.append(QUOTE);
//        sb.append(value);
//        sb.append(QUOTE);
//    }
//
//    @Override
//    public String format(ILoggingEvent event) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//
//        fieldName("level", sb);
//        quoto(event.getLevel().levelStr, sb);
//        sb.append(COMMA);
//
//        fieldName("logger", sb);
//        quoto(event.getLoggerName(), sb);
//        sb.append(COMMA);
//
//        fieldName("timestamp", sb);
//        sb.append(event.getTimeStamp());
//        sb.append(COMMA);
//
//        fieldName("ip", sb);
//        quoto(ip, sb);
//        sb.append(COMMA);
//
//        fieldName("message", sb);
//        if (this.expectJson) {
//            sb.append(event.getFormattedMessage());
//        } else {
//            quoto(event.getFormattedMessage(), sb);
//        }
//
//        sb.append("}");
//        return sb.toString();
//    }
//
//    private boolean isExpectJson() {
//        return expectJson;
//    }
//
//    public void setExpectJson(boolean expectJson) {
//        this.expectJson = expectJson;
//    }
//}