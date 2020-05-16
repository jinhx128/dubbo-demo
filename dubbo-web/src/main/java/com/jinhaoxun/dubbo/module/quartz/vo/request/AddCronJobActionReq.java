package com.jinhaoxun.dubbo.module.quartz.vo.request;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 新增Cron定时任务请求实体类
 */
@Setter
@Getter
public class AddCronJobActionReq extends ActionRequest {

    @ApiModelProperty(required = true, value = "任务名", example = "任务名")
    @NotNull(message = "任务名不能为空")
    private String jobName;

    @ApiModelProperty(required = true, value = "任务组名", example = "任务组名")
    @NotNull(message = "任务组名不能为空")
    private String jobGroupName;

    @ApiModelProperty(required = true, value = "触发器名", example = "触发器名")
    @NotNull(message = "触发器名不能为空")
    private String triggerName;

    @ApiModelProperty(required = true, value = "触发器组名", example = "触发器组名")
    @NotNull(message = "触发器组名不能为空")
    private String triggerGroupName;

    @ApiModelProperty(required = true, value = "任务类", example = "任务类")
    @NotNull(message = "任务类不能为空")
    private String jobClass;

    @ApiModelProperty(required = true, value = "任务执行时间", example = "任务执行时间")
    @NotNull(message = "任务执行时间不能为空")
    private String date;

    @ApiModelProperty(required = true, value = "参数", example = "参数")
    @NotNull(message = "参数不能为空")
    private Map<String, String> params = new HashMap<>();

}
