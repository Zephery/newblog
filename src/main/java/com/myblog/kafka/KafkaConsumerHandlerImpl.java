package com.myblog.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumerHandlerImpl implements KafkaConsumerHandler {
    private final static Logger logger= LoggerFactory.getLogger(KafkaConsumerHandlerImpl.class);

    @Override
    public void consume(ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String, String> record : records) {
            String msg = record.value();
            logger.info(msg);
        }
    }
}
