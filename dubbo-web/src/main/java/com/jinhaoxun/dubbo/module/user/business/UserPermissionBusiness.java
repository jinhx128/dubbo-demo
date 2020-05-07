package com.jinhaoxun.dubbo.module.user.business;

import com.jinhaoxun.dubbo.module.user.service.UserPermissionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class UserPermissionBusiness {

    @Reference
    private UserPermissionService userPermissionService;

}
