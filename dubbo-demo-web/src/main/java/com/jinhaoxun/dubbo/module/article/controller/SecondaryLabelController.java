package com.jinhaoxun.dubbo.module.article.controller;

import com.jinhaoxun.dubbo.module.article.business.SecondaryLabelBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 二级标签前端控制器
 */
@RestController
@RequestMapping("/secondarylabel")
@Api("二级标签接口")
public class SecondaryLabelController {

    @Resource
    private SecondaryLabelBusiness secondaryLabelBusiness;

}

