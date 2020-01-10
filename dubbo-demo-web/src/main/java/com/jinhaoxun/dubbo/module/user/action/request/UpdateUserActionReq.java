package com.jinhaoxun.dubbo.module.user.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取验证码请求实体类
 */
@Setter
@Getter
public class UpdateUserActionReq extends ActionRequest {

    /**
     * 主键ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 邮箱(设置唯一值,一个邮箱绑定一个账号)
     */
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     * 手机号(设置唯一值,一个手机号绑定一个账号)
     */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 封号状态(正常使用：false 被封禁：true)
     */
    @NotNull(message = "封号状态不能为空")
    private Boolean ban;

    /**
     * 账号状态(正常使用：true 注销：false)
     */
    @NotNull(message = "账号状态不能为空")
    private Boolean status;

    /**
     * 用户名(设置唯一值)
     */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 用户昵称
     */
    @NotNull(message = "用户昵称不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 性别(保密：1 男：2 女：3 其他：4)
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 文章量
     */
    @NotNull(message = "文章量不能为空")
    private Integer article;

    /**
     * 文章评论量
     */
    @NotNull(message = "文章评论量不能为空")
    private Integer articleComment;

    /**
     * 文章点赞量
     */
    @NotNull(message = "文章点赞量不能为空")
    private Integer articlePraise;

    /**
     * 地址
     */
    @NotNull(message = "地址不能为空")
    private String region;

    /**
     * 职业
     */
    @NotNull(message = "职业不能为空")
    private String occupation;

    /**
     * 简介
     */
    @NotNull(message = "简介不能为空")
    private String dynamic;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date createTime;

    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空")
    private Date updateTime;

    /**
     * 更新人ID
     */
    @NotNull(message = "更新人ID不能为空")
    private Long updaterId;
}
