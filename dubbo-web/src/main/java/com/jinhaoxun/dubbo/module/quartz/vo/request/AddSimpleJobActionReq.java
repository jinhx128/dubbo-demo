package com.jinhaoxun.dubbo.module.quartz.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 新增Simple定时任务请求实体类
 */
@Setter
@Getter
public class AddSimpleJobActionReq extends ActionRequest {

    @ApiModelProperty(required = true, value = "任务类", example = "任务类")
    @NotNull(message = "任务类不能为空")
    private String jobClass;

    @ApiModelProperty(required = true, value = "任务执行时间", example = "任务执行时间")
    @NotNull(message = "任务执行时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @ApiModelProperty(required = true, value = "参数", example = "解析后的数据")
    @NotNull(message = "参数不能为空")
    private Map<String, String> params = new HashMap<>();

    @ApiModelProperty(required = true, value = "操作人id", example = "操作人id")
    @NotNull(message = "操作人id不能为空")
    private Long operatorId;

    @ApiModelProperty(required = true, value = "任务类型", example = "任务类型")
    @NotNull(message = "任务类型不能为空")
    private Integer type;

}
