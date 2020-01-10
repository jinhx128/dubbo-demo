package com.jinhaoxun.dubbo.module.quartz.business;

import com.jinhaoxun.dubbo.module.quartz.action.request.AddCronJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.action.request.AddSimpleJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.action.request.DeleteJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.AddCronJobServiceReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.AddSimpleJobServiceReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.DeleteJobServiceReq;
import com.jinhaoxun.dubbo.module.quartz.service.QuartzService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
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
     * @param addSimpleJobActionReq 任务参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addSimpleJobFallBack")
    public void addSimpleJob(AddSimpleJobActionReq addSimpleJobActionReq) throws Exception {
        AddSimpleJobServiceReq addSimpleJobServiceReq = new AddSimpleJobServiceReq();
        BeanUtils.copyProperties(addSimpleJobActionReq, addSimpleJobServiceReq);
        quartzService.addSimpleJob(addSimpleJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobActionReq 任务参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void addSimpleJobFallBack(AddSimpleJobActionReq addSimpleJobActionReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addSimpleJobListFallBack")
    public void addSimpleJobList() throws Exception {
        quartzService.addSimpleJobList();
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void addSimpleJobListFallBack(Throwable exception) throws Exception {
        throw (Exception) exception;
    }


    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobActionReq 任务参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addCronJobFallBack")
    public void addCronJob(AddCronJobActionReq addCronJobActionReq) throws Exception {
        AddCronJobServiceReq addCronJobServiceReq = new AddCronJobServiceReq();
        BeanUtils.copyProperties(addCronJobActionReq, addCronJobServiceReq);
        quartzService.addCronJob(addCronJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobActionReq 任务参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void addCronJobFallBack(AddCronJobActionReq addCronJobActionReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobActionReq 删除任务参数
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteJobFallBack")
    public void deleteJob(DeleteJobActionReq deleteJobActionReq) throws Exception {
        DeleteJobServiceReq deleteJobServiceReq = new DeleteJobServiceReq();
        BeanUtils.copyProperties(deleteJobActionReq, deleteJobServiceReq);
        quartzService.deleteJob(deleteJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobActionReq 删除任务参数
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void deleteJobFallBack(DeleteJobActionReq deleteJobActionReq, Throwable exception) throws Exception {
        throw (Exception) exception;
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteSchedulerFallBack")
    public void deleteScheduler() throws Exception {
        quartzService.deleteScheduler();
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @param exception Hystrix抛出的异常
     * @return
     * @throws Exception
     */
    public void deleteSchedulerFallBack(Throwable exception) throws Exception {
        throw (Exception) exception;
    }
}
