package com.jinhaoxun.dubbo.module.user.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户登录请求实体类
 */
@Setter
@Getter
public class UserLoginActionReq extends ActionRequest {

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空")
    private String type;

}
