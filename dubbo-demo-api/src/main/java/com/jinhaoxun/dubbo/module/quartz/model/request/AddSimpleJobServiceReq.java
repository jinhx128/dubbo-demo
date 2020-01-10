package com.jinhaoxun.dubbo.module.quartz.model.request;

import com.jinhaoxun.dubbo.model.service.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

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
public class AddSimpleJobServiceReq extends ServiceRequest {

    private String jobClass;

    private Date date;

    private Map<String, String> params = new HashMap<>();

    private Long operatorId;

    private Integer type;

}
