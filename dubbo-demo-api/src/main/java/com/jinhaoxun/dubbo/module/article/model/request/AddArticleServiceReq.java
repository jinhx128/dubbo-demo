package com.jinhaoxun.dubbo.module.article.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 新增文章请求实体类
 */
@Setter
@Getter
public class AddArticleServiceReq extends ServiceRequest {

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
     * 作者ID
     */
    private Long authorId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

}
