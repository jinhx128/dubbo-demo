package com.jinhaoxun.dubbo.user.dto.response;

import com.jinhaoxun.dubbo.vo.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户登录返回实体
 */
@Setter
@Getter
public class AddSessionServiceRes extends ServiceResponse {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 加密密码
     */
    private String realPassword;
}
