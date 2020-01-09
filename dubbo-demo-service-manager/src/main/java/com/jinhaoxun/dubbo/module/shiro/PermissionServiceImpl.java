package com.jinhaoxun.dubbo.module.shiro;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.shiro.Permission;
import com.jinhaoxun.dubbo.mapper.shiro.PermissionMapper;
import com.jinhaoxun.dubbo.module.shiro.service.PermissionService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 权限服务类
 */
@Service
@Component
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
