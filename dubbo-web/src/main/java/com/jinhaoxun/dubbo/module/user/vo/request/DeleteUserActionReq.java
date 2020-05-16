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
 * @description 用户id请求实体类
 */
@Setter
@Getter
public class DeleteUserActionReq extends ActionRequest {

    /**
     * 用户ID(主键)
     */
    @ApiModelProperty(required = true, value = "用户ID", example = "1233333")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
