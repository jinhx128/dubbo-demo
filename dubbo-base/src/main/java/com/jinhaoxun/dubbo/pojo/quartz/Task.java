package com.jinhaoxun.dubbo.pojo.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 任务表实体类
 */
@Setter
@Getter
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("TASK_ID")
    private Long taskId;

    /**
     * 类型（0：系统，1：用户）
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 参数
     */
    @TableField("PARAMS")
    private String params;

    /**
     * 执行时间
     */
    @TableField("EXECUTION_TIME")
    private LocalDateTime executionTime;

    /**
     * 执行状态（false：未执行，true：已执行）
     */
    @TableField("EXECUTION_STATUS")
    private Boolean executionStatus;

    /**
     * 任务类名
     */
    @TableField("JOB_CLASS")
    private String jobClass;

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
