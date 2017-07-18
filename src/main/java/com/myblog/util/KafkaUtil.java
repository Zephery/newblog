package com.myblog.util;

import com.myblog.common.Config;
import com.myblog.kafka.KafkaConsumerHandler;
import com.myblog.kafka.KafkaConsumerHandlerImpl;
import com.myblog.kafka.KafkaConsumerRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Zephery on 2017/2/14.
 */
public class KafkaUtil {
    private final static Logger logger = LoggerFactory.getLogger(KafkaUtil.class);

    private static final String KAFKA_CONFIG = "/kafka_consumer.properties";

    private static KafkaConsumerRunner runner;

    public static void main(String[] args) {
        Properties props = getKafkaProp();
        KafkaConsumerHandler handler = new KafkaConsumerHandlerImpl();
        runner = new KafkaConsumerRunner(props, handler);
        shutdownHook();
        new Thread(runner).start();
    }

    private static Properties getKafkaProp() {
        Properties props = new Properties();;
        final InputStream input = Config.class
                .getResourceAsStream(KAFKA_CONFIG);
        if (input == null) {
            return null;
        }
        try {
            props.load(input);
        } catch (final IOException e) {
            return null;
        } finally {
            try {
                input.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    private static void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if(runner != null) {
                        runner.shutdown();
                        Thread.sleep(10000); // wait for output finish.
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
