package com.jinhaoxun.dubbo.module.article.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章请求实体类
 */
@Setter
@Getter
public class GetArticleLabelServiceReq extends ServiceRequest {

    /**
     * 文章ID
     */
    private Long articleId;

}
