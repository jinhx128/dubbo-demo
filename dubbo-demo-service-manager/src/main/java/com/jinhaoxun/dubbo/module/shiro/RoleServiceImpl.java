package com.jinhaoxun.dubbo.module.shiro;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.shiro.Role;
import com.jinhaoxun.dubbo.mapper.shiro.RoleMapper;
import com.jinhaoxun.dubbo.module.shiro.service.RoleService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 角色服务类
 */
@Service
@Component
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
