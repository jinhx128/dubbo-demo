package com.jinhaoxun.dubbo.module.websocket.controller;

import com.jinhaoxun.dubbo.module.websocket.model.request.*;
import com.jinhaoxun.dubbo.module.websocket.service.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description WebSocket前端控制器
 */
@RestController
@RequestMapping("/websocket")
@Api("WebSocket接口")
public class WebSocketController {

    @Reference
    private WebSocketService iWebSocketService;

    /**
     * @author jinhaoxun
     * @description 单发消息
     * 注解的方法可以使用下列参数:
     * 使用@Payload方法参数用于获取消息中的payload（即消息的内容）
     * 使用@Header 方法参数用于获取特定的头部
     * 使用@Headers方法参数用于获取所有的头部存放到一个map中
     * java.security.Principal 方法参数用于获取在websocket握手阶段使用的用户信息
     * @param chatRequestMessage 发送消息参数
     * @throws Exception
     */
    @MessageMapping("/chat")
    @ApiOperation("单发消息")
    public void messageHandling(@Validated @RequestBody ChatRequestMessage chatRequestMessage) throws Exception {
        iWebSocketService.messageHandling(chatRequestMessage);
    }

    /**
     * @author jinhaoxun
     * @description 群发消息
     * 注解的方法可以使用下列参数:
     * 使用@Payload方法参数用于获取消息中的payload（即消息的内容）
     * 使用@Header 方法参数用于获取特定的头部
     * 使用@Headers方法参数用于获取所有的头部存放到一个map中
     * java.security.Principal 方法参数用于获取在websocket握手阶段使用的用户信息
     * @param allRequestMessage 发送消息参数
     * @throws Exception
     */
    @MessageMapping("/all")
    @ApiOperation("群发消息")
    public void messageHandlingAll(@Validated @RequestBody AllRequestMessage allRequestMessage) throws Exception {
        iWebSocketService.messageHandlingAll(allRequestMessage);
    }
}
