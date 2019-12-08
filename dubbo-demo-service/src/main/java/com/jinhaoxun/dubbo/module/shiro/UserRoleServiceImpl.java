package com.jinhaoxun.dubbo.module.shiro;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.shiro.UserRole;
import com.jinhaoxun.dubbo.mapper.shiro.UserRoleMapper;
import com.jinhaoxun.dubbo.module.shiro.service.UserRoleService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户角色服务类
 */
@Service
@Component
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
