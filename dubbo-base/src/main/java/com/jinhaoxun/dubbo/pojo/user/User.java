package com.jinhaoxun.dubbo.pojo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户表实体类
 */
@Setter
@Getter
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("USER_ID")
    private Long userId;

    /**
     * 邮箱(设置唯一值,一个邮箱绑定一个账号)
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 手机号(设置唯一值,一个手机号绑定一个账号)
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 封号状态(正常使用：false 被封禁：true)
     */
    @TableField("BAN")
    private Boolean ban;

    /**
     * 账号状态(正常使用：true 注销：false)
     */
    @TableField("STATUS")
    private Boolean status;

    /**
     * 用户名(设置唯一值)
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 用户昵称
     */
    @TableField("NAME")
    private String name;

    /**
     * 年龄
     */
    @TableField("AGE")
    private Integer age;

    /**
     * 性别(保密：1 男：2 女：3 其他：4)
     */
    @TableField("SEX")
    private Integer sex;

    /**
     * 文章量
     */
    @TableField("ARTICLE")
    private Integer article;

    /**
     * 地址
     */
    @TableField("REGION")
    private String region;

    /**
     * 职业
     */
    @TableField("OCCUPATION")
    private String occupation;

    /**
     * 简介
     */
    @TableField("DYNAMIC")
    private String dynamic;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @TableField("UPDATER_ID")
    private Long updaterId;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
