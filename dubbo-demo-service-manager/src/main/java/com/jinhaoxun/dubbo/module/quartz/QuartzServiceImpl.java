package com.jinhaoxun.dubbo.module.quartz;

import com.jinhaoxun.dubbo.module.quartz.model.request.AddCronJobReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.AddSimpleJobReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.DeleteJobReq;
import com.jinhaoxun.dubbo.org.quartz.QuartzManager;
import com.jinhaoxun.dubbo.pojo.quartz.Task;
import com.jinhaoxun.dubbo.response.ResponseFactory;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.jinhaoxun.dubbo.util.idutil.IdUtil;
import com.jinhaoxun.dubbo.module.quartz.service.QuartzService;
import com.jinhaoxun.dubbo.module.quartz.service.TaskService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
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
     * @param addSimpleJobReq 任务参数
     * @return ResponseResult 是否新增成功
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult addSimpleJob(AddSimpleJobReq addSimpleJobReq) throws Exception {
/*        SimpleTrigger cronTrigger = timingTasks.setTrigger(date);
        JobDetail jobDetail = timingTasks.setJobDetail(id,op);
        timingTasks.startScheduler(jobDetail,cronTrigger);*/
        Task task = new Task();
        Date now = new Date();
        task.setCreateTime(now);
        task.setUpdateTime(now);
        task.setExecutionStatus(false);
        task.setExecutionTime(addSimpleJobReq.getDate());
        task.setUpdaterId(addSimpleJobReq.getOperatorId());
        JSONObject json = JSONObject.fromObject(addSimpleJobReq.getParams());
        task.setParams(json.toString());
        task.setType(addSimpleJobReq.getType());
        task.setTaskId(IdUtil.getId());
        task.setJobClass(addSimpleJobReq.getJobClass());
        iTaskService.save(task);
        if(addSimpleJobReq.getDate().getTime() <= now.getTime() + ADD_QUARTZ_TIME){
            quartzManager.addSimpleJob(addSimpleJobReq,task.getTaskId().toString());
        }
        return ResponseFactory.buildSuccessResponse("新增任务成功！");
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult addSimpleJobList() throws Exception {
        List<Task> taskList = (List<Task>)iTaskService.getTaskList().getData();
        AddSimpleJobReq addSimpleJobReq = new AddSimpleJobReq();
        taskList.forEach(throwingConsumerWrapper(n -> {
            addSimpleJobReq.setDate(n.getExecutionTime());
            addSimpleJobReq.setJobClass(n.getJobClass());
            addSimpleJobReq.setType(n.getType());
            addSimpleJobReq.setParams(JSONObject.fromObject(n.getParams()));
            addSimpleJobReq.setOperatorId(n.getUpdaterId());
            quartzManager.addSimpleJob(addSimpleJobReq,n.getTaskId().toString());
        }));
        return ResponseFactory.buildSuccessResponse("加入数据库Simple任务到任务列表成功！");
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult addCronJob(AddCronJobReq addCronJobReq) throws Exception {
        quartzManager.addCronJob(addCronJobReq);
        return ResponseFactory.buildSuccessResponse("新增任务成功！");
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobReq 删除任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult deleteJob(DeleteJobReq deleteJobReq) throws Exception {
        quartzManager.removeJob(deleteJobReq.getJobName(),deleteJobReq.getJobGroupName(),deleteJobReq.getTriggerName(),deleteJobReq.getTriggerGroupName());
        return ResponseFactory.buildSuccessResponse("删除任务成功！");
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult deleteScheduler() throws Exception {
        quartzManager.shutdown();
        return ResponseFactory.buildSuccessResponse("关闭调度器成功！");
    }
}
