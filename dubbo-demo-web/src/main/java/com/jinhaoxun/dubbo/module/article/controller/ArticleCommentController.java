package com.jinhaoxun.dubbo.module.article.controller;

import com.jinhaoxun.dubbo.module.article.business.ArticleCommentBusiness;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章评论前端控制器
 */
@RestController
@RequestMapping("/articlecomment")
@Api("文章评论接口")
public class ArticleCommentController {

    @Resource
    private ArticleCommentBusiness articleCommentBusiness;

}

