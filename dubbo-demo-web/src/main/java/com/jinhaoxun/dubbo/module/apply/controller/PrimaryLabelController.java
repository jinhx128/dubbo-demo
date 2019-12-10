package com.jinhaoxun.dubbo.module.apply.controller;

import com.jinhaoxun.dubbo.module.apply.business.PrimaryLabelBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 一级标签前端控制器
 */
@RestController
@RequestMapping("/primarylabel")
@Api("一级标签接口")
public class PrimaryLabelController {

    @Resource
    private PrimaryLabelBusiness primaryLabelBusiness;

}

