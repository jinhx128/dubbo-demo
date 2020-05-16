package com.jinhaoxun.dubbo.notify.dto.response;

import com.jinhaoxun.dubbo.vo.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取邮箱验证码响应实体类
 */
@Setter
@Getter
public class GetEmailCodeServiceRes extends ServiceResponse {

    /**
     * 验证码
     */
    private String code;

}
