package com.jinhaoxun.dubbo.module.shiro;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.shiro.UserPermission;
import com.jinhaoxun.dubbo.mapper.shiro.UserPermissionMapper;
import com.jinhaoxun.dubbo.module.shiro.service.UserPermissionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 用户权限服务类
 */
@Service
@Component
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {

    @Resource
    private UserPermissionMapper userPermissionMapper;

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    @HystrixCommand
    @Override
    public Set<String> selectPermissionSet(Long userId) {
        return userPermissionMapper.selectPermissionSet(userId);
    }

}
