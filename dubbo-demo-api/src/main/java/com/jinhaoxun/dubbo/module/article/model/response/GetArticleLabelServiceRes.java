package com.jinhaoxun.dubbo.module.article.model.response;

import com.jinhaoxun.dubbo.model.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 获取文章响应实体类
 */
@Setter
@Getter
public class GetArticleLabelServiceRes extends ServiceResponse {

    /**
     * 主键ID
     */
    private Long labelId;

    /**
     * 关联文章ID
     */
    private Long articleId;

    /**
     * 关联一级标签编码(个人成长：1 心情随笔：2 技术分享：3 资源分享：4 )
     */
    private Integer primaryCode;

    /**
     * 关联二级标签编码(后台：1 前端：2 数据库：3 等)
     */
    private Integer secondaryCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人ID
     */
    private Long updaterId;

}
