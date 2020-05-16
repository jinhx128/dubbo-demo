package com.jinhaoxun.dubbo.module.rabbitmq.controller;

import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import com.jinhaoxun.dubbo.vo.http.HttpRequest;
import com.jinhaoxun.dubbo.vo.http.HttpResponse;
import com.jinhaoxun.dubbo.module.rabbitmq.vo.request.AddMessageActionReq;
import com.jinhaoxun.dubbo.module.rabbitmq.business.RabbitmqBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description RabbitMQ前端控制器
 */
@RequestMapping("/rabbitmq")
@RestController
@Api(value = "/rabbitmq", tags = "RabbitMQ接口")
public class RabbitmqController {

    @Resource
    private RabbitmqBusiness rabbitmqBusiness;

    /**
     * @author jinhaoxun
     * @description 发送消息方法
     * @param addMessageActionReqHttpRequest 发送的消息
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
    @PostMapping(value="/addmessage", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "发送消息", notes = "发送消息接口描述")
    public HttpResponse<ActionResponse> addMessage(@Validated @RequestBody HttpRequest<AddMessageActionReq> addMessageActionReqHttpRequest) throws Exception {
        rabbitmqBusiness.addMessage(addMessageActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }
}

