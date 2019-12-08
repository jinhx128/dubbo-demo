package com.jinhaoxun.dubbo.module.apply;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.apply.ArticlePraise;
import com.jinhaoxun.dubbo.mapper.apply.ArticlePraiseMapper;
import com.jinhaoxun.dubbo.module.apply.service.ArticlePraiseService;
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
