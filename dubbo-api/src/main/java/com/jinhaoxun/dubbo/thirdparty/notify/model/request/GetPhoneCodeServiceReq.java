package com.jinhaoxun.dubbo.thirdparty.notify.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取手机验证码请求实体类
 */
@Setter
@Getter
public class GetPhoneCodeServiceReq extends ServiceRequest {

    /**
     * 手机号
     */
    private String phone;

}
