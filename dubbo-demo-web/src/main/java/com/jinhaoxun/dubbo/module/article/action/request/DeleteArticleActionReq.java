package com.jinhaoxun.dubbo.module.article.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
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
public class DeleteArticleActionReq extends ActionRequest {

    /**
     * 文章ID
     */
    @NotNull(message = "文章id不能为空")
    private Long articleId;

}
