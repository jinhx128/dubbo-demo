package com.jinhaoxun.dubbo.module.user.controller;

import com.jinhaoxun.dubbo.module.user.business.RoleBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 角色前端控制器
 */
@RestController
@RequestMapping("/role")
@Api(value = "/role", tags = "角色接口")
public class RoleController {

    @Resource
    private RoleBusiness roleBusiness;

}

