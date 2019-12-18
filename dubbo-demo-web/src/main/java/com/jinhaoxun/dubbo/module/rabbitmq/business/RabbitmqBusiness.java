package com.jinhaoxun.dubbo.module.rabbitmq.business;

import com.jinhaoxun.dubbo.module.rabbitmq.service.RabbitmqService;
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
public class RabbitmqBusiness {

    @Reference
    private RabbitmqService rabbitmqService;

    /**
     * @author jinhaoxun
     * @description 发送消息方法
     * @param content 发送的消息
     */
    @HystrixCommand(fallbackMethod = "addConsumerFallBack")
    public void addConsumer(String content){
        rabbitmqService.sendMsg(content);
    }

    /**
     * @author jinhaoxun
     * @description 发送消息方法
     * @param content 发送的消息
     */
    public void addConsumerFallBack(String content){
    }
}
