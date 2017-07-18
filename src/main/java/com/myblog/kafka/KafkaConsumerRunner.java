package com.myblog.kafka;

import com.myblog.common.Config;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerRunner implements Runnable {

    private final AtomicBoolean closed = new AtomicBoolean(false);
    private KafkaConsumer<String, String> consumer;
    private KafkaConsumerHandler handler;

    public KafkaConsumerRunner(Properties props, KafkaConsumerHandler handler) {
        consumer = new KafkaConsumer<>(props);
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            consumer.subscribe(Arrays.asList(Config.getProperty("input_topic")));
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(3000);
                try {
                    consumer.commitSync(); // commit
                } catch (Exception e) {
                }
                if (records.count() > 0) {
                    handler.consume(records);
                }
            }
        } catch (WakeupException e) {
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
        }
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.close();
    }
}
