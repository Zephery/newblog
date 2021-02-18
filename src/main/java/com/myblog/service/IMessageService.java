package com.myblog.service;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/3 11:55
 * Description:RabbitMQ消息队列
 */
public interface IMessageService {
    /**
     * 消息队列写入
     *
     * @param routingKey
     * @param message
     */
    void pushToMessageQueue(String routingKey, String message);
}