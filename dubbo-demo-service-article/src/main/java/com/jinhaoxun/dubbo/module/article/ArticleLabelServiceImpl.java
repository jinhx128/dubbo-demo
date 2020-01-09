package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.module.article.model.request.DeleteArticleLabelServiceReq;
import com.jinhaoxun.dubbo.module.article.model.request.GetArticleLabelServiceReq;
import com.jinhaoxun.dubbo.module.article.model.response.GetArticleLabelServiceRes;
import com.jinhaoxun.dubbo.pojo.article.ArticleLabel;
import com.jinhaoxun.dubbo.module.article.model.request.UpdateArticleServiceReq;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.mapper.article.ArticleLabelMapper;
import com.jinhaoxun.dubbo.module.article.service.ArticleLabelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章标签服务类
 */
@Service
@Component
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements ArticleLabelService {

    @Resource
    private ArticleLabelMapper articleLabelMapper;
    @Resource
    private ExceptionFactory exceptionFactory;

    /**
     * @author jinhaoxun
     * @description 新增文章标签
     * @param articleLabel 文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void addArticleLabel(ArticleLabel articleLabel) throws Exception {
        int count = articleLabelMapper.insert(articleLabel);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_ADD_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_ADD_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 删除文章标签
     * @param deleteArticleLabelServiceReq 文章id
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void deleteArticleLabelByArticleId(DeleteArticleLabelServiceReq deleteArticleLabelServiceReq) throws Exception {
        int count = articleLabelMapper.deleteLabelByArticleId(deleteArticleLabelServiceReq.getArticleId());
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_DELETE_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_DELETE_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 更新文章标签
     * @param updateArticleServiceReq 更新的文章信息参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void updateArticleLabel(UpdateArticleServiceReq updateArticleServiceReq) throws Exception {
        UpdateWrapper<ArticleLabel> uw = new UpdateWrapper<>();
        Date date = new Date();
        ArticleLabel articleLabel = new ArticleLabel();
        articleLabel.setSecondaryCode(updateArticleServiceReq.getSecondaryLabelCode());
        articleLabel.setPrimaryCode(updateArticleServiceReq.getPrimaryLabelCode());
        articleLabel.setUpdateTime(date);
        articleLabel.setUpdaterId(updateArticleServiceReq.getUpdaterId());
        uw.eq(AbstractConstant.ARTICLE_ID,updateArticleServiceReq.getArticleId());
        int count = articleLabelMapper.update(articleLabel,uw);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_UPDATE_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_UPDATE_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 获取文章标签
     * @param getArticleLabelServiceReq 文章id
     * @return GetArticleLabelServiceRes 获取的文章标签
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public GetArticleLabelServiceRes getArticleLabelByArticleId(GetArticleLabelServiceReq getArticleLabelServiceReq) throws Exception {
        ArticleLabel articleLabel = articleLabelMapper.getLabelByArticleId(getArticleLabelServiceReq.getArticleId());
        if(articleLabel == null){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_GET_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_GET_FAIL.getMsg());
        }
        GetArticleLabelServiceRes getArticleLabelServiceRes = new GetArticleLabelServiceRes();
        BeanUtils.copyProperties(articleLabel, getArticleLabelServiceRes);
        return getArticleLabelServiceRes;
    }
}
