package com.jinhaoxun.dubbo.rabbitmq.service;

import com.jinhaoxun.dubbo.rabbitmq.dto.request.AddMessageServiceReq;

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
     * @param addMessageServiceReq 发送的消息
     * @return
     * @throws Exception
     */
    void sendMsg(AddMessageServiceReq addMessageServiceReq) throws Exception;
}
