package com.jinhaoxun.dubbo.module.rabbitmq;

import com.jinhaoxun.dubbo.module.rabbitmq.service.RabbitmqService;
import com.jinhaoxun.dubbo.org.rabbitmq.RabbitmqProducerFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description RabbitMQ服务类
 */
@Slf4j
@Service
@Component
public class RabbitmqServiceImpl implements RabbitmqService {

    @Resource
    private RabbitmqProducerFactory rabbitmqProducerFactory;

    /**
     * @author jinhaoxun
     * @description 发送消息方法
     * @param content 发送的消息
     */
    @HystrixCommand
    @Override
    public void sendMsg(String content){
        rabbitmqProducerFactory.sendMsg(content);
    }
}
