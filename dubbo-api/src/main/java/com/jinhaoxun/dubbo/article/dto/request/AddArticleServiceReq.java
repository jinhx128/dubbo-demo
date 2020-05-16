package com.jinhaoxun.dubbo.article.dto.request;

import com.jinhaoxun.dubbo.vo.service.ServiceRequest;
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
     * 标签
     */
    private String label;

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
