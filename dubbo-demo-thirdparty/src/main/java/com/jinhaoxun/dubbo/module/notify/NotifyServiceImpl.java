package com.jinhaoxun.dubbo.module.notify;

import com.jinhaoxun.dubbo.org.util.EmailUtil;
import com.jinhaoxun.dubbo.org.util.SmsUtil;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.jinhaoxun.dubbo.thirdparty.notify.service.NotifyService;
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
     * @param phone 手机号
     * @return ResponseResult 获取的验证码
     * @throws Exception
     */
    @Override
    public ResponseResult getPhoneCode(String phone) throws Exception {
        return smsUtil.getSms(phone);
    }

    /**
     * @author jinhaoxun
     * @description 获取邮箱验证码
     * @param email 邮箱
     * @return ResponseResult 获取的验证码
     * @throws Exception
     */
    @Override
    public ResponseResult getEmailCode(String email) throws Exception {
        return emailUtil.getEmail(email);
    }
}
