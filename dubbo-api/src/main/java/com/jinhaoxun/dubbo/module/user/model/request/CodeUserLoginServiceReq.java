package com.jinhaoxun.dubbo.module.user.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取验证码请求实体类
 */
@Setter
@Getter
public class CodeUserLoginServiceReq extends ServiceRequest {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 类型
     */
    private String type;

    /**
     * 验证码
     */
    private String code;
}
