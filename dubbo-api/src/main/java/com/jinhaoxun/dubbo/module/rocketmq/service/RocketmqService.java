package com.jinhaoxun.dubbo.module.rocketmq.service;

import com.jinhaoxun.dubbo.module.rocketmq.model.request.*;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description RocketMQ服务接口
 */
public interface RocketmqService {

    /**
     * @author jinhaoxun
     * @description 发送消息
     * @param addMessageServiceReq 发送的消息
     * @return
     * @throws Exception
     */
    void addMessage(AddMessageServiceReq addMessageServiceReq) throws Exception;

}
