package com.jinhaoxun.dubbo.module.apply;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.po.apply.Article;
import com.jinhaoxun.dubbo.po.apply.ArticleLabel;
import com.jinhaoxun.dubbo.module.apply.model.request.*;
import com.jinhaoxun.dubbo.module.apply.model.response.*;
import com.jinhaoxun.dubbo.response.ResponseFactory;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.jinhaoxun.dubbo.util.idutil.IdUtil;
import com.jinhaoxun.dubbo.mapper.apply.ArticleMapper;
import com.jinhaoxun.dubbo.module.apply.service.ArticleLabelService;
import com.jinhaoxun.dubbo.module.apply.service.ArticleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private ArticleLabelService iArticleLabelService;
    @Resource
    private ExceptionFactory exceptionFactory;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListReq 筛选条件参数
     * @return ResponseResult 获取到的文章列表
     */
    @HystrixCommand
    @Override
    public ResponseResult getArticleList(GetArticleListReq getArticleListReq) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        if(getArticleListReq.getKeyWord() == null){
            if(getArticleListReq.getPrimaryLabelCode() != null){
                qw.eq(AbstractConstant.ARTICLE_PRIMARY_CODE,getArticleListReq.getPrimaryLabelCode());
                if(getArticleListReq.getSecondaryLabelCode() != null){
                    qw.eq(AbstractConstant.ARTICLE_SECONDARY_CODE,getArticleListReq.getSecondaryLabelCode());
                }
            }
        }else{
            qw.like(AbstractConstant.ARTICLE_TITLE,getArticleListReq.getKeyWord());
        }
        qw.eq(AbstractConstant.ARTICLE_STATUS,2);
        qw.orderByDesc(AbstractConstant.UPDATE_TIME);

        Page<Article> page = new Page<>(getArticleListReq.getCurrentPage(), getArticleListReq.getPageSize());
        List<Article> articleList = articleMapper.selectPage(page,qw).getRecords();
        int total = articleMapper.selectCount(qw);
        GetArticleListRes getArticleListRes = new GetArticleListRes();
        getArticleListRes.setTotal(total);
        getArticleListRes.setArticleList(articleList);
        return ResponseFactory.buildSuccessResponse(getArticleListRes,"获取成功！");
    }

    /**
     * @author jinhaoxun
     * @description 新增文章
     * @param addArticleReq 文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult addArticle(AddArticleReq addArticleReq) throws Exception {
        String title = addArticleReq.getTitle();
        int count = articleMapper.getArticleByTitle(title);
        if(count == 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_EXISTING.getCode(),ResponseMsg.ARTICLE_EXISTING.getMsg());
        }
        Article article = new Article();
        Long aritcleId = IdUtil.getId();
        article.setArticleId(aritcleId);
        article.setAuthorId(addArticleReq.getAuthorId());
        article.setCommentAmount(0);
        article.setPraiseAmount(0);
        article.setReadAmount(0);
        Date date = new Date();
        article.setCreateTime(date);
        article.setUpdateTime(date);
        article.setUpdaterId(addArticleReq.getAuthorId());
        article.setTitle(title);
        article.setStatus(addArticleReq.getStatus());
        article.setContent(addArticleReq.getContent());

        count = articleMapper.insert(article);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_ADD_FAIL.getCode(),ResponseMsg.ARTICLE_ADD_FAIL.getMsg());
        }

        ArticleLabel articleLabel = new ArticleLabel();
        Long labelId = IdUtil.getId();
        if(addArticleReq.getPrimaryLabelCode() != 0){
            articleLabel.setSecondaryCode(addArticleReq.getSecondaryLabelCode());
        }
        articleLabel.setLabelId(labelId);
        articleLabel.setArticleId(aritcleId);
        articleLabel.setPrimaryCode(addArticleReq.getPrimaryLabelCode());
        articleLabel.setCreateTime(date);
        articleLabel.setUpdateTime(date);
        articleLabel.setUpdaterId(addArticleReq.getAuthorId());
        return iArticleLabelService.addArticleLabel(articleLabel);
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleReq 删除文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult deleteArticle(DeleteArticleReq deleteArticleReq) throws Exception {
        Long articleId = deleteArticleReq.getArticleId();
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return ResponseFactory.buildSuccessResponse(true,"删除成功！");
        }
        int count = articleMapper.deleteById(articleId);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_DELETE_FAIL.getCode(),ResponseMsg.ARTICLE_DELETE_FAIL.getMsg());
        }
        return iArticleLabelService.deleteArticleLabelByArticleId(articleId);
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleReq 更新文章请求参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult updateArticle(UpdateArticleReq updateArticleReq) throws Exception {
        UpdateWrapper<Article> uw = new UpdateWrapper<>();
        Date date = new Date();
        Article article = new Article();
        article.setTitle(updateArticleReq.getTitle());
        article.setContent(updateArticleReq.getContent());
        article.setUpdateTime(date);
        article.setUpdaterId(updateArticleReq.getUpdaterId());
        article.setStatus(updateArticleReq.getStatus());
        uw.eq(AbstractConstant.ARTICLE_ID,updateArticleReq.getArticleId());
        int count = articleMapper.update(article,uw);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_UPDATE_FAIL.getCode(),ResponseMsg.ARTICLE_UPDATE_FAIL.getMsg());
        }
        return iArticleLabelService.updateArticleLabel(updateArticleReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleReq 获取文章请求参数
     * @return ResponseResult 是否获取成功
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult getArticle(GetArticleReq getArticleReq) throws Exception {
        Article article = articleMapper.selectById(getArticleReq.getArticleId());
        if(article == null){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_GET_FAIL.getCode(),ResponseMsg.ARTICLE_GET_FAIL.getMsg());
        }
        ResponseResult articleLabelResponseResult = iArticleLabelService.getArticleLabelByArticleId(article.getArticleId());
        ArticleLabel articleLabel = (ArticleLabel)articleLabelResponseResult.getData();
        GetArticleRes getArticleRes = new GetArticleRes();
        getArticleRes.setArticleId(article.getArticleId());
        getArticleRes.setTitle(article.getTitle());
        getArticleRes.setCreateTime(article.getCreateTime());
        getArticleRes.setUpdateTime(article.getUpdateTime());
        getArticleRes.setPraiseAmount(article.getPraiseAmount());
        getArticleRes.setReadAmount(article.getReadAmount());
        getArticleRes.setContent(article.getContent());
        getArticleRes.setCommentAmount(article.getCommentAmount());
        getArticleRes.setStatus(article.getStatus());

        getArticleRes.setPrimaryLabel(articleLabel.getPrimaryCode());
        getArticleRes.setSecondaryLabel(articleLabel.getSecondaryCode());

        return ResponseFactory.buildSuccessResponse(getArticleRes,"获取成功！");
    }
}
