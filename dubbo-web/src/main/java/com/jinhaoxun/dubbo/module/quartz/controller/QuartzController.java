package com.jinhaoxun.dubbo.module.quartz.controller;

import com.jinhaoxun.dubbo.vo.action.ActionRequest;
import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import com.jinhaoxun.dubbo.vo.http.HttpRequest;
import com.jinhaoxun.dubbo.vo.http.HttpResponse;
import com.jinhaoxun.dubbo.module.quartz.vo.request.AddCronJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.vo.request.AddSimpleJobActionReq;
import com.jinhaoxun.dubbo.module.quartz.business.QuartzBusiness;
import com.jinhaoxun.dubbo.module.quartz.vo.request.DeleteJobActionReq;
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
@Api(value = "/quartz", tags = "Quartz接口")
public class QuartzController {

    @Resource
    private QuartzBusiness quartzBusiness;

    /**
     * @author jinhaoxun
     * @description 新增Simple任务
     * @param addSimpleJobActionReqHttpRequest 任务参数
     * @return HttpResponse<ActionResponse>
     * @throws Exception
     */
    @PostMapping(value = "/addsimplejob", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "新增Simple任务", notes = "新增Simple任务接口描述")
    public HttpResponse<ActionResponse> addSimpleJob(@Validated @RequestBody HttpRequest<AddSimpleJobActionReq> addSimpleJobActionReqHttpRequest) throws Exception {
        quartzBusiness.addSimpleJob(addSimpleJobActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 加入数据库Simple任务到任务列表
     * @param actionRequestHttpRequest 统一请求参数
     * @return HttpResponse<ActionResponse>
     * @throws Exception
     */
    @PostMapping(value = "/addsimplejoblist", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "加入数据库Simple任务到任务列表", notes = "加入数据库Simple任务到任务列表接口描述")
    public HttpResponse<ActionResponse> addSimpleJobList(@RequestBody HttpRequest<ActionRequest> actionRequestHttpRequest) throws Exception {
        quartzBusiness.addSimpleJobList();
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 新增Cron任务
     * @param addCronJobActionReqHttpRequest 任务参数
     * @return HttpResponse<ActionResponse>
     * @throws Exception
     */
    @PostMapping(value = "/addcronjob", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "新增Cron任务", notes = "新增Cron任务接口描述")
    public HttpResponse<ActionResponse> addCronJob(@Validated @RequestBody HttpRequest<AddCronJobActionReq> addCronJobActionReqHttpRequest) throws Exception {
        quartzBusiness.addCronJob(addCronJobActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 删除任务
     * @param deleteJobActionReqHttpRequest 删除任务参数
     * @return HttpResponse<ActionResponse>
     * @throws Exception
     */
    @PostMapping(value = "/deletejob", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "删除任务", notes = "删除任务接口描述")
    public HttpResponse<ActionResponse> deleteJob(@Validated @RequestBody HttpRequest<DeleteJobActionReq> deleteJobActionReqHttpRequest) throws Exception {
        quartzBusiness.deleteJob(deleteJobActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 关闭调度器
     * @param actionRequestHttpRequest 统一请求参数
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
    @PostMapping(value = "/deletescheduler", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "关闭调度器", notes = "关闭调度器接口描述")
    public HttpResponse<ActionResponse> deleteScheduler(@RequestBody HttpRequest<ActionRequest> actionRequestHttpRequest) throws Exception {
        quartzBusiness.deleteScheduler();
        return HttpResponse.buildSuccess();
    }
}

