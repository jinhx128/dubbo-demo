package com.jinhaoxun.dubbo.module.quartz.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 删除定时任务请求实体类
 */
@Setter
@Getter
public class DeleteJobServiceReq extends ServiceRequest {

    private String jobName;

    private String jobGroupName;

    private String triggerName;

    private String triggerGroupName;

}
