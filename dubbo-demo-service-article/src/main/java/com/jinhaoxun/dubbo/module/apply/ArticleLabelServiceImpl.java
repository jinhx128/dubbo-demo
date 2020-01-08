package com.jinhaoxun.dubbo.module.apply;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.po.apply.ArticleLabel;
import com.jinhaoxun.dubbo.module.apply.model.request.UpdateArticleReq;
import com.jinhaoxun.dubbo.response.ResponseFactory;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.jinhaoxun.dubbo.mapper.apply.ArticleLabelMapper;
import com.jinhaoxun.dubbo.module.apply.service.ArticleLabelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    public ResponseResult addArticleLabel(ArticleLabel articleLabel) throws Exception {
        int count = articleLabelMapper.insert(articleLabel);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_ADD_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_ADD_FAIL.getMsg());
        }
        return ResponseFactory.buildSuccessResponse("新增成功！");
    }

    /**
     * @author jinhaoxun
     * @description 删除文章标签
     * @param articleId 文章id
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult deleteArticleLabelByArticleId(Long articleId) throws Exception {
        int count = articleLabelMapper.deleteLabelByArticleId(articleId);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_DELETE_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_DELETE_FAIL.getMsg());
        }
        return ResponseFactory.buildSuccessResponse("删除成功！");
    }

    /**
     * @author jinhaoxun
     * @description 更新文章标签
     * @param updateArticleReq 更新的文章信息参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult updateArticleLabel(UpdateArticleReq updateArticleReq) throws Exception {
        UpdateWrapper<ArticleLabel> uw = new UpdateWrapper<>();
        Date date = new Date();
        ArticleLabel articleLabel = new ArticleLabel();
        articleLabel.setSecondaryCode(updateArticleReq.getSecondaryLabelCode());
        articleLabel.setPrimaryCode(updateArticleReq.getPrimaryLabelCode());
        articleLabel.setUpdateTime(date);
        articleLabel.setUpdaterId(updateArticleReq.getUpdaterId());
        uw.eq(AbstractConstant.ARTICLE_ID,updateArticleReq.getArticleId());
        int count = articleLabelMapper.update(articleLabel,uw);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_UPDATE_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_UPDATE_FAIL.getMsg());
        }
        return ResponseFactory.buildSuccessResponse("更新成功！");
    }

    /**
     * @author jinhaoxun
     * @description 获取文章标签
     * @param articleId 文章id
     * @return ResponseResult 获取的文章标签
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult getArticleLabelByArticleId(Long articleId) throws Exception {
        ArticleLabel articleLabel = articleLabelMapper.getLabelByArticleId(articleId);
        if(articleLabel == null){
            throw exceptionFactory.build(ResponseMsg.ARTICLE_LABEL_GET_FAIL.getCode(),ResponseMsg.ARTICLE_LABEL_GET_FAIL.getMsg());
        }
        return ResponseFactory.buildSuccessResponse(articleLabel,"获取成功！");
    }
}
