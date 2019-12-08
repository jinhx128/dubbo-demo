package com.jinhaoxun.dubbo.module.test;

import com.jinhaoxun.dubbo.module.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 测试前端控制器
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Api("测试接口")
public class TestController {

    @Reference
    private TestService testService;

    @PostMapping("/start")
    @ApiOperation("开始")
    public void start(String cron, String taskName) throws Exception {
    }

    @PostMapping("/startprint")
    @ApiOperation("打印")
    public void startPrint(int n) throws Exception {
    }

    @PostMapping("/testtaskexecutor")
    @ApiOperation("测试TaskExecutor")
    public void testTaskExecutor() throws Exception {
        testService.sendMessage1();
        testService.sendMessage2();
    }

    @PostMapping("/test1")
    @ApiOperation("测试TaskExecutor")
    public void testTask() throws Exception {
        String a = "62.00";
        long b = (long) Double.parseDouble(a) * 100;
    }

}
