package com.jinhaoxun.dubbo.module.shiro.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户登录返回实体
 */
@Setter
@Getter
@ToString
public class AddSessionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 加密密码
     */
    private String realPassword;
}
