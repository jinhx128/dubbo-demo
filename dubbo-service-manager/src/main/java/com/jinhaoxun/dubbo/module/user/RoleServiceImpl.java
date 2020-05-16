package com.jinhaoxun.dubbo.module.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.user.Role;
import com.jinhaoxun.dubbo.mapper.user.RoleMapper;
import com.jinhaoxun.dubbo.user.service.RoleService;
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
