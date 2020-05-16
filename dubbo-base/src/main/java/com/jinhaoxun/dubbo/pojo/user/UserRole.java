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
 * @description 用户角色表实体类
 */
@Setter
@Getter
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("USER_ROLE_ID")
    private Long userRoleId;

    /**
     * 关联角色编码(管理员：1 普通用户：2 VIP用户：3 )
     */
    @TableField("CODE")
    private Integer code;

    /**
     * 关联用户ID
     */
    @TableField("USER_ID")
    private Long userId;

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

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
