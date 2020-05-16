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
 * @description 角色表实体类
 */
@Setter
@Getter
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("ROLE_ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 角色编码(管理员：1 普通用户：2 VIP用户：3 )
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
