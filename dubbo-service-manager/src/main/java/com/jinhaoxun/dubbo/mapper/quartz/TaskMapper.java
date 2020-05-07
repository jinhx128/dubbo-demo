package com.jinhaoxun.dubbo.mapper.quartz;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhaoxun.dubbo.pojo.quartz.Task;

import java.util.List;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-30
 * @description 任务Mapper接口
 */
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * @author jinhaoxun
     * @description 获取数据库Simple任务列表
     * @return ResponseResult Simple任务列表
     */
    List<Task> getTaskList() throws Exception ;

    /**
     * @author jinhaoxun
     * @description 修改状态为已执行
     * @param taskId 任务ID
     * @return int 修改数据条数
     */
    int updateExecutionStatus(Long taskId) throws Exception ;

}
