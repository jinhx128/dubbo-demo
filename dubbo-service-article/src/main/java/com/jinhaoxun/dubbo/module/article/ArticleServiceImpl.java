package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.pojo.article.Article;
import com.jinhaoxun.dubbo.pojo.article.ArticleLabel;
import com.jinhaoxun.dubbo.module.article.model.request.*;
import com.jinhaoxun.dubbo.module.article.model.response.*;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.util.idutil.IdUtil;
import com.jinhaoxun.dubbo.mapper.article.ArticleMapper;
import com.jinhaoxun.dubbo.module.article.service.ArticleLabelService;
import com.jinhaoxun.dubbo.module.article.service.ArticleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章服务类
 */
@Service
@Component
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleLabelService articleLabelService;
    @Resource
    private ExceptionFactory exceptionFactory;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListServiceReq 筛选条件参数
     * @return GetArticleListServiceRes 获取到的文章列表
     */
    @HystrixCommand
    @Override
    public GetArticleListServiceRes getArticleList(GetArticleListServiceReq getArticleListServiceReq) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        if(getArticleListServiceReq.getKeyWord() == null){
            if(getArticleListServiceReq.getPrimaryLabelCode() != null){
                qw.eq(AbstractConstant.ARTICLE_PRIMARY_CODE,getArticleListServiceReq.getPrimaryLabelCode());
                if(getArticleListServiceReq.getSecondaryLabelCode() != null){
                    qw.eq(AbstractConstant.ARTICLE_SECONDARY_CODE,getArticleListServiceReq.getSecondaryLabelCode());
                }
            }
        }else{
            qw.like(AbstractConstant.ARTICLE_TITLE,getArticleListServiceReq.getKeyWord());
        }
        qw.eq(AbstractConstant.ARTICLE_STATUS,2);
        qw.orderByDesc(AbstractConstant.UPDATE_TIME);

        Page<Article> page = new Page<>(getArticleListServiceReq.getPage(), getArticleListServiceReq.getSize());
        List<Article> articleList = articleMapper.selectPage(page,qw).getRecords();
        int totals = articleMapper.selectCount(qw);
        GetArticleListServiceRes getArticleListRes = new GetArticleListServiceRes();
        getArticleListRes.setTotals(totals);
        getArticleListRes.setArticleList(articleList);
        return getArticleListRes;
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleServiceReq 文章信息参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void addArticle(AddArticleServiceReq addArticleServiceReq) throws Exception {
        String title = addArticleServiceReq.getTitle();
        int count = articleMapper.getArticleByTitle(title);
        if(count == 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_EXISTING.getCode(),ResponseMsg.ARTICLE_EXISTING.getMsg());
        }
        Article article = new Article();
        Long aritcleId = IdUtil.getId();
        article.setArticleId(aritcleId);
        article.setAuthorId(addArticleServiceReq.getAuthorId());
        article.setCommentAmount(0);
        article.setPraiseAmount(0);
        article.setReadAmount(0);
        Date date = new Date();
        article.setCreateTime(date);
        article.setUpdateTime(date);
        article.setUpdaterId(addArticleServiceReq.getAuthorId());
        article.setTitle(title);
        article.setStatus(addArticleServiceReq.getStatus());
        article.setContent(addArticleServiceReq.getContent());

        count = articleMapper.insert(article);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_ADD_FAIL.getCode(),ResponseMsg.ARTICLE_ADD_FAIL.getMsg());
        }

        ArticleLabel articleLabel = new ArticleLabel();
        Long labelId = IdUtil.getId();
        if(addArticleServiceReq.getPrimaryLabelCode() != 0){
            articleLabel.setSecondaryCode(addArticleServiceReq.getSecondaryLabelCode());
        }
        articleLabel.setLabelId(labelId);
        articleLabel.setArticleId(aritcleId);
        articleLabel.setPrimaryCode(addArticleServiceReq.getPrimaryLabelCode());
        articleLabel.setCreateTime(date);
        articleLabel.setUpdateTime(date);
        articleLabel.setUpdaterId(addArticleServiceReq.getAuthorId());
        articleLabelService.addArticleLabel(articleLabel);
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleServiceReq 删除文章请求参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void deleteArticle(DeleteArticleServiceReq deleteArticleServiceReq) throws Exception {
        Long articleId = deleteArticleServiceReq.getArticleId();
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return ;
        }
        int count = articleMapper.deleteById(articleId);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_DELETE_FAIL.getCode(),ResponseMsg.ARTICLE_DELETE_FAIL.getMsg());
        }
        DeleteArticleLabelServiceReq deleteArticleLabelServiceReq = new DeleteArticleLabelServiceReq();
        BeanUtils.copyProperties(deleteArticleServiceReq, deleteArticleLabelServiceReq);
        articleLabelService.deleteArticleLabelByArticleId(deleteArticleLabelServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleServiceReq 更新文章请求参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void updateArticle(UpdateArticleServiceReq updateArticleServiceReq) throws Exception {
        UpdateWrapper<Article> uw = new UpdateWrapper<>();
        Date date = new Date();
        Article article = new Article();
        article.setTitle(updateArticleServiceReq.getTitle());
        article.setContent(updateArticleServiceReq.getContent());
        article.setUpdateTime(date);
        article.setUpdaterId(updateArticleServiceReq.getUpdaterId());
        article.setStatus(updateArticleServiceReq.getStatus());
        uw.eq(AbstractConstant.ARTICLE_ID,updateArticleServiceReq.getArticleId());
        int count = articleMapper.update(article,uw);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_UPDATE_FAIL.getCode(),ResponseMsg.ARTICLE_UPDATE_FAIL.getMsg());
        }
        articleLabelService.updateArticleLabel(updateArticleServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleServiceReq 获取文章请求参数
     * @return GetArticleServiceRe
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public GetArticleServiceRes getArticle(GetArticleServiceReq getArticleServiceReq) throws Exception {
        Article article = articleMapper.selectById(getArticleServiceReq.getArticleId());
        if(article == null){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_GET_FAIL.getCode(),ResponseMsg.ARTICLE_GET_FAIL.getMsg());
        }
        GetArticleLabelServiceReq getArticleLabelServiceReq = new GetArticleLabelServiceReq();
        BeanUtils.copyProperties(getArticleServiceReq, getArticleLabelServiceReq);
        GetArticleLabelServiceRes getArticleLabelServiceRes = articleLabelService.getArticleLabelByArticleId(getArticleLabelServiceReq);

        GetArticleServiceRes getArticleServiceRes = new GetArticleServiceRes();
        getArticleServiceRes.setArticleId(article.getArticleId());
        getArticleServiceRes.setTitle(article.getTitle());
        getArticleServiceRes.setCreateTime(article.getCreateTime());
        getArticleServiceRes.setUpdateTime(article.getUpdateTime());
        getArticleServiceRes.setPraiseAmount(article.getPraiseAmount());
        getArticleServiceRes.setReadAmount(article.getReadAmount());
        getArticleServiceRes.setContent(article.getContent());
        getArticleServiceRes.setCommentAmount(article.getCommentAmount());
        getArticleServiceRes.setStatus(article.getStatus());

        getArticleServiceRes.setPrimaryLabel(getArticleLabelServiceRes.getPrimaryCode());
        getArticleServiceRes.setSecondaryLabel(getArticleLabelServiceRes.getSecondaryCode());

        return getArticleServiceRes;
    }
}
