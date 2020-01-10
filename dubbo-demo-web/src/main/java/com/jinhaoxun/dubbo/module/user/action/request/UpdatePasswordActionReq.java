package com.jinhaoxun.dubbo.module.user.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 更新密码请求实体类
 */
@Setter
@Getter
public class UpdatePasswordActionReq extends ActionRequest {

    /**
     * 用户ID
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

}
