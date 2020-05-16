package com.jinhaoxun.dubbo.quartz.service;

import com.jinhaoxun.dubbo.quartz.dto.request.AddCronJobServiceReq;
import com.jinhaoxun.dubbo.quartz.dto.request.AddSimpleJobServiceReq;
import com.jinhaoxun.dubbo.quartz.dto.request.DeleteJobServiceReq;

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
     * @param addSimpleJobServiceReq 任务参数
     * @return
     * @throws Exception
     */
    void addSimpleJob(AddSimpleJobServiceReq addSimpleJobServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return
     * @throws Exception
     */
    void addSimpleJobList() throws Exception;

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobServiceReq 任务参数
     * @return
     * @throws Exception
     */
    void addCronJob(AddCronJobServiceReq addCronJobServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobServiceReq 删除任务参数
     * @return
     * @throws Exception
     */
    void deleteJob(DeleteJobServiceReq deleteJobServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return
     * @throws Exception
     */
    void deleteScheduler() throws Exception;
}
