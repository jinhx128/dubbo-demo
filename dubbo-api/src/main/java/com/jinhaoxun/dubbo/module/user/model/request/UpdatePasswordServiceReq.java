package com.jinhaoxun.dubbo.module.user.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 更新密码请求实体类
 */
@Setter
@Getter
public class UpdatePasswordServiceReq extends ServiceRequest {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 密码
     */
    private String password;

}
