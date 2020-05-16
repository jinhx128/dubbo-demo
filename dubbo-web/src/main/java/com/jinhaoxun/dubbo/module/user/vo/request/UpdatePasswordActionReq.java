package com.jinhaoxun.dubbo.module.user.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(required = true, value = "用户ID", example = "1233333")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码", example = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

}
