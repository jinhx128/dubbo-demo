package com.jinhaoxun.dubbo.module.websocket.controller;

import com.jinhaoxun.dubbo.module.websocket.business.WebSocketBusiness;
import com.jinhaoxun.dubbo.websocket.dto.request.AllRequestMessage;
import com.jinhaoxun.dubbo.websocket.dto.request.ChatRequestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/websocket", tags = "WebSocket接口")
public class WebSocketController {

    @Resource
    private WebSocketBusiness webSocketBusiness;

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
    @ApiOperation(value = "单发消息", notes = "单发消息接口描述")
    public void messageHandling(@Validated @RequestBody ChatRequestMessage chatRequestMessage) throws Exception {
        webSocketBusiness.messageHandling(chatRequestMessage);
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
    @ApiOperation(value = "群发消息", notes = "群发消息接口描述")
    public void messageHandlingAll(@Validated @RequestBody AllRequestMessage allRequestMessage) throws Exception {
        webSocketBusiness.messageHandlingAll(allRequestMessage);
    }
}
