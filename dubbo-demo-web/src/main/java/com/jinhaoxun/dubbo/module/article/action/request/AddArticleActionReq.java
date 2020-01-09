package com.jinhaoxun.dubbo.module.article.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
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
     * 一级标签
     */
    @NotNull(message = "一级标签不能为空")
    private Integer primaryLabelCode;

    /**
     * 二级标签
     */
    @NotNull(message = "二级标签不能为空")
    private Integer secondaryLabelCode;

    /**
     * 标题
     */
    @NotNull(message = "标题不能为空")
    private String title;

    /**
     * 作者ID
     */
    @NotNull(message = "作者id不能为空")
    private Long authorId;

    /**
     * 内容
     */
    @NotNull(message = "内容不能为空")
    private String content;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

}
