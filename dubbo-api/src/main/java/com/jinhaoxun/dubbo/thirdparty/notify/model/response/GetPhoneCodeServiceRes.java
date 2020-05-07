package com.jinhaoxun.dubbo.thirdparty.notify.model.response;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import com.jinhaoxun.dubbo.model.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取手机验证码响应实体类
 */
@Setter
@Getter
public class GetPhoneCodeServiceRes extends ServiceResponse {

    /**
     * 验证码
     */
    private String code;

}
