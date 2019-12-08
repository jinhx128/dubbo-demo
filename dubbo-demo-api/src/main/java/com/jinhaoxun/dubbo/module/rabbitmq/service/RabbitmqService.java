package com.jinhaoxun.dubbo.module.rabbitmq.service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description RabbitMQ服务接口
 */
public interface RabbitmqService {

    /**
     * @author jinhaoxun
     * @description 发送消息方法
     * @param content 发送的消息
     */
    void sendMsg(String content);
}