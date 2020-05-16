package com.jinhaoxun.dubbo.module.article.controller;

import com.jinhaoxun.dubbo.common.aop.HttpCheck;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import com.jinhaoxun.dubbo.vo.http.HttpRequest;
import com.jinhaoxun.dubbo.vo.http.HttpResponse;
import com.jinhaoxun.dubbo.module.article.vo.request.*;
import com.jinhaoxun.dubbo.module.article.vo.response.GetArticleActionRes;
import com.jinhaoxun.dubbo.module.article.vo.response.GetArticleListActionRes;
import com.jinhaoxun.dubbo.module.article.business.ArticleBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章端前控制器
 */
@RestController
@RequestMapping("/article")
@Api(value = "/article", tags = "文章接口")
public class ArticleController {

    @Resource
    private ArticleBusiness articleBusiness;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListActionReqHttpRequest 筛选条件参数
     * @return GetArticleListActionRes 获取到的文章列表
     */
    @PostMapping(value = "/getarticlelist", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取文章列表", notes = "获取文章列表接口描述")
    @HttpCheck(login = false, dataType = GetArticleListActionReq.class)
    public HttpResponse<GetArticleListActionRes> getArticleList(@Validated @RequestBody HttpRequest<GetArticleListActionReq> getArticleListActionReqHttpRequest) throws Exception {
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
    @PostMapping(value = "/addarticle", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "新增文章", notes = "新增文章接口描述")
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
    @PostMapping(value = "/deletearticle", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "删除文章", notes = "删除文章接口描述")
//    @CacheEvict(value = AbstractConstant.ARTICLE_INFO_CACHE_KEY, key = "#deleteArticleActionReqHttpRequest.getData().getArticleId()")
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
    @PostMapping(value = "/updatearticle", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新文章", notes = "更新文章接口描述")
//    @CacheEvict(value = AbstractConstant.ARTICLE_INFO_CACHE_KEY, key = "#updateArticleActionReqHttpRequest.getData().getArticleId()")
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
    @PostMapping(value = "/getarticle", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取文章", notes = "获取文章接口描述")
//    @Cacheable(value = AbstractConstant.ARTICLE_INFO_CACHE_KEY, key = "#getArticleActionReqHttpRequest.getData().getArticleId()")
    public HttpResponse<GetArticleActionRes> getArticle(@Validated @RequestBody HttpRequest<GetArticleActionReq> getArticleActionReqHttpRequest) throws Exception {
        GetArticleActionRes getArticleActionRes = articleBusiness.getArticle(getArticleActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess(getArticleActionRes);
    }
}

