package com.jinhaoxun.dubbo.module.article.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionPageableRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章列表请求实体类
 */
@Setter
@Getter
public class GetArticleListActionReq extends ActionPageableRequest {

    /**
     * 标签
     */
    @ApiModelProperty(required = true, value = "标签", example = "Java")
    private String label;

    /**
     * 关键字
     */
    @ApiModelProperty(required = true, value = "关键字", example = "Java")
    private String keyWord;

}
