package com.jinhaoxun.dubbo.common.login;

import com.jinhaoxun.dubbo.vo.base.Model;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务端缓存的用户信息对象
 * @Auther: jinhaoxun
 * @Date: 2019/1/22 16:36
 * @Description:
 */
@Setter
@Getter
public class UserServer extends Model {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 微信 OPENID
     */
    private String openId;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String IDNo;
    /**
     * 省编码
     */
    private String provinceId;
    /**
     * 市编码
     */
    private String cityId;
    /**
     * 是否校验登陆
     */
    private boolean secondLogin = false;
    
    private String check;

}
