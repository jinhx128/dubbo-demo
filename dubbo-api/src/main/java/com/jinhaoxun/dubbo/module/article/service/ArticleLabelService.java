package com.jinhaoxun.dubbo.module.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.module.article.model.request.DeleteArticleLabelServiceReq;
import com.jinhaoxun.dubbo.module.article.model.request.GetArticleLabelServiceReq;
import com.jinhaoxun.dubbo.module.article.model.response.GetArticleLabelServiceRes;
import com.jinhaoxun.dubbo.pojo.article.ArticleLabel;
import com.jinhaoxun.dubbo.module.article.model.request.UpdateArticleServiceReq;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章标签服务接口
 */
public interface ArticleLabelService extends IService<ArticleLabel> {

    /**
     * @author jinhaoxun
     * @description 新增文章标签
     * @param articleLabel 文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    void addArticleLabel(ArticleLabel articleLabel) throws Exception;

    /**
     * @author jinhaoxun
     * @description 删除文章标签
     * @param deleteArticleLabelServiceReq 文章id
     * @return
     * @throws Exception
     */
    void deleteArticleLabelByArticleId(DeleteArticleLabelServiceReq deleteArticleLabelServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 更新文章标签
     * @param updateArticleServiceReq 更新的文章信息参数
     * @return
     * @throws Exception
     */
    void updateArticleLabel(UpdateArticleServiceReq updateArticleServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取文章标签
     * @param getArticleLabelServiceReq 文章id
     * @return GetArticleLabelServiceRes 获取的文章标签
     * @throws Exception
     */
    GetArticleLabelServiceRes getArticleLabelByArticleId(GetArticleLabelServiceReq getArticleLabelServiceReq) throws Exception;

}
