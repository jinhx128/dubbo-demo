package com.jinhaoxun.dubbo.module.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.module.article.model.response.GetArticleListServiceRes;
import com.jinhaoxun.dubbo.module.article.model.response.GetArticleServiceRes;
import com.jinhaoxun.dubbo.pojo.article.Article;
import com.jinhaoxun.dubbo.module.article.model.request.*;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章服务接口
 */
public interface ArticleService extends IService<Article> {

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListServiceReq 筛选条件参数
     * @return GetArticleListServiceRes 获取到的文章列表
     */
    GetArticleListServiceRes getArticleList(GetArticleListServiceReq getArticleListServiceReq);

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleServiceReq 文章信息参数
     * @return
     * @throws Exception
     */
    void addArticle(AddArticleServiceReq addArticleServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleServiceReq 删除文章请求参数
     * @return
     * @throws Exception
     */
    void deleteArticle(DeleteArticleServiceReq deleteArticleServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleServiceReq 更新文章请求参数
     * @return
     * @throws Exception
     */
    void updateArticle(UpdateArticleServiceReq updateArticleServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleServiceReq 获取文章请求参数
     * @return GetArticleServiceRes
     * @throws Exception
     */
    GetArticleServiceRes getArticle(GetArticleServiceReq getArticleServiceReq) throws Exception;

}
