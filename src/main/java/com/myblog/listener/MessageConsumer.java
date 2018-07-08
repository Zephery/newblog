package com.myblog.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/3 18:30
 * Description:RabbitMQ侦听
 */
@Service
public class MessageConsumer implements MessageListener {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
//    @Resource
//    private AmqpTemplate amqpTemplate;


    public void onMessage(Message message) {
        logger.info(message.toString());
    }
}