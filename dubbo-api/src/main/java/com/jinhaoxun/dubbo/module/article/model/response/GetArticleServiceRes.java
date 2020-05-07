package com.jinhaoxun.dubbo.module.article.model.response;

import com.jinhaoxun.dubbo.model.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章响应实体类
 */
@Setter
@Getter
public class GetArticleServiceRes extends ServiceResponse {

    private Long articleId;

    private String title;

    private String content;

    private Integer praiseAmount;

    private Integer commentAmount;

    /**
     * 文章状态(草稿：1 已发表：2 )
     */
    private Integer status;

    private Integer readAmount;

    private Date createTime;

    private Date updateTime;

    private int primaryLabel;

    private int secondaryLabel;

}
