package com.jinhaoxun.dubbo.module.user.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
public class UpdateUserActionReq extends ActionRequest {

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "用户ID", example = "1233333")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 邮箱(设置唯一值,一个邮箱绑定一个账号)
     */
    @ApiModelProperty(required = true, value = "邮箱", example = "邮箱")
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     * 手机号(设置唯一值,一个手机号绑定一个账号)
     */
    @ApiModelProperty(required = true, value = "手机号", example = "手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 封号状态(正常使用：false 被封禁：true)
     */
    @ApiModelProperty(required = true, value = "封号状态", example = "true")
    @NotNull(message = "封号状态不能为空")
    private Boolean ban;

    /**
     * 账号状态(正常使用：true 注销：false)
     */
    @ApiModelProperty(required = true, value = "账号状态", example = "true")
    @NotNull(message = "账号状态不能为空")
    private Boolean status;

    /**
     * 用户名(设置唯一值)
     */
    @ApiModelProperty(required = true, value = "用户名", example = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码", example = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 用户昵称
     */
    @ApiModelProperty(required = true, value = "用户昵称", example = "用户昵称")
    @NotNull(message = "用户昵称不能为空")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(required = true, value = "年龄", example = "24")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 性别(保密：1 男：2 女：3 其他：4)
     */
    @ApiModelProperty(required = true, value = "性别", example = "1")
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 文章量
     */
    @ApiModelProperty(required = true, value = "文章量", example = "124")
    @NotNull(message = "文章量不能为空")
    private Integer article;

    /**
     * 地址
     */
    @ApiModelProperty(required = true, value = "地址", example = "地址")
    @NotNull(message = "地址不能为空")
    private String region;

    /**
     * 职业
     */
    @ApiModelProperty(required = true, value = "职业", example = "职业")
    @NotNull(message = "职业不能为空")
    private String occupation;

    /**
     * 简介
     */
    @ApiModelProperty(required = true, value = "简介", example = "简介")
    @NotNull(message = "简介不能为空")
    private String dynamic;

    /**
     * 创建时间
     */
    @ApiModelProperty(required = true, value = "创建时间", example = "创建时间")
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(required = true, value = "更新时间", example = "更新时间")
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @ApiModelProperty(required = true, value = "更新人ID", example = "1233333")
    @NotNull(message = "更新人ID不能为空")
    private Long updaterId;
}
