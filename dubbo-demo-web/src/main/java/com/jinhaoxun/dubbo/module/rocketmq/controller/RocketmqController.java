package com.jinhaoxun.dubbo.module.rocketmq.controller;

import com.jinhaoxun.dubbo.module.rocketmq.business.RocketmqBusiness;
import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddConsumerReq;
import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddMessageReq;
import com.jinhaoxun.dubbo.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description RocketMQ前端控制器
 */
@RequestMapping("/rocketmq")
@RestController
@Api("RocketMQ接口")
public class RocketmqController {

    @Resource
    private RocketmqBusiness rocketmqBusiness;

    /**
     * @author jinhaoxun
     * @description 发送消息
     * @param addMessageReq 发送的消息
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @PostMapping(value="/message", produces = "application/json; charset=UTF-8")
    @ApiOperation("发送消息")
    public ResponseResult addMessage(@Validated @RequestBody AddMessageReq addMessageReq) throws Exception {
        return rocketmqBusiness.addMessage(addMessageReq);
    }

}

