package com.jinhaoxun.dubbo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.pojo.user.RolePermission;

import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 用户权限服务接口
 */
public interface RolePermissionService extends IService<RolePermission> {

    Set<String> selectPermissionSet(Long userId);

}
