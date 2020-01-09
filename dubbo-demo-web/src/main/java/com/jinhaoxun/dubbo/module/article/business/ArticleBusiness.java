package com.jinhaoxun.dubbo.module.article.business;

import com.jinhaoxun.dubbo.module.article.action.request.*;
import com.jinhaoxun.dubbo.module.article.action.response.GetArticleActionRes;
import com.jinhaoxun.dubbo.module.article.action.response.GetArticleListActionRes;
import com.jinhaoxun.dubbo.module.article.model.request.*;
import com.jinhaoxun.dubbo.module.article.model.response.GetArticleListServiceRes;
import com.jinhaoxun.dubbo.module.article.model.response.GetArticleServiceRes;
import com.jinhaoxun.dubbo.module.article.service.ArticleService;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class ArticleBusiness {

    @Reference
    private ArticleService articleService;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListActionReq 筛选条件参数
     * @return GetArticleListActionRes 获取到的文章列表
     */
    @HystrixCommand(fallbackMethod = "getArticleListFallBack")
    public GetArticleListActionRes getArticleList(GetArticleListActionReq getArticleListActionReq) throws Exception {
        GetArticleListServiceReq getArticleListServiceReq = new GetArticleListServiceReq();
        BeanUtils.copyProperties(getArticleListActionReq, getArticleListServiceReq);
        GetArticleListActionRes getArticleListActionRes = new GetArticleListActionRes();
        GetArticleListServiceRes getArticleListServiceRes = articleService.getArticleList(getArticleListServiceReq);
        BeanUtils.copyProperties(getArticleListServiceRes, getArticleListActionRes);
        return getArticleListActionRes;
    }

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListReq 筛选条件参数
     * @param exception Hystrix抛出的异常
     * @return
     */
    public void getArticleListFallBack(GetArticleListServiceReq getArticleListReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleActionReq 文章信息参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addArticleFallBack")
    public void addArticle(AddArticleActionReq addArticleActionReq) throws Exception {
        AddArticleServiceReq addArticleServiceReq = new AddArticleServiceReq();
        BeanUtils.copyProperties(addArticleActionReq, addArticleServiceReq);
        articleService.addArticle(addArticleServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleReq 文章信息参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void addArticleFallBack(AddArticleServiceReq addArticleReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleActionReq 删除文章请求参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteArticleFallBack")
    public void deleteArticle(DeleteArticleActionReq deleteArticleActionReq) throws Exception {
        DeleteArticleServiceReq deleteArticleServiceReq = new DeleteArticleServiceReq();
        BeanUtils.copyProperties(deleteArticleActionReq, deleteArticleServiceReq);
        articleService.deleteArticle(deleteArticleServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleReq 删除文章请求参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void deleteArticleFallBack(DeleteArticleServiceReq deleteArticleReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleActionReq 更新文章请求参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "updateArticleFallBack")
    public void updateArticle(UpdateArticleActionReq updateArticleActionReq) throws Exception {
        UpdateArticleServiceReq updateArticleServiceReq = new UpdateArticleServiceReq();
        BeanUtils.copyProperties(updateArticleActionReq, updateArticleServiceReq);
        articleService.updateArticle(updateArticleServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleReq 更新文章请求参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void updateArticleFallBack(UpdateArticleServiceReq updateArticleReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleActionReq 获取文章请求参数
     * @return GetArticleActionRes
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "getArticleFallBack")
    public GetArticleActionRes getArticle(GetArticleActionReq getArticleActionReq) throws Exception {
        GetArticleServiceReq getArticleServiceReq = new GetArticleServiceReq();
        GetArticleActionRes getArticleActionRes = new GetArticleActionRes();
        BeanUtils.copyProperties(getArticleActionReq, getArticleServiceReq);
        GetArticleServiceRes getArticleServiceRes = articleService.getArticle(getArticleServiceReq);
        BeanUtils.copyProperties(getArticleServiceRes, getArticleActionRes);
        return getArticleActionRes;
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleReq 获取文章请求参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void getArticleFallBack(GetArticleServiceReq getArticleReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }
}
