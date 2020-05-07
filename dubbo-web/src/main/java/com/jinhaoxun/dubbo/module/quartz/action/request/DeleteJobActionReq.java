package com.jinhaoxun.dubbo.module.quartz.action.request;

import com.jinhaoxun.dubbo.model.action.ActionRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 删除定时任务请求实体类
 */
@Setter
@Getter
public class DeleteJobActionReq extends ActionRequest {

    @NotNull(message = "任务名不能为空")
    private String jobName;

    @NotNull(message = "任务组名不能为空")
    private String jobGroupName;

    @NotNull(message = "调度器名不能为空")
    private String triggerName;

    @NotNull(message = "调度器组名不能为空")
    private String triggerGroupName;

}
