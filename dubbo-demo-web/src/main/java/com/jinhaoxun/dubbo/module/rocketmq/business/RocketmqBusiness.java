package com.jinhaoxun.dubbo.module.rocketmq.business;

import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddConsumerReq;
import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddMessageReq;
import com.jinhaoxun.dubbo.module.rocketmq.service.RocketmqService;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class RocketmqBusiness {

    @Reference
    private RocketmqService rocketmqService;

    /**
     * @author jinhaoxun
     * @description 发送消息
     * @param addMessageReq 发送的消息
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addMessageFallBack")
    public ResponseResult addMessage(AddMessageReq addMessageReq) throws Exception {
        return rocketmqService.addMessage(addMessageReq);
    }

    /**
     * @author jinhaoxun
     * @description 发送消息
     * @param addMessageReq 发送的消息
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addMessageFallBack(AddMessageReq addMessageReq) throws Exception {
        return null;
    }

}
