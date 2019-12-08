package com.jinhaoxun.dubbo.module.quartz.service;

import com.jinhaoxun.dubbo.module.quartz.model.request.*;
import com.jinhaoxun.dubbo.response.ResponseResult;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description Quartz服务接口
 */
public interface QuartzService {
    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobReq 任务参数
     * @return ResponseResult 是否新增成功
     * @throws Exception
     */
    ResponseResult addSimpleJob(AddSimpleJobReq addSimpleJobReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult addSimpleJobList() throws Exception;

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult addCronJob(AddCronJobReq addCronJobReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobReq 删除任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult deleteJob(DeleteJobReq deleteJobReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    ResponseResult deleteScheduler() throws Exception;
}
