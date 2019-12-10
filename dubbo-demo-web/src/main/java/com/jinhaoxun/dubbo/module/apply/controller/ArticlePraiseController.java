package com.jinhaoxun.dubbo.module.apply.controller;

import com.jinhaoxun.dubbo.module.apply.business.ArticlePraiseBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章点赞前端控制器
 */
@RestController
@RequestMapping("/articlepraise")
@Api("文章点赞接口")
public class ArticlePraiseController {

    @Resource
    private ArticlePraiseBusiness articlePraiseBusiness;

}

