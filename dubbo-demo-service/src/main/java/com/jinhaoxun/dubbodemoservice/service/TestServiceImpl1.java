package com.jinhaoxun.dubbodemoservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jinhaoxun.dubbodemoapi.service.TestService1;
import org.springframework.stereotype.Component;

/**
 * @Description: 测试服务实现类
 * @Author: jinhaoxun
 * @Date: 2019/12/4 11:25
 * @Version: 1.0
 */
@Service(version = "1.0.0")
@Component
public class TestServiceImpl1 implements TestService1 {

    /**
     * @Author jinhaoxun
     * @Description
     * @Param
     * @Return java.lang.String
     * @Exception Exception
     * @Date 2019/12/4 14:30
     * @Version 1.0
     */
    @Override
    public String sayHello() {
        return "Hello Dubbo";
    }
}
