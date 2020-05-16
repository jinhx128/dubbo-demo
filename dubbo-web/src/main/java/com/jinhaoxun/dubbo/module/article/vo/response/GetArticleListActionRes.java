package com.jinhaoxun.dubbo.module.article.vo.response;

import com.jinhaoxun.dubbo.vo.action.ActionPageableResponse;
import com.jinhaoxun.dubbo.pojo.article.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章列表响应实体类
 */
@Setter
@Getter
@ApiModel("获取文章列表响应实体类")
public class GetArticleListActionRes extends ActionPageableResponse {

    @ApiModelProperty(required = true, value = "文章列表", example = "文章列表")
    private List<Article> articleList;

}
