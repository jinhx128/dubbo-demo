package com.jinhaoxun.dubbo.module.quartz.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(required = true, value = "任务名", example = "任务名")
    @NotNull(message = "任务名不能为空")
    private String jobName;

    @ApiModelProperty(required = true, value = "任务组名", example = "任务组名")
    @NotNull(message = "任务组名不能为空")
    private String jobGroupName;

    @ApiModelProperty(required = true, value = "调度器名", example = "调度器名")
    @NotNull(message = "调度器名不能为空")
    private String triggerName;

    @ApiModelProperty(required = true, value = "调度器组名", example = "调度器组名")
    @NotNull(message = "调度器组名不能为空")
    private String triggerGroupName;

}
