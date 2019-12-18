package com.jinhaoxun.dubbo.thirdparty.notify.service;

import com.jinhaoxun.dubbo.response.ResponseResult;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 通知模块服务接口
 */
public interface NotifyService {

    /**
     * @author jinhaoxun
     * @description 获取手机验证码
     * @param phone 手机号
     * @return ResponseResult 获取的验证码
     * @throws Exception
     */
    ResponseResult getPhoneCode(String phone) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取邮箱验证码
     * @param email 邮箱
     * @return ResponseResult 获取的验证码
     * @throws Exception
     */
    ResponseResult getEmailCode(String email) throws Exception;

}
