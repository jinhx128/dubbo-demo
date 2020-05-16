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
 * @description 权限表实体类
 */
@Setter
@Getter
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("PERMISSION_ID")
    private Long permissionId;

    /**
     * 权限名称(查看文章详情、查看他人主页等)
     */
    @TableField("NAME")
    private String name;

    /**
     * 访问路径
     */
    @TableField("URL")
    private String url;

    /**
     * 权限编码(1、2、3)
     */
    @TableField("CODE")
    private Integer code;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @TableField("CREATER_ID")
    private Long createrId;

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
