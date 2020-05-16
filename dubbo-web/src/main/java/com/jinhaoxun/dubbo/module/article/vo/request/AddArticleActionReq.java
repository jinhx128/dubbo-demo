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
 * @description 新增文章请求实体类
 */
@Setter
@Getter
public class AddArticleActionReq extends ActionRequest {

    /**
     * 标签
     */
    @ApiModelProperty(required = true, value = "标签", example = "Java")
    @NotNull(message = "标签不能为空")
    private String label;

    /**
     * 标题
     */
    @ApiModelProperty(required = true, value = "标题", example = "怎么学习Java")
    @NotNull(message = "标题不能为空")
    private String title;

    /**
     * 作者ID
     */
    @ApiModelProperty(required = true, value = "作者id", example = "123333333")
    @NotNull(message = "作者id不能为空")
    private Long authorId;

    /**
     * 内容
     */
    @ApiModelProperty(required = true, value = "内容", example = "就这样学")
    @NotNull(message = "内容不能为空")
    private String content;

    /**
     * 状态(草稿：0 已发表：1 )
     */
    @ApiModelProperty(required = true, value = "状态(草稿：0 已发表：1 )", example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
