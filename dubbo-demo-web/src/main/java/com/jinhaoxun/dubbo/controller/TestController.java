package com.jinhaoxun.dubbo.controller;

import com.jinhaoxun.dubbo.module.testservice.service.TestService;
import com.jinhaoxun.dubbo.thirdparty.test.service.TestThirdParty;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试前端控制器
 * @Author: jinhaoxun
 * @Date: 2019/12/4 14:35
 * @Version: 1.0
 */
@RestController
public class TestController {

    @Reference(version = "1.0.0")
    private TestService testService;

    @Reference(version = "1.0.0")
    private TestThirdParty testThirdParty;

    /**
     * @Author jinhaoxun
     * @Description
     * @Return java.lang.String
     * @Exception Exception
     * @Date 2019/12/4 14:37
     * @Version 1.0
     */
    @GetMapping(value="/sayhello", produces = "application/json; charset=UTF-8")
    public String sayHello(){
        return testService.sayHello();
    }

    /**
     * @Author jinhaoxun
     * @Description
     * @Return java.lang.String
     * @Exception Exception
     * @Date 2019/12/4 14:37
     * @Version 1.0
     */
    @GetMapping(value="/sayhello1", produces = "application/json; charset=UTF-8")
    public String sayHello1(){
        return testThirdParty.sayHello();
    }
}
