package com.jinhaoxun.dubbo.module.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.po.shiro.UserPermission;

import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 用户权限服务接口
 */
public interface UserPermissionService extends IService<UserPermission> {

    Set<String> selectPermissionSet(Long userId);

}
