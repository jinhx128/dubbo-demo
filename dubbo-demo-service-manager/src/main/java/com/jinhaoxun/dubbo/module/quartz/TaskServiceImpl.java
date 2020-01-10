package com.jinhaoxun.dubbo.module.quartz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.quartz.Task;
import com.jinhaoxun.dubbo.mapper.quartz.TaskMapper;
import com.jinhaoxun.dubbo.module.quartz.service.TaskService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-30
 * @description 任务服务类
 */
@Service
@Component
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    /**
     * @author jinhaoxun
     * @description 获取数据库Simple任务列表
     * @return List<Task> Simple任务列表
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public List<Task> getTaskList() throws Exception {
        return taskMapper.getTaskList();
    }

    /**
     * @author jinhaoxun
     * @description 修改状态为已执行
     * @param taskId 任务ID
     * @return int 修改数据条数
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public int updateExecutionStatus(Long taskId) throws Exception {
        return taskMapper.updateExecutionStatus(taskId);
    }
}
