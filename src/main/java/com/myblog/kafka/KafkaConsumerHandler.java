package com.myblog.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface KafkaConsumerHandler {
	
	public void consume(ConsumerRecords<String, String> records);
}
