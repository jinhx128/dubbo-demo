package com.jinhaoxun.dubbo.module.rabbitmq.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 发送RabbitMQ消息请求实体类
 */
@Setter
@Getter
public class AddMessageActionReq extends ActionRequest {

    @ApiModelProperty(required = true, value = "发送消息", example = "发送消息")
    @NotNull(message = "发送消息不能为空")
    private String content;

}
