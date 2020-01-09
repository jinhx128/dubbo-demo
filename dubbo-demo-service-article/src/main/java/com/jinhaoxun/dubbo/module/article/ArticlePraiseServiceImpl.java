package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.article.ArticlePraise;
import com.jinhaoxun.dubbo.mapper.article.ArticlePraiseMapper;
import com.jinhaoxun.dubbo.module.article.service.ArticlePraiseService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章点赞服务类
 */
@Service
@Component
public class ArticlePraiseServiceImpl extends ServiceImpl<ArticlePraiseMapper, ArticlePraise> implements ArticlePraiseService {

}
