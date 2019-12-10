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
     * @description 开启消费者
     * @param addConsumerReq 消费者信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addConsumerFallBack")
    public ResponseResult addConsumer(AddConsumerReq addConsumerReq) throws Exception {
        return rocketmqService.addConsumer(addConsumerReq);
    }

    /**
     * @author jinhaoxun
     * @description 开启消费者
     * @param addConsumerReq 消费者信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addConsumerFallBack(AddConsumerReq addConsumerReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 关闭消费者
     * @return ResponseResult 成功提示信息
     */
    @HystrixCommand(fallbackMethod = "deleteConsumerFallBack")
    public ResponseResult deleteConsumer(){
        return rocketmqService.deleteConsumer();
    }

    /**
     * @author jinhaoxun
     * @description 关闭消费者
     * @return ResponseResult 成功提示信息
     */
    public ResponseResult deleteConsumerFallBack(){
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 开启生产者
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addProducerFallBack")
    public ResponseResult addProducer() throws Exception {
        return rocketmqService.addProducer();
    }

    /**
     * @author jinhaoxun
     * @description 开启生产者
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addProducerFallBack() throws Exception {
        return null;
    }

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

    /**
     * @author jinhaoxun
     * @description 关闭生产者
     * @return ResponseResult 成功提示信息
     */
    @HystrixCommand(fallbackMethod = "deleteProducerFallBack")
    public ResponseResult deleteProducer(){
        return rocketmqService.deleteProducer();
    }

    /**
     * @author jinhaoxun
     * @description 关闭生产者
     * @return ResponseResult 成功提示信息
     */
    public ResponseResult deleteProducerFallBack(){
        return null;
    }
}
