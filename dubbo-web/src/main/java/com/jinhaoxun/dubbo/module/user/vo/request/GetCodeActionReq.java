package com.jinhaoxun.dubbo.module.user.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取验证码请求实体类
 */
@Setter
@Getter
public class GetCodeActionReq extends ActionRequest {

    /**
     * 手机号
     */
    @ApiModelProperty(required = true, value = "手机号", example = "手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(required = true, value = "邮箱", example = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 类型
     */
    @ApiModelProperty(required = true, value = "类型", example = "类型")
    @NotNull(message = "类型不能为空")
    private String type;

}
