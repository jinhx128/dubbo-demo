package com.jinhaoxun.dubbo.module.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.pojo.quartz.Task;

import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-30
 * @description 任务服务接口
 */
public interface TaskService extends IService<Task> {

    /**
     * @author jinhaoxun
     * @description 获取数据库Simple任务列表
     * @return List<Task> Simple任务列表
     * @throws Exception
     */
    List<Task> getTaskList() throws Exception;

    /**
     * @author jinhaoxun
     * @description 修改状态为已执行
     * @param taskId 任务ID
     * @return int 修改数据条数
     * @throws Exception
     */
    int updateExecutionStatus(Long taskId) throws Exception;

}
