package com.jinhaoxun.dubbo.module.shiro.business;

import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.module.shiro.model.request.*;
import com.jinhaoxun.dubbo.module.shiro.model.response.AddSessionResponse;
import com.jinhaoxun.dubbo.module.shiro.service.UserService;
import com.jinhaoxun.dubbo.pojo.shiro.User;
import com.jinhaoxun.dubbo.response.ResponseFactory;
import com.jinhaoxun.dubbo.response.ResponseResult;
import com.jinhaoxun.dubbo.util.requestutil.JwtUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.sf.json.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        AddSessionResponse addSessionResponse = (AddSessionResponse) userService.addSession(userLoginReq).getData();
        this.loginSuccess(addSessionResponse.getUserId(), addSessionResponse.getRealPassword(), response);
        return ResponseFactory.buildSuccessResponse("登录成功!");
    }

    /**
     * @author jinhaoxun
     * @description 登录后更新缓存，生成token，设置响应头部信息
     * @param userId 用户id
     * @param password 用户密码
     * @param response 请求响应体
     */
    private void loginSuccess(Long userId, String password, HttpServletResponse response) {
        // 清除可能存在的Shiro权限信息缓存
        String tokenKey= AbstractConstant.SHIRO_ROLE_PERMISSION_KEY + userId;
        if (redisTemplate.hasKey(tokenKey)) {
            redisTemplate.delete(tokenKey);
        }
        //获取当前时间戳
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());

        JSONObject json = new JSONObject();
        //生成token
        String token = JwtUtil.createToken(Long.toString(userId), password , currentTimeMillis);
        json.put("token",token );
        //更新RefreshToken缓存的时间戳
        String refreshToken = AbstractConstant.REFRESH_TOKEN + userId;
        redisTemplate.opsForValue().set(refreshToken, currentTimeMillis);
        redisTemplate.expire(refreshToken, AbstractConstant.REFRESH_TOKEN_CHECK_EXPIRATION_TIME, TimeUnit.SECONDS);
        //写入header
        response.setHeader(AbstractConstant.REQUEST_AUTH_HEADER, token);
        response.setHeader("Access-Control-Expose-Headers", AbstractConstant.REQUEST_AUTH_HEADER);
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
        return userService.addUser(userRegisterReq);
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
        AddSessionResponse addSessionResponse = (AddSessionResponse) userService.addCodeSession(getCodeReq).getData();
        this.loginSuccess(addSessionResponse.getUserId(), addSessionResponse.getRealPassword(), response);
        return ResponseFactory.buildSuccessResponse("登录成功!");
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
