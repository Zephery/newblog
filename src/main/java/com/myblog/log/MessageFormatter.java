package com.myblog.log;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author wenzhihuai
 * @since 2018/4/29 16:23
 */
public class MessageFormatter implements Formatter {

    @Override
    public String format(ILoggingEvent event) {
        return event.getFormattedMessage();
    }

}