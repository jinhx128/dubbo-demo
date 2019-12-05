package com.jinhaoxun.dubbo.module;

import com.jinhaoxun.dubbo.thirdparty.test.service.TestThirdParty;

/**
 * @Description: 测试模块
 * @Author: jinhaoxun
 * @Date: 2019/12/5 17:31
 * @Version: 1.0
 */
public class TestThirdPartyImpl implements TestThirdParty {
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
