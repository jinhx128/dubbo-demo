package com.jinhaoxun.dubbo.module.user.business;

import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.vo.action.ActionPageableRequest;
import com.jinhaoxun.dubbo.vo.service.ServicePageableRequest;
import com.jinhaoxun.dubbo.module.user.vo.request.*;
import com.jinhaoxun.dubbo.module.user.vo.response.GetUserActionRes;
import com.jinhaoxun.dubbo.module.user.vo.response.GetUserListActionRes;
import com.jinhaoxun.dubbo.user.dto.request.*;
import com.jinhaoxun.dubbo.user.dto.response.AddSessionServiceRes;
import com.jinhaoxun.dubbo.user.dto.response.GetUserListServiceRes;
import com.jinhaoxun.dubbo.user.dto.response.GetUserServiceRes;
import com.jinhaoxun.dubbo.user.service.UserService;
import com.jinhaoxun.dubbo.util.requestutil.JwtUtil;
import net.sf.json.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
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
     * @param userLoginActionReq 登录信息参数
     * @param httpServletResponse 请求响应体
     * @return
     * @throws Exception
     */
    public void addSession(UserLoginActionReq userLoginActionReq, HttpServletResponse httpServletResponse) throws Exception {
        UserLoginServiceReq userLoginServiceReq = new UserLoginServiceReq();
        BeanUtils.copyProperties(userLoginActionReq, userLoginServiceReq);
        AddSessionServiceRes addSessionResponse = userService.addSession(userLoginServiceReq);
        this.loginSuccess(addSessionResponse.getUserId(), addSessionResponse.getRealPassword(), httpServletResponse);
    }

    /**
     * @author jinhaoxun
     * @description 登录后更新缓存，生成token，设置响应头部信息
     * @param userId 用户id
     * @param password 用户密码
     * @param httpServletResponse 请求响应体
     */
    private void loginSuccess(Long userId, String password, HttpServletResponse httpServletResponse) {
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
        httpServletResponse.setHeader(AbstractConstant.REQUEST_AUTH_HEADER, token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", AbstractConstant.REQUEST_AUTH_HEADER);
    }

    /**
     * @author jinhaoxun
     * @description 用户注册
     * @param userRegisterActionReq 注册信息参数
     * @return
     * @throws Exception
     */
    public void addUser(UserRegisterActionReq userRegisterActionReq) throws Exception {
        UserRegisterServiceReq userRegisterServiceReq = new UserRegisterServiceReq();
        BeanUtils.copyProperties(userRegisterActionReq, userRegisterServiceReq);
        userService.addUser(userRegisterServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 用户注销
     * @param deleteUserActionReq 用户id
     * @return
     * @throws Exception
     */
    public void deleteUser(DeleteUserActionReq deleteUserActionReq) throws Exception {
        DeleteUserServiceReq deleteUserServiceReq = new DeleteUserServiceReq();
        BeanUtils.copyProperties(deleteUserActionReq, deleteUserServiceReq);
        userService.deleteUser(deleteUserServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取验证码
     * @param getCodeActionReq 获取验证码类型参数
     * @return
     * @throws Exception
     */
    public void getCode(GetCodeActionReq getCodeActionReq) throws Exception {
        GetCodeServiceReq getCodeServiceReq = new GetCodeServiceReq();
        BeanUtils.copyProperties(getCodeActionReq, getCodeServiceReq);
        userService.getCode(getCodeServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 验证码登录
     * @param codeUserLoginActionReq 登录信息参数
     * @param httpServletResponse 请求响应体
     * @return
     * @throws Exception
     */
    public void addCodeSession(CodeUserLoginActionReq codeUserLoginActionReq, HttpServletResponse httpServletResponse) throws Exception {
        CodeUserLoginServiceReq codeUserLoginServiceReq = new CodeUserLoginServiceReq();
        BeanUtils.copyProperties(codeUserLoginActionReq, codeUserLoginServiceReq);
        AddSessionServiceRes addSessionServiceRes = userService.addCodeSession(codeUserLoginServiceReq);
        this.loginSuccess(addSessionServiceRes.getUserId(), addSessionServiceRes.getRealPassword(), httpServletResponse);
    }

    /**
     * @author jinhaoxun
     * @description 修改密码
     * @param updatePasswordActionReq 修改密码信息参数
     * @return
     * @throws Exception
     */
    public void updatePassword(UpdatePasswordActionReq updatePasswordActionReq) throws Exception {
        UpdatePasswordServiceReq updatePasswordServiceReq = new UpdatePasswordServiceReq();
        BeanUtils.copyProperties(updatePasswordActionReq, updatePasswordServiceReq);
        userService.updatePassword(updatePasswordServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 账号封禁
     * @param addBanActionReq 用户id
     * @return ResponseResult 是否封禁成功
     * @throws Exception
     */
    public void addBan(AddBanActionReq addBanActionReq) throws Exception {
        AddBanServiceReq addBanServiceReq = new AddBanServiceReq();
        BeanUtils.copyProperties(addBanActionReq, addBanServiceReq);
        userService.addBan(addBanServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 账号解封
     * @param deleteBanActionReq 用户id
     * @return
     * @throws Exception
     */
    public void deleteBan(DeleteBanActionReq deleteBanActionReq) throws Exception {
        DeleteBanServiceReq deleteBanServiceReq = new DeleteBanServiceReq();
        BeanUtils.copyProperties(deleteBanActionReq, deleteBanServiceReq);
        userService.deleteBan(deleteBanServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取用户信息
     * @param getUserActionReq 用户id
     * @return GetUserActionRes 获取到的用户信息
     * @throws Exception
     */
    public GetUserActionRes getUser(GetUserActionReq getUserActionReq) throws Exception {
        GetUserServiceReq getUserServiceReq = new GetUserServiceReq();
        BeanUtils.copyProperties(getUserActionReq, getUserServiceReq);
        GetUserServiceRes getUserServiceRes = userService.getUser(getUserServiceReq);
        GetUserActionRes getUserActionRes = new GetUserActionRes();
        BeanUtils.copyProperties(getUserServiceRes, getUserActionRes);
        return getUserActionRes;
    }

    /**
     * @author jinhaoxun
     * @description 更新用户信息
     * @param updateUserActionReq 用户信息
     * @return
     * @throws Exception
     */
    public void updateUser(UpdateUserActionReq updateUserActionReq) throws Exception {
        UpdateUserServiceReq updateUserServiceReq = new UpdateUserServiceReq();
        BeanUtils.copyProperties(updateUserActionReq, updateUserServiceReq);
        userService.updateUser(updateUserServiceReq);
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @param actionPageableRequest 获取所有账号列表入参
     * @return List<User> 获取到的账号列表
     * @throws Exception
     */
    public GetUserListActionRes getUserList(ActionPageableRequest actionPageableRequest) throws Exception {
        ServicePageableRequest servicePageableRequest = new ServicePageableRequest();
        BeanUtils.copyProperties(actionPageableRequest, servicePageableRequest);
        GetUserListServiceRes getUserListServiceRes = userService.getUserList(servicePageableRequest);
        GetUserListActionRes getUserListActionRes = new GetUserListActionRes();
        getUserListActionRes.setTotals(getUserListServiceRes.getTotals());
        getUserListActionRes.setUserList(getUserListServiceRes.getUserList());
        return getUserListActionRes;
    }
}
