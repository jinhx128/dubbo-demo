package com.jinhaoxun.dubbo.module.test.service;


/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description RocketMQ服务接口
 */
public interface TestService {

    void sendMessage1() throws InterruptedException;

    void sendMessage2() throws InterruptedException;

}
