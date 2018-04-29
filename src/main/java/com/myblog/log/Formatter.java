package com.myblog.log;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author wenzhihuai
 * @since 2018/4/29 16:20
 */
public interface Formatter {
    String format(ILoggingEvent event);
}