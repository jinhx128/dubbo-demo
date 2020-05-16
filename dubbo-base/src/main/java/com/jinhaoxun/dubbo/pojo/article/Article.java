package com.jinhaoxun.dubbo.pojo.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 文章表实体类
 */
@Getter
@Setter
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("ARTICLE_ID")
    private Long articleId;

    /**
     * 作者ID
     */
    @TableField("AUTHOR_ID")
    private Long authorId;

    /**
     * 文章标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 内容
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 文章状态(草稿：0 已发表：1 )
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @TableField("UPDATER_ID")
    private Long updaterId;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
