package com.myblog.service.impl;

import com.myblog.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/3 11:56
 * Description:消息队列
 */
@Slf4j
@Service("messageService")
public class MessageServiceImpl implements IMessageService {
//    @Resource
//    private AmqpTemplate amqpTemplate;

    public void pushToMessageQueue(String routingKey, String message) {
//        amqpTemplate.convertAndSend(routingKey, message);
//        logger.info("成功插入消息 " + message);
    }

    public void popMessage(String destinationQueueName) {
//        Message message = amqpTemplate.receive(destinationQueueName);
//        logger.info("成功取出消息 " + new String(message.getBody()));
    }
}