package com.jinhaoxun.dubbo.module.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.user.Permission;
import com.jinhaoxun.dubbo.user.service.PermissionService;
import com.jinhaoxun.dubbo.mapper.user.PermissionMapper;
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
