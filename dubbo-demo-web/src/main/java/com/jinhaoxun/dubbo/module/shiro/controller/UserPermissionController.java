package com.jinhaoxun.dubbo.module.shiro.controller;

import com.jinhaoxun.dubbo.module.shiro.business.UserPermissionBusiness;
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
@RequestMapping("/userpermission")
@Api("用户权限接口")
public class UserPermissionController {

    @Resource
    private UserPermissionBusiness userPermissionBusiness;

}

