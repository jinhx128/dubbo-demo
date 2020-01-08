package com.jinhaoxun.dubbo.module.quartz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.po.quartz.Task;
import com.jinhaoxun.dubbo.response.ResponseFactory;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.jinhaoxun.dubbo.mapper.quartz.TaskMapper;
import com.jinhaoxun.dubbo.module.quartz.service.TaskService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

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
     * @return ResponseResult Simple任务列表
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult getTaskList(){
        return ResponseFactory.buildSuccessResponse(taskMapper.getTaskList());
    }

    /**
     * @author jinhaoxun
     * @description 修改状态为已执行
     * @param taskId 任务ID
     * @return ResponseResult 修改数据条数
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public ResponseResult updateExecutionStatus(Long taskId){
        return ResponseFactory.buildSuccessResponse(taskMapper.updateExecutionStatus(taskId));
    }
}
