package com.jinhaoxun.dubbo.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhaoxun.dubbo.pojo.user.RolePermission;

import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户权限Mapper接口
 */
public interface UserPermissionMapper extends BaseMapper<RolePermission> {

    /**
     * @author jinhaoxun
     * @description 获得用户权限列表
     * @param userId 用户id
     * @return ResponseResult 用户权限集合
     */
    Set<String> selectPermissionSet(Long userId);

}
