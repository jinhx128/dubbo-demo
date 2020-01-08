package com.jinhaoxun.dubbo.module.rocketmq;

import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddMessageReq;
import com.jinhaoxun.dubbo.module.rocketmq.service.RocketmqService;
import com.jinhaoxun.dubbo.response.ResponseFactory;
import com.jinhaoxun.dubbo.response.ResponseResult;
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
     * @param addMessageReq 发送的消息
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult addMessage(AddMessageReq addMessageReq) throws Exception {
        rocketMQTemplate.convertAndSend(addMessageReq.getTopic(), addMessageReq.getMessage());
        return ResponseFactory.buildSuccessResponse("发送消息成功！");
    }
}
