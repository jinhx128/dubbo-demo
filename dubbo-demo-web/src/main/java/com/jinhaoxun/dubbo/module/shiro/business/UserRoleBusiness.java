package com.jinhaoxun.dubbo.module.shiro.business;

import com.jinhaoxun.dubbo.module.shiro.service.UserRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class UserRoleBusiness {

    @Reference
    private UserRoleService userRoleService;

}
