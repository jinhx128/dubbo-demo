package com.jinhaoxun.dubbo.module.user.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
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
public class GetUserActionReq extends ActionRequest {

    /**
     * 用户ID(主键)
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
