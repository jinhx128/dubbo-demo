package com.jinhaoxun.dubbo.module.rocketmq.service;

import com.jinhaoxun.dubbo.module.rocketmq.model.request.*;
import com.jinhaoxun.dubbo.response.ResponseResult;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description RocketMQ服务接口
 */
public interface RocketmqService {

    /**
     * @author jinhaoxun
     * @description 开启消费者
     * @param addConsumerReq 消费者信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult addConsumer(AddConsumerReq addConsumerReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 关闭消费者
     * @return ResponseResult 成功提示信息
     */
    ResponseResult deleteConsumer();

    /**
     * @author jinhaoxun
     * @description 开启生产者
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult addProducer() throws Exception;

    /**
     * @author jinhaoxun
     * @description 发送消息
     * @param addMessageReq 发送的消息
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult addMessage(AddMessageReq addMessageReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 关闭生产者
     * @return ResponseResult 成功提示信息
     */
    ResponseResult deleteProducer();
}