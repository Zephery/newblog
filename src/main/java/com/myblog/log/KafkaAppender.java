//package com.myblog.log;
//
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.AppenderBase;
//import lombok.Data;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Properties;
//
///**
// * @author wenzhihuai
// * @since 2018/4/29 16:20
// */
//@Data
//public class KafkaAppender extends AppenderBase<ILoggingEvent> {
//
//    private String topic;
//    private String brokerList;
//    private Producer<byte[], byte[]> producer;
//    private Formatter formatter;
//
//    @Override
//    public void start() {
//        if (null == this.formatter) {
////            this.formatter = new MessageFormatter();
//            this.formatter = new JsonFormatter();
//        }
//        super.start();
//        Properties properties = new Properties();
//        properties.put("request.required.acks", "1");
//        properties.put("bootstrap.servers", brokerList);
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
//        this.producer = getKafkaProducer(properties);
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//        this.producer.close();
//    }
//
//    @Override
//    protected void append(ILoggingEvent iLoggingEvent) {
//        String payload = this.formatter.format(iLoggingEvent);
//        //TODO 线程太多会导致服务器有影响
//        new Thread(() -> producer.send(
//                new ProducerRecord<>(topic, payload.getBytes(StandardCharsets.UTF_8)))).start();
//    }
//
//    private Producer<byte[], byte[]> getKafkaProducer(Properties props) {
//        return new KafkaProducer<>(props);
//    }
//}