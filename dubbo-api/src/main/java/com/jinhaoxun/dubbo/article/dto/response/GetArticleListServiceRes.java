package com.jinhaoxun.dubbo.article.dto.response;

import com.jinhaoxun.dubbo.vo.service.ServicePageableResponse;
import com.jinhaoxun.dubbo.pojo.article.Article;
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
public class GetArticleListServiceRes extends ServicePageableResponse {

    private List<Article> articleList;

}
