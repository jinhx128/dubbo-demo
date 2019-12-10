package com.jinhaoxun.dubbo.module.shiro.controller;

import com.jinhaoxun.dubbo.module.shiro.business.UserRoleBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户角色前端控制器
 */
@RestController
@RequestMapping("/userrole")
@Api("用户角色接口")
public class UserRoleController {

    @Resource
    private UserRoleBusiness userRoleBusiness;

}

