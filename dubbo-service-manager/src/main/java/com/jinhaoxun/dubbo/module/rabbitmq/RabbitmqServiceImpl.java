package com.jinhaoxun.dubbo.module.rabbitmq;

import com.jinhaoxun.dubbo.framework.rabbitmq.RabbitmqProducerFactory;
import com.jinhaoxun.dubbo.rabbitmq.dto.request.AddMessageServiceReq;
import com.jinhaoxun.dubbo.rabbitmq.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

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
     * @param addMessageServiceReq 发送的消息
     * @return
     * @throws Exception
     */
    @Override
    public void sendMsg(AddMessageServiceReq addMessageServiceReq) throws Exception{
        rabbitmqProducerFactory.sendMsg(addMessageServiceReq.getContent());
    }
}
