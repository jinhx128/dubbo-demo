package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.article.dto.request.AddArticleServiceReq;
import com.jinhaoxun.dubbo.article.dto.request.DeleteArticleServiceReq;
import com.jinhaoxun.dubbo.article.dto.request.GetArticleServiceReq;
import com.jinhaoxun.dubbo.article.dto.request.UpdateArticleServiceReq;
import com.jinhaoxun.dubbo.article.dto.response.GetArticleListServiceRes;
import com.jinhaoxun.dubbo.article.dto.response.GetArticleServiceRes;
import com.jinhaoxun.dubbo.article.service.ArticleService;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.mapper.article.ArticleMapper;
import com.jinhaoxun.dubbo.module.article.model.request.GetArticleListServiceReq;
import com.jinhaoxun.dubbo.pojo.article.Article;
import com.jinhaoxun.dubbo.util.datautil.StringUtil;
import com.jinhaoxun.dubbo.util.idutil.IdUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
    private ExceptionFactory exceptionFactory;

    /**
     * @author jinhaoxun
     * @description 获取文章列表
     * @param getArticleListServiceReq 筛选条件参数
     * @return GetArticleListServiceRes 获取到的文章列表
     */
    @Override
    public GetArticleListServiceRes getArticleList(GetArticleListServiceReq getArticleListServiceReq) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        if(!StringUtil.isEmpty(getArticleListServiceReq.getKeyWord())){
            qw.like(AbstractConstant.ARTICLE_TITLE,getArticleListServiceReq.getKeyWord());
        }
        if(!StringUtil.isEmpty(getArticleListServiceReq.getLabel())){
            qw.eq(AbstractConstant.ARTICLE_LABEL,getArticleListServiceReq.getLabel());
        }
        qw.eq(AbstractConstant.ARTICLE_STATUS,1);
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
        article.setLabel(addArticleServiceReq.getLabel());
        LocalDateTime now = LocalDateTime.now();
        article.setCreateTime(now);
        article.setUpdateTime(now);
        article.setUpdaterId(addArticleServiceReq.getAuthorId());
        article.setTitle(title);
        article.setStatus(addArticleServiceReq.getStatus());
        article.setContent(addArticleServiceReq.getContent());

        count = articleMapper.insert(article);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_ADD_FAIL.getCode(),ResponseMsg.ARTICLE_ADD_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 删除文章
     * @param deleteArticleServiceReq 删除文章请求参数
     * @return
     * @throws Exception
     */
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
    }

    /**
     * @author jinhaoxun
     * @description 更新文章
     * @param updateArticleServiceReq 更新文章请求参数
     * @return
     * @throws Exception
     */
    @Override
    public void updateArticle(UpdateArticleServiceReq updateArticleServiceReq) throws Exception {
        UpdateWrapper<Article> uw = new UpdateWrapper<>();
        Article article = new Article();
        article.setTitle(updateArticleServiceReq.getTitle());
        article.setContent(updateArticleServiceReq.getContent());
        article.setLabel(updateArticleServiceReq.getLabel());
        article.setUpdateTime(LocalDateTime.now());
        article.setUpdaterId(updateArticleServiceReq.getUpdaterId());
        article.setStatus(updateArticleServiceReq.getStatus());
        uw.eq(AbstractConstant.ARTICLE_ID,updateArticleServiceReq.getArticleId());
        int count = articleMapper.update(article,uw);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_UPDATE_FAIL.getCode(),ResponseMsg.ARTICLE_UPDATE_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 获取文章信息
     * @param getArticleServiceReq 获取文章请求参数
     * @return GetArticleServiceRe
     * @throws Exception
     */
    @Override
    public GetArticleServiceRes getArticle(GetArticleServiceReq getArticleServiceReq) throws Exception {
        Article article = articleMapper.selectById(getArticleServiceReq.getArticleId());
        if(article == null){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_GET_FAIL.getCode(),ResponseMsg.ARTICLE_GET_FAIL.getMsg());
        }

        GetArticleServiceRes getArticleServiceRes = new GetArticleServiceRes();
        getArticleServiceRes.setArticleId(article.getArticleId());
        getArticleServiceRes.setTitle(article.getTitle());
        getArticleServiceRes.setCreateTime(article.getCreateTime());
        getArticleServiceRes.setUpdateTime(article.getUpdateTime());
        getArticleServiceRes.setLabel(article.getLabel());
        getArticleServiceRes.setContent(article.getContent());
        getArticleServiceRes.setStatus(article.getStatus());
        getArticleServiceRes.setAuthorId(article.getAuthorId());

        return getArticleServiceRes;
    }
}
