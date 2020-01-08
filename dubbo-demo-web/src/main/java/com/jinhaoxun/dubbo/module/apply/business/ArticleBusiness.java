package com.jinhaoxun.dubbo.module.apply.business;

import com.jinhaoxun.dubbo.module.apply.model.request.*;
import com.jinhaoxun.dubbo.module.apply.service.ArticleService;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
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
     * @param getArticleListReq 筛选条件参数
     * @return ResponseResult 获取到的文章列表
     */
    @HystrixCommand(fallbackMethod = "getArticleListFallBack")
    public ResponseResult getArticleList(GetArticleListReq getArticleListReq) throws Exception {
        return articleService.getArticleList(getArticleListReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListReq 筛选条件参数
     * @return ResponseResult 获取到的文章列表
     */
    public ResponseResult getArticleListFallBack(GetArticleListReq getArticleListReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleReq 文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addArticleFallBack")
    public ResponseResult addArticle(AddArticleReq addArticleReq) throws Exception {
        return articleService.addArticle(addArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleReq 文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addArticleFallBack(AddArticleReq addArticleReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleReq 删除文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteArticleFallBack")
    public ResponseResult deleteArticle(DeleteArticleReq deleteArticleReq) throws Exception {
        return articleService.deleteArticle(deleteArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleReq 删除文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult deleteArticleFallBack(DeleteArticleReq deleteArticleReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleReq 更新文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "updateArticleFallBack")
    public ResponseResult updateArticle(UpdateArticleReq updateArticleReq) throws Exception {
        return articleService.updateArticle(updateArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleReq 更新文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult updateArticleFallBack(UpdateArticleReq updateArticleReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleReq 获取文章请求参数
     * @return ResponseResult 是否获取成功
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "getArticleFallBack")
    public ResponseResult getArticle(GetArticleReq getArticleReq) throws Exception {
        return articleService.getArticle(getArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleReq 获取文章请求参数
     * @return ResponseResult 是否获取成功
     * @throws Exception
     */
    public ResponseResult getArticleFallBack(GetArticleReq getArticleReq, Throwable e) throws Exception {
        throw (Exception) e;
    }
}
