package com.jinhaoxun.dubbo.module.article.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinhaoxun
 * @version 1.0
 * @date 2018-05-09
 * @description 更新文章请求实体类
 */
@Setter
@Getter
public class UpdateArticleServiceReq extends ServiceRequest {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 一级标签
     */
    private Integer primaryLabelCode;

    /**
     * 二级标签
     */
    private Integer secondaryLabelCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 更新人ID
     */
    private Long updaterId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

}
