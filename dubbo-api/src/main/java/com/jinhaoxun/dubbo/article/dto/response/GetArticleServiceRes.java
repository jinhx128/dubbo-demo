package com.jinhaoxun.dubbo.article.dto.response;

import com.jinhaoxun.dubbo.vo.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章响应实体类
 */
@Setter
@Getter
public class GetArticleServiceRes extends ServiceResponse {

    private Long authorId;

    private Long articleId;

    private String title;

    private String content;

    private String label;

    /**
     * 文章状态(草稿：0 已发表：1 )
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
