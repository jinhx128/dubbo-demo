package com.jinhaoxun.dubbo.module.article.vo.response;

import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("获取文章响应实体类")
public class GetArticleActionRes extends ActionResponse {

    @ApiModelProperty(required = true, value = "作者id", example = "123333333")
    private Long authorId;

    @ApiModelProperty(required = true, value = "文章id", example = "123333333")
    private Long articleId;

    @ApiModelProperty(required = true, value = "标题", example = "怎么学习Java")
    private String title;

    @ApiModelProperty(required = true, value = "内容", example = "就这样学")
    private String content;

    @ApiModelProperty(required = true, value = "标签", example = "Java")
    private String label;

    /**
     * 状态(草稿：0 已发表：1 )
     */
    @ApiModelProperty(required = true, value = "状态(草稿：0 已发表：1 )", example = "1")
    private Integer status;

    @ApiModelProperty(required = true, value = "创建时间", example = "2018-01-01 18:00:00")
    private LocalDateTime createTime;

    @ApiModelProperty(required = true, value = "更新时间", example = "2018-01-01 18:00:00")
    private LocalDateTime updateTime;

}
