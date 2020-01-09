package com.jinhaoxun.dubbo.module.article.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 删除文章请求实体类
 */
@Setter
@Getter
public class DeleteArticleServiceReq extends ServiceRequest {

    /**
     * 文章ID
     */
    @NotNull(message = "文章id不能为空")
    private Long articleId;

}
