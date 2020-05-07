package com.jinhaoxun.dubbo.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.pojo.user.UserRole;

import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户角色服务接口
 */
public interface UserRoleService extends IService<UserRole> {

    Set<String> selectRoleSet(Long userId);

}
