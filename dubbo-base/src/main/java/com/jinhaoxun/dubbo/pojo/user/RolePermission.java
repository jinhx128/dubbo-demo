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
 * @description 用户权限表实体类
 */
@Setter
@Getter
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("USER_PERMISSION_ID")
    private Long userPermissionId;

    /**
     * 关联权限类型(管理员操作：A 普通用户操作：B VIP用户操作：C )
     */
    @TableField("PERMISSION_CODE")
    private String permissionCode;

    /**
     * 关联权限编码(管理员：1 普通用户：2 VIP用户：3 )
     */
    @TableField("ROLE_CODE")
    private Integer roleCode;

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
