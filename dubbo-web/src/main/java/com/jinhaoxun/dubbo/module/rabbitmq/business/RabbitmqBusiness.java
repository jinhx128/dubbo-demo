package com.jinhaoxun.dubbo.module.rabbitmq.business;

import com.jinhaoxun.dubbo.module.rabbitmq.vo.request.AddMessageActionReq;
import com.jinhaoxun.dubbo.rabbitmq.dto.request.AddMessageServiceReq;
import com.jinhaoxun.dubbo.rabbitmq.service.RabbitmqService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
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
     * @param addMessageActionReq 发送的消息
     * @return
     * @throws Exception
     */
    public void addMessage(AddMessageActionReq addMessageActionReq) throws Exception{
        AddMessageServiceReq addMessageServiceReq = new AddMessageServiceReq();
        BeanUtils.copyProperties(addMessageActionReq, addMessageServiceReq);
        rabbitmqService.sendMsg(addMessageServiceReq);
    }
}
