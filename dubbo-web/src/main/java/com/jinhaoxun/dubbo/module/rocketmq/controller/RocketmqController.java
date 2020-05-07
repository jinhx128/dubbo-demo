package com.jinhaoxun.dubbo.module.rocketmq.controller;

import com.jinhaoxun.dubbo.model.action.ActionResponse;
import com.jinhaoxun.dubbo.model.http.HttpRequest;
import com.jinhaoxun.dubbo.model.http.HttpResponse;
import com.jinhaoxun.dubbo.module.rocketmq.action.request.AddMessageActionReq;
import com.jinhaoxun.dubbo.module.rocketmq.business.RocketmqBusiness;
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
     * @param addMessageActionReqHttpRequest 发送的消息
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
    @PostMapping(value="/message", produces = "application/json; charset=UTF-8")
    @ApiOperation("发送消息")
    public HttpResponse<ActionResponse> addMessage(@Validated @RequestBody HttpRequest<AddMessageActionReq> addMessageActionReqHttpRequest) throws Exception {
        rocketmqBusiness.addMessage(addMessageActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

}

