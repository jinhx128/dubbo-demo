package com.jinhaoxun.dubbo.module.article.model.request;

import com.jinhaoxun.dubbo.model.service.ServicePageableRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章列表请求实体类
 */
@Setter
@Getter
public class GetArticleListServiceReq extends ServicePageableRequest {

    /**
     * 一级标签
     */
    private Integer primaryLabelCode;

    /**
     * 二级标签
     */
    private Integer secondaryLabelCode;

    /**
     * 关键字
     */
    private String keyWord;

}
