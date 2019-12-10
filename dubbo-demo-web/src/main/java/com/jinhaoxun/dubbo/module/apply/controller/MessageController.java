package com.jinhaoxun.dubbo.module.apply.controller;

import com.jinhaoxun.dubbo.module.apply.business.MessageBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 消息前端控制器
 */
@RestController
@RequestMapping("/message")
@Api("消息接口")
public class MessageController {

    @Resource
    private MessageBusiness messageBusiness;

}

