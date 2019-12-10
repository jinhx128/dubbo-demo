package com.jinhaoxun.dubbo.module.quartz.controller;

import com.jinhaoxun.dubbo.module.quartz.business.QuartzBusiness;
import com.jinhaoxun.dubbo.module.quartz.model.request.AddCronJobReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.AddSimpleJobReq;
import com.jinhaoxun.dubbo.module.quartz.model.request.DeleteJobReq;
import com.jinhaoxun.dubbo.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description Quartz前端控制器
 */
@RequestMapping("/quartz")
@RestController
@Api("Quartz接口")
public class QuartzController {

    @Resource
    private QuartzBusiness quartzBusiness;

    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @PostMapping(value = "/simplejob", produces = "application/json; charset=UTF-8")
    @ApiOperation("新增Simple任务")
    public ResponseResult addSimpleJob(@Validated @RequestBody AddSimpleJobReq addSimpleJobReq) throws Exception {
        return quartzBusiness.addSimpleJob(addSimpleJobReq);
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @PostMapping(value = "/simplejoblist", produces = "application/json; charset=UTF-8")
    @ApiOperation("加入数据库Simple任务到任务列表")
    public ResponseResult addSimpleJobList() throws Exception {
        return quartzBusiness.addSimpleJobList();
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobReq 任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @PostMapping(value = "/cronjob", produces = "application/json; charset=UTF-8")
    @ApiOperation("新增Cron任务")
    public ResponseResult addCronJob(@Validated @RequestBody AddCronJobReq addCronJobReq) throws Exception {
        return quartzBusiness.addCronJob(addCronJobReq);
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobReq 删除任务参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @DeleteMapping(value = "/job", produces = "application/json; charset=UTF-8")
    @ApiOperation("删除任务")
    public ResponseResult deleteJob(@Validated @RequestBody DeleteJobReq deleteJobReq) throws Exception {
        return quartzBusiness.deleteJob(deleteJobReq);
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @DeleteMapping(value = "/scheduler", produces = "application/json; charset=UTF-8")
    @ApiOperation("关闭调度器")
    public ResponseResult deleteScheduler() throws Exception {
        return quartzBusiness.deleteScheduler();
    }
}

