package com.jinhaoxun.dubbo.notify.service;

import com.jinhaoxun.dubbo.notify.dto.response.GetEmailCodeServiceRes;
import com.jinhaoxun.dubbo.notify.dto.response.GetPhoneCodeServiceRes;
import com.jinhaoxun.dubbo.notify.dto.request.GetEmailCodeServiceReq;
import com.jinhaoxun.dubbo.notify.dto.request.GetPhoneCodeServiceReq;

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
     * @param getPhoneCodeServiceReq 手机号
     * @return GetPhoneCodeServiceRes 获取的验证码
     * @throws Exception
     */
    GetPhoneCodeServiceRes getPhoneCode(GetPhoneCodeServiceReq getPhoneCodeServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取邮箱验证码
     * @param getEmailCodeServiceReq 邮箱
     * @return GetEmailCodeServiceRes 获取的验证码
     * @throws Exception
     */
    GetEmailCodeServiceRes getEmailCode(GetEmailCodeServiceReq getEmailCodeServiceReq) throws Exception;

}
