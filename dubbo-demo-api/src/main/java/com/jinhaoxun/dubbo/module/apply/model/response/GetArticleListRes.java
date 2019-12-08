package com.jinhaoxun.dubbo.module.apply.model.response;

import com.jinhaoxun.dubbo.po.apply.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章列表响应实体类
 */
@Setter
@Getter
@ToString
public class GetArticleListRes extends PageRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Article> articleList;

}
