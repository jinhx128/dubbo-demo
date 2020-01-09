package com.jinhaoxun.dubbo.module.article.controller;

import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.model.action.ActionResponse;
import com.jinhaoxun.dubbo.model.http.HttpRequest;
import com.jinhaoxun.dubbo.model.http.HttpResponse;
import com.jinhaoxun.dubbo.module.article.action.request.*;
import com.jinhaoxun.dubbo.module.article.action.response.GetArticleActionRes;
import com.jinhaoxun.dubbo.module.article.action.response.GetArticleListActionRes;
import com.jinhaoxun.dubbo.module.article.business.ArticleBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
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
public class ArticleController {

    @Resource
    private ArticleBusiness articleBusiness;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListActionReqHttpRequest 筛选条件参数
     * @return GetArticleListActionRes 获取到的文章列表
     */
    @GetMapping(value = "/articlelist", produces = "application/json; charset=UTF-8")
    @ApiOperation("获取文章列表")
    public HttpResponse<GetArticleListActionRes> getArticleList(@Validated HttpRequest<GetArticleListActionReq> getArticleListActionReqHttpRequest) throws Exception {
        GetArticleListActionRes getArticleListActionRes = articleBusiness.getArticleList(getArticleListActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess(getArticleListActionRes);
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleActionReqHttpRequest 文章信息参数
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
    @PostMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("新增文章")
    public HttpResponse<ActionResponse> addArticle(@Validated @RequestBody HttpRequest<AddArticleActionReq> addArticleActionReqHttpRequest) throws Exception {
        articleBusiness.addArticle(addArticleActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleActionReqHttpRequest 删除文章请求参数
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
    @DeleteMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("删除文章")
    @CacheEvict(value = AbstractConstant.ARTICLE_INFO_CACHE_KEY, key = "#deleteArticleReq.getArticleId()")
    public HttpResponse<ActionResponse> deleteArticle(@Validated @RequestBody HttpRequest<DeleteArticleActionReq> deleteArticleActionReqHttpRequest) throws Exception {
        articleBusiness.deleteArticle(deleteArticleActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleActionReqHttpRequest 更新文章请求参数
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
//    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
//    @RequiresPermissions("A1")
//    @RequiresUser
    @PatchMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("更新文章")
    @CacheEvict(value = AbstractConstant.ARTICLE_INFO_CACHE_KEY, key = "#updateArticleReq.getArticleId()")
    public HttpResponse<ActionResponse> updateArticle(@Validated @RequestBody HttpRequest<UpdateArticleActionReq> updateArticleActionReqHttpRequest) throws Exception {
        articleBusiness.updateArticle(updateArticleActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleActionReqHttpRequest 获取文章请求参数
     * @return GetArticleActionRes 是否获取成功
     * @throws Exception
     */
    @GetMapping(value = "/article", produces = "application/json; charset=UTF-8")
    @ApiOperation("获取文章")
    @Cacheable(value = AbstractConstant.ARTICLE_INFO_CACHE_KEY, key = "#getArticleReq.getArticleId()")
    public HttpResponse<GetArticleActionRes> getArticle(@Validated HttpRequest<GetArticleActionReq> getArticleActionReqHttpRequest) throws Exception {
        GetArticleActionRes getArticleActionRes = articleBusiness.getArticle(getArticleActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess(getArticleActionRes);
    }
}

