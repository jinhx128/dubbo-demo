package com.jinhaoxun.dubbo.message.service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-12-09
 * @description 通知模块服务接口
 */
public interface MessageService {

    /**
     * @author jinhaoxun
     * @description 获取手机验证码
     * @param phone 手机号
     * @return ResponseResult 获取的验证码
     * @throws Exception
     */
    void SendSmsMessage(String phone) throws Exception;


}
