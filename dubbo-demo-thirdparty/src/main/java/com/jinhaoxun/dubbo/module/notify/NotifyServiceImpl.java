package com.jinhaoxun.dubbo.module.notify;

import com.jinhaoxun.dubbo.org.util.EmailUtil;
import com.jinhaoxun.dubbo.org.util.SmsUtil;
import com.jinhaoxun.dubbo.thirdparty.notify.model.request.GetEmailCodeServiceReq;
import com.jinhaoxun.dubbo.thirdparty.notify.model.request.GetPhoneCodeServiceReq;
import com.jinhaoxun.dubbo.thirdparty.notify.model.response.GetEmailCodeServiceRes;
import com.jinhaoxun.dubbo.thirdparty.notify.model.response.GetPhoneCodeServiceRes;
import com.jinhaoxun.dubbo.thirdparty.notify.service.NotifyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 通知模块服务类
 */
@Slf4j
@Service
@Component
public class NotifyServiceImpl implements NotifyService {

    @Resource
    private EmailUtil emailUtil;
    @Resource
    private SmsUtil smsUtil;

    /**
     * @author jinhaoxun
     * @description 获取手机验证码
     * @param getPhoneCodeServiceReq 手机号
     * @return GetPhoneCodeServiceRes 获取的验证码
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public GetPhoneCodeServiceRes getPhoneCode(GetPhoneCodeServiceReq getPhoneCodeServiceReq) throws Exception {
        GetPhoneCodeServiceRes getPhoneCodeServiceRes = new GetPhoneCodeServiceRes();
        getPhoneCodeServiceRes.setCode(smsUtil.getSms(getPhoneCodeServiceReq.getPhone()));
        return getPhoneCodeServiceRes;
    }

    /**
     * @author jinhaoxun
     * @description 获取邮箱验证码
     * @param getEmailCodeServiceReq 邮箱
     * @return GetEmailCodeServiceRes 获取的验证码
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public GetEmailCodeServiceRes getEmailCode(GetEmailCodeServiceReq getEmailCodeServiceReq) throws Exception {
        GetEmailCodeServiceRes getEmailCodeServiceRes = new GetEmailCodeServiceRes();
        getEmailCodeServiceRes.setCode(emailUtil.getEmail(getEmailCodeServiceReq.getEmail()));
        return getEmailCodeServiceRes;
    }
}
