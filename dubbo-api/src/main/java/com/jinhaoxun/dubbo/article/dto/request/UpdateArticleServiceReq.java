package com.jinhaoxun.dubbo.article.dto.request;

import com.jinhaoxun.dubbo.vo.service.ServiceRequest;
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
     * 标签
     */
    private String label;

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
