package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.article.SecondaryLabel;
import com.jinhaoxun.dubbo.mapper.article.SecondaryLabelMapper;
import com.jinhaoxun.dubbo.module.article.service.SecondaryLabelService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 二级标签服务类
 */
@Service
@Component
public class SecondaryLabelServiceImpl extends ServiceImpl<SecondaryLabelMapper, SecondaryLabel> implements SecondaryLabelService {

}
