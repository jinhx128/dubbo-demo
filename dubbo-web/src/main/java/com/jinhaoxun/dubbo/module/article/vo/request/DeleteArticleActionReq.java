package com.jinhaoxun.dubbo.module.article.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(required = true, value = "文章id", example = "123333333")
    @NotNull(message = "文章id不能为空")
    private Long articleId;

}
