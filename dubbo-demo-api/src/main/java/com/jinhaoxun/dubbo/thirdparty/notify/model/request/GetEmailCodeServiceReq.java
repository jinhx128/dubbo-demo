package com.jinhaoxun.dubbo.thirdparty.notify.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取邮箱验证码请求实体类
 */
@Setter
@Getter
public class GetEmailCodeServiceReq extends ServiceRequest {

    /**
     * 邮箱
     */
    private String email;

}
