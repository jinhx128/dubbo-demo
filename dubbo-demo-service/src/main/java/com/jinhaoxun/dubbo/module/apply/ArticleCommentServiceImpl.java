package com.jinhaoxun.dubbo.module.apply;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.apply.ArticleComment;
import com.jinhaoxun.dubbo.mapper.apply.ArticleCommentMapper;
import com.jinhaoxun.dubbo.module.apply.service.ArticleCommentService;
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
