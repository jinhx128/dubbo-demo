package com.jinhaoxun.dubbo.module.quartz.business;

import com.jinhaoxun.dubbo.module.quartz.vo.request.AddCronJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.vo.request.AddSimpleJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.vo.request.DeleteJobActionReq;
import com.jinhaoxun.dubbo.quartz.dto.request.AddCronJobServiceReq;
import com.jinhaoxun.dubbo.quartz.dto.request.AddSimpleJobServiceReq;
import com.jinhaoxun.dubbo.quartz.dto.request.DeleteJobServiceReq;
import com.jinhaoxun.dubbo.quartz.service.QuartzService;
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
    public void addSimpleJob(AddSimpleJobActionReq addSimpleJobActionReq) throws Exception {
        AddSimpleJobServiceReq addSimpleJobServiceReq = new AddSimpleJobServiceReq();
        BeanUtils.copyProperties(addSimpleJobActionReq, addSimpleJobServiceReq);
        quartzService.addSimpleJob(addSimpleJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return
     * @throws Exception
     */
    public void addSimpleJobList() throws Exception {
        quartzService.addSimpleJobList();
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobActionReq 任务参数
     * @return
     * @throws Exception
     */
    public void addCronJob(AddCronJobActionReq addCronJobActionReq) throws Exception {
        AddCronJobServiceReq addCronJobServiceReq = new AddCronJobServiceReq();
        BeanUtils.copyProperties(addCronJobActionReq, addCronJobServiceReq);
        quartzService.addCronJob(addCronJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobActionReq 删除任务参数
     * @return
     * @throws Exception
     */
    public void deleteJob(DeleteJobActionReq deleteJobActionReq) throws Exception {
        DeleteJobServiceReq deleteJobServiceReq = new DeleteJobServiceReq();
        BeanUtils.copyProperties(deleteJobActionReq, deleteJobServiceReq);
        quartzService.deleteJob(deleteJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return
     * @throws Exception
     */
    public void deleteScheduler() throws Exception {
        quartzService.deleteScheduler();
    }
}
