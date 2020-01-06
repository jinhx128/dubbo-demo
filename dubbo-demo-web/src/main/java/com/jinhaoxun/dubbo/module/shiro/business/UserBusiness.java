package com.jinhaoxun.dubbo.module.shiro.business;

import com.jinhaoxun.dubbo.module.shiro.model.request.*;
import com.jinhaoxun.dubbo.module.shiro.service.UserService;
import com.jinhaoxun.dubbo.po.shiro.User;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class UserBusiness {

    @Reference
    private UserService userService;

    /**
     * @author jinhaoxun
     * @description 进行登录
     * @param userLoginReq 登录信息参数
     * @param response 请求响应体
     * @return ResponseResult 登录操作结果
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addSessionFallBack")
    public ResponseResult addSession(UserLoginReq userLoginReq, HttpServletResponse response) throws Exception {
        return userService.addSession(userLoginReq, response);
    }

    /**
     * @author jinhaoxun
     * @description 进行登录
     * @param userLoginReq 登录信息参数
     * @param response 请求响应体
     * @return ResponseResult 登录操作结果
     * @throws Exception
     */
    public ResponseResult addSessionFallBack(UserLoginReq userLoginReq, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 用户注册
     * @param userRegisterReq 注册信息参数
     * @return ResponseResult 是否注册成功
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addUserFallBack")
    public ResponseResult addUser(UserRegisterReq userRegisterReq) throws Exception {
        ResponseResult responseResult = userService.addUser(userRegisterReq);
        return responseResult;
    }

    /**
     * @author jinhaoxun
     * @description 用户注册
     * @param userRegisterReq 注册信息参数
     * @return ResponseResult 是否注册成功
     * @throws Exception
     */
    public ResponseResult addUserFallBack(UserRegisterReq userRegisterReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 用户注销
     * @param userIdReq 用户id
     * @return ResponseResult 是否注销成功
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteUserFallBack")
    public ResponseResult deleteUser(UserIdReq userIdReq) throws Exception {
        return userService.deleteUser(userIdReq);
    }

    /**
     * @author jinhaoxun
     * @description 用户注销
     * @param userIdReq 用户id
     * @return ResponseResult 是否注销成功
     * @throws Exception
     */
    public ResponseResult deleteUserFallBack(UserIdReq userIdReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 获取验证码
     * @param getCodeReq 获取验证码类型参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "getCodeFallBack")
    public ResponseResult getCode(GetCodeReq getCodeReq) throws Exception {
        return userService.getCode(getCodeReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取验证码
     * @param getCodeReq 获取验证码类型参数
     * @return ResponseResult 成功提示信息
     * @throws Exception
     */
    public ResponseResult getCodeFallBack(GetCodeReq getCodeReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 验证码登录
     * @param getCodeReq 登录信息参数
     * @param response 请求响应体
     * @return ResponseResult 登录操作结果
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addCodeSessionFallBack")
    public ResponseResult addCodeSession(GetCodeReq getCodeReq, HttpServletResponse response) throws Exception {
        return userService.addCodeSession(getCodeReq,response);
    }

    /**
     * @author jinhaoxun
     * @description 验证码登录
     * @param getCodeReq 登录信息参数
     * @param response 请求响应体
     * @return ResponseResult 登录操作结果
     * @throws Exception
     */
    public ResponseResult addCodeSessionFallBack(GetCodeReq getCodeReq, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 修改密码
     * @param updatePasswordReq 修改密码信息参数
     * @return ResponseResult 修改密码操作结果
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "updatePasswordFallBack")
    public ResponseResult updatePassword(UpdatePasswordReq updatePasswordReq) throws Exception {
        return userService.updatePassword(updatePasswordReq);
    }

    /**
     * @author jinhaoxun
     * @description 修改密码
     * @param updatePasswordReq 修改密码信息参数
     * @return ResponseResult 修改密码操作结果
     * @throws Exception
     */
    public ResponseResult updatePasswordFallBack(UpdatePasswordReq updatePasswordReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 账号封禁
     * @param userIdReq 用户id
     * @return ResponseResult 是否封禁成功
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "addBanFallBack")
    public ResponseResult addBan(UserIdReq userIdReq) throws Exception {
        return userService.addBan(userIdReq);
    }

    /**
     * @author jinhaoxun
     * @description 账号封禁
     * @param userIdReq 用户id
     * @return ResponseResult 是否封禁成功
     * @throws Exception
     */
    public ResponseResult addBanFallBack(UserIdReq userIdReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 账号解封
     * @param userIdReq 用户id
     * @return ResponseResult 是否解封成功
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "deleteBanFallBack")
    public ResponseResult deleteBan(UserIdReq userIdReq) throws Exception {
        return userService.deleteBan(userIdReq);
    }

    /**
     * @author jinhaoxun
     * @description 账号解封
     * @param userIdReq 用户id
     * @return ResponseResult 是否解封成功
     * @throws Exception
     */
    public ResponseResult deleteBanFallBack(UserIdReq userIdReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 获取用户信息
     * @param userIdReq 用户id
     * @return ResponseResult 获取到的用户信息
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "getUserFallBack")
    public ResponseResult getUser(UserIdReq userIdReq) throws Exception {
        return userService.getUser(userIdReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取用户信息
     * @param userIdReq 用户id
     * @return ResponseResult 获取到的用户信息
     * @throws Exception
     */
    public ResponseResult getUserFallBack(UserIdReq userIdReq) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 更新用户信息
     * @param user 用户信息
     * @return ResponseResult 是否更新成功
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "updateUserFallBack")
    public ResponseResult updateUser(User user) throws Exception {
        return userService.updateUser(user);
    }

    /**
     * @author jinhaoxun
     * @description 更新用户信息
     * @param user 用户信息
     * @return ResponseResult 是否更新成功
     * @throws Exception
     */
    public ResponseResult updateUserFallBack(User user) throws Exception {
        return null;
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    @HystrixCommand(fallbackMethod = "getUserListFallBack")
    public ResponseResult getUserList() throws Exception {
        return userService.getUserList();
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    public ResponseResult getUserListFallBack() throws Exception {
        return null;
    }
}
