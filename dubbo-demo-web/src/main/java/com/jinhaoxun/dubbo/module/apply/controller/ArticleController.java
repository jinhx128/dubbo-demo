package com.jinhaoxun.dubbo.module.apply.controller;

import com.jinhaoxun.dubbo.module.apply.business.ArticleBusiness;
import com.jinhaoxun.dubbo.module.apply.model.request.*;
import com.jinhaoxun.dubbo.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章端前控制器
 */
@RestController
@RequestMapping("/article")
@Api("文章接口")
@CacheConfig(cacheNames = "hahahaaah")
public class ArticleController {

    @Resource
    private ArticleBusiness articleBusiness;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListReq 筛选条件参数
     * @return ResponseResult 获取到的文章列表
     */
    @GetMapping(value = "/articlelist", produces = "application/json; charset=UTF-8")
    @ApiOperation("获取文章列表")
    public ResponseResult getArticleList(@Validated GetArticleListReq getArticleListReq) throws Exception {
        return articleBusiness.getArticleList(getArticleListReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleReq 文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @PostMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("新增文章")
    public ResponseResult addArticle(@Validated @RequestBody AddArticleReq addArticleReq) throws Exception {
        return articleBusiness.addArticle(addArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleReq 删除文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @DeleteMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("删除文章")
    @CacheEvict(value = "info", key = "#deleteArticleReq.getArticleId()")
    public ResponseResult deleteArticle(@Validated @RequestBody DeleteArticleReq deleteArticleReq) throws Exception {
        return articleBusiness.deleteArticle(deleteArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleReq 更新文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
//    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
//    @RequiresPermissions("A1")
//    @RequiresUser
    @PatchMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("更新文章")
    @CachePut(value = "info", key = "#addArticleReq.getAuthorId()")
    public ResponseResult updateArticle(@Validated @RequestBody UpdateArticleReq updateArticleReq) throws Exception {
        return articleBusiness.updateArticle(updateArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleReq 获取文章请求参数
     * @return ResponseResult 是否获取成功
     * @throws Exception
     */
    @GetMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("获取文章")
    @Cacheable(value = "info", key = "#getArticleReq.getArticleId()")
    public ResponseResult getArticle(@Validated GetArticleReq getArticleReq) throws Exception {
        return articleBusiness.getArticle(getArticleReq);
    }
}

