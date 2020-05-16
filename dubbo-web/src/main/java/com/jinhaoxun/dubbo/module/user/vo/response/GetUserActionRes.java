package com.jinhaoxun.dubbo.module.user.vo.response;

import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取验证码请求实体类
 */
@Setter
@Getter
@ApiModel("获取验证码请求实体类")
public class GetUserActionRes extends ActionResponse {

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "用户id", example = "123333333")
    private Long userId;

    /**
     * 邮箱(设置唯一值,一个邮箱绑定一个账号)
     */
    @ApiModelProperty(required = true, value = "邮箱", example = "邮箱")
    private String email;

    /**
     * 手机号(设置唯一值,一个手机号绑定一个账号)
     */
    @ApiModelProperty(required = true, value = "手机号", example = "手机号")
    private String phone;

    /**
     * 封号状态(正常使用：false 被封禁：true)
     */
    @ApiModelProperty(required = true, value = "封号状态", example = "true")
    private Boolean ban;

    /**
     * 账号状态(正常使用：true 注销：false)
     */
    @ApiModelProperty(required = true, value = "账号状态(正常使用：true 注销：false)", example = "true")
    private Boolean status;

    /**
     * 用户名(设置唯一值)
     */
    @ApiModelProperty(required = true, value = "用户名", example = "用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码", example = "密码")
    private String password;

    /**
     * 用户昵称
     */
    @ApiModelProperty(required = true, value = "手机号", example = "手机号")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(required = true, value = "年龄", example = "24")
    private Integer age;

    /**
     * 性别(保密：1 男：2 女：3 其他：4)
     */
    @ApiModelProperty(required = true, value = "性别(保密：1 男：2 女：3 其他：4)", example = "1")
    private Integer sex;

    /**
     * 文章量
     */
    @ApiModelProperty(required = true, value = "文章量", example = "124")
    private Integer article;

    /**
     * 地址
     */
    @ApiModelProperty(required = true, value = "地址", example = "地址")
    private String region;

    /**
     * 职业
     */
    @ApiModelProperty(required = true, value = "职业", example = "职业")
    private String occupation;

    /**
     * 简介
     */
    @ApiModelProperty(required = true, value = "简介", example = "简介")
    private String dynamic;

    /**
     * 创建时间
     */
    @ApiModelProperty(required = true, value = "创建时间", example = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(required = true, value = "更新时间", example = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @ApiModelProperty(required = true, value = "更新人ID", example = "123333333")
    private Long updaterId;
}
