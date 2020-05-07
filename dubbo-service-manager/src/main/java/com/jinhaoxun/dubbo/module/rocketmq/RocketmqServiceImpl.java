package com.jinhaoxun.dubbo.module.rocketmq;

import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddMessageServiceReq;
import com.jinhaoxun.dubbo.module.rocketmq.service.RocketmqService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description RocketMQ服务类
 */
@Slf4j
@Service
@Component
public class RocketmqServiceImpl implements RocketmqService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * @author jinhaoxun
     * @description 发送消息
     * @param addMessageServiceReq 发送的消息
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void addMessage(AddMessageServiceReq addMessageServiceReq) throws Exception {
        rocketMQTemplate.convertAndSend(addMessageServiceReq.getTopic(), addMessageServiceReq.getMessage());
    }
}
