package com.jinhaoxun.dubbo.module.quartz;

import com.jinhaoxun.dubbo.framework.quartz.QuartzManager;
import com.jinhaoxun.dubbo.quartz.dto.request.AddCronJobServiceReq;
import com.jinhaoxun.dubbo.quartz.dto.request.AddSimpleJobServiceReq;
import com.jinhaoxun.dubbo.quartz.dto.request.DeleteJobServiceReq;
import com.jinhaoxun.dubbo.quartz.service.QuartzService;
import com.jinhaoxun.dubbo.quartz.service.TaskService;
import com.jinhaoxun.dubbo.pojo.quartz.Task;
import com.jinhaoxun.dubbo.util.idutil.IdUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import static com.jinhaoxun.dubbo.exception.LambdaThrowingConsumer.throwingConsumerWrapper;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description Quartz服务类
 */
@Slf4j
@Service
@Component
public class QuartzServiceImpl implements QuartzService {

    /**
     * 自定义UTF_8
     */
    private static final int ADD_QUARTZ_TIME = 60 * 60 * 1000;

    @Resource
    private QuartzManager quartzManager;
    @Resource
    private TaskService iTaskService;

    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobServiceReq 任务参数
     * @return
     * @throws Exception
     */
    @Override
    public void addSimpleJob(AddSimpleJobServiceReq addSimpleJobServiceReq) throws Exception {
/*        SimpleTrigger cronTrigger = timingTasks.setTrigger(date);
        JobDetail jobDetail = timingTasks.setJobDetail(id,op);
        timingTasks.startScheduler(jobDetail,cronTrigger);*/
        Task task = new Task();
        LocalDateTime now = LocalDateTime.now();
        task.setCreateTime(now);
        task.setUpdateTime(now);
        task.setExecutionStatus(false);
        task.setExecutionTime(addSimpleJobServiceReq.getDate());
        task.setUpdaterId(addSimpleJobServiceReq.getOperatorId());
        JSONObject json = JSONObject.fromObject(addSimpleJobServiceReq.getParams());
        task.setParams(json.toString());
        task.setType(addSimpleJobServiceReq.getType());
        task.setTaskId(IdUtil.getId());
        task.setJobClass(addSimpleJobServiceReq.getJobClass());
        iTaskService.save(task);
        if(addSimpleJobServiceReq.getDate().toInstant(ZoneOffset.of("+8")).toEpochMilli() <= now.toInstant(ZoneOffset.of("+8")).toEpochMilli() + ADD_QUARTZ_TIME){
            quartzManager.addSimpleJob(addSimpleJobServiceReq, task.getTaskId().toString());
        }
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @Override
    public void addSimpleJobList() throws Exception {
        List<Task> taskList = iTaskService.getTaskList();
        AddSimpleJobServiceReq addSimpleJobReq = new AddSimpleJobServiceReq();
        taskList.forEach(throwingConsumerWrapper(n -> {
            addSimpleJobReq.setDate(n.getExecutionTime());
            addSimpleJobReq.setJobClass(n.getJobClass());
            addSimpleJobReq.setType(n.getType());
            addSimpleJobReq.setParams(JSONObject.fromObject(n.getParams()));
            addSimpleJobReq.setOperatorId(n.getUpdaterId());
            quartzManager.addSimpleJob(addSimpleJobReq,n.getTaskId().toString());
        }));
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobServiceReq 任务参数
     * @return
     * @throws Exception
     */
    @Override
    public void addCronJob(AddCronJobServiceReq addCronJobServiceReq) throws Exception {
        quartzManager.addCronJob(addCronJobServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobServiceReq 删除任务参数
     * @return
     * @throws Exception
     */
    @Override
    public void deleteJob(DeleteJobServiceReq deleteJobServiceReq) throws Exception {
        quartzManager.removeJob(deleteJobServiceReq.getJobName(),
                deleteJobServiceReq.getJobGroupName(),deleteJobServiceReq.getTriggerName(),deleteJobServiceReq.getTriggerGroupName());
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return
     * @throws Exception
     */
    @Override
    public void deleteScheduler() throws Exception {
        quartzManager.shutdown();
    }
}
