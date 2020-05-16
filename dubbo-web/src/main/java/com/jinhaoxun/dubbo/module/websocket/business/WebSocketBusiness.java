package com.jinhaoxun.dubbo.module.websocket.business;

import com.jinhaoxun.dubbo.websocket.dto.request.AllRequestMessage;
import com.jinhaoxun.dubbo.websocket.dto.request.ChatRequestMessage;
import com.jinhaoxun.dubbo.websocket.service.WebSocketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class WebSocketBusiness {

    @Reference
    private WebSocketService webSocketService;

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
    public void messageHandling(ChatRequestMessage chatRequestMessage) throws Exception {
        webSocketService.messageHandling(chatRequestMessage);
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
    public void messageHandlingAll(AllRequestMessage allRequestMessage) throws Exception {
        webSocketService.messageHandlingAll(allRequestMessage);
    }
}
