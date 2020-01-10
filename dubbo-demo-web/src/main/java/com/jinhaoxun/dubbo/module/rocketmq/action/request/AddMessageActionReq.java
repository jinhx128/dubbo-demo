package com.jinhaoxun.dubbo.module.rocketmq.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 发送RocketMQ消息请求实体类
 */
@Setter
@Getter
public class AddMessageActionReq extends ActionRequest {

    @NotNull(message = "Topic不能为空")
    private String topic;

    @NotNull(message = "Tag不能为空")
    private String tag;

    @NotNull(message = "发送消息不能为空")
    private String message;

}
