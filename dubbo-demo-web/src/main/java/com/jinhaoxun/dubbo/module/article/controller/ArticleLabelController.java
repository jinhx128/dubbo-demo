package com.jinhaoxun.dubbo.module.article.controller;

import com.jinhaoxun.dubbo.module.article.business.ArticleLabelBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章标签前端控制器
 */
@RestController
@RequestMapping("/articlelabel")
@Api("文章标签接口")
public class ArticleLabelController {

    @Resource
    private ArticleLabelBusiness articleLabelBusiness;

}

