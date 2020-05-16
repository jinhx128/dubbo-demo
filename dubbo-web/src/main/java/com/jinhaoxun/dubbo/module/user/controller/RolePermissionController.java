package com.jinhaoxun.dubbo.module.user.controller;

import com.jinhaoxun.dubbo.module.user.business.RolePermissionBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户权限前端控制器
 */
@RestController
@RequestMapping("/rolepermission")
@Api(value = "/rolepermission", tags = "用户权限接口")
public class RolePermissionController {

    @Resource
    private RolePermissionBusiness rolePermissionBusiness;

}

