package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.article.ArticleComment;
import com.jinhaoxun.dubbo.mapper.article.ArticleCommentMapper;
import com.jinhaoxun.dubbo.module.article.service.ArticleCommentService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章评论服务类
 */
@Service
@Component
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

}
