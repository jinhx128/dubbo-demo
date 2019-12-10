package com.jinhaoxun.dubbo.module.quartz.business;

import com.jinhaoxun.dubbo.module.quartz.model.request.AddCronJobReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.AddSimpleJobReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.DeleteJobReq;
import com.jinhaoxun.dubbo.module.quartz.service.QuartzService;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class QuartzBusiness {

    @Reference
    private QuartzService quartzService;

    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addSimpleJobFallBack")
    public ResponseResult addSimpleJob(AddSimpleJobReq addSimpleJobReq) throws Exception {
        return quartzService.addSimpleJob(addSimpleJobReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addSimpleJobFallBack(AddSimpleJobReq addSimpleJobReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addSimpleJobListFallBack")
    public ResponseResult addSimpleJobList() throws Exception {
        return quartzService.addSimpleJobList();
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addSimpleJobListFallBack() throws Exception {
        return null;
    }


    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addCronJobFallBack")
    public ResponseResult addCronJob(AddCronJobReq addCronJobReq) throws Exception {
        return quartzService.addCronJob(addCronJobReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult addCronJobFallBack(AddCronJobReq addCronJobReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobReq 删除任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteJobFallBack")
    public ResponseResult deleteJob(DeleteJobReq deleteJobReq) throws Exception {
        return quartzService.deleteJob(deleteJobReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobReq 删除任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult deleteJobFallBack(DeleteJobReq deleteJobReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteSchedulerFallBack")
    public ResponseResult deleteScheduler() throws Exception {
        return quartzService.deleteScheduler();
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult deleteSchedulerFallBack() throws Exception {
        return null;
    }
}
