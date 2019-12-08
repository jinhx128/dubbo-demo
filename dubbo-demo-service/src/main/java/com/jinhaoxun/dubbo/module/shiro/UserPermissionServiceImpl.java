package com.jinhaoxun.dubbo.module.shiro;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.shiro.UserPermission;
import com.jinhaoxun.dubbo.mapper.shiro.UserPermissionMapper;
import com.jinhaoxun.dubbo.module.shiro.service.UserPermissionService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 用户权限服务类
 */
@Service
@Component
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {

}
