package com.jinhaoxun.dubbo.user.dto.response;

import com.jinhaoxun.dubbo.vo.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取验证码请求实体类
 */
@Setter
@Getter
public class GetUserServiceRes extends ServiceResponse {

    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 邮箱(设置唯一值,一个邮箱绑定一个账号)
     */
    private String email;

    /**
     * 手机号(设置唯一值,一个手机号绑定一个账号)
     */
    private String phone;

    /**
     * 封号状态(正常使用：false 被封禁：true)
     */
    private Boolean ban;

    /**
     * 账号状态(正常使用：true 注销：false)
     */
    private Boolean status;

    /**
     * 用户名(设置唯一值)
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(保密：1 男：2 女：3 其他：4)
     */
    private Integer sex;

    /**
     * 文章量
     */
    private Integer article;

    /**
     * 地址
     */
    private String region;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 简介
     */
    private String dynamic;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updaterId;
}
