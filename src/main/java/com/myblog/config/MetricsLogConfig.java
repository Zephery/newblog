package com.myblog.config;

import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zepherywen
 * @since 2022/4/30 23:26
 */
@Slf4j
@Component
public class MetricsLogConfig {
    @Bean
    public LoggingMeterRegistry loggingMeterRegistry() {
        return new LoggingMeterRegistry();
    }
}
