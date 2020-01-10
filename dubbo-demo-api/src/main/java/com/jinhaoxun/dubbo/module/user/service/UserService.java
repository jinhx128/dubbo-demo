package com.jinhaoxun.dubbo.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhaoxun.dubbo.model.service.ServicePageableRequest;
import com.jinhaoxun.dubbo.module.user.model.response.AddSessionServiceRes;
import com.jinhaoxun.dubbo.module.user.model.response.GetUserListServiceRes;
import com.jinhaoxun.dubbo.module.user.model.response.GetUserServiceRes;
import com.jinhaoxun.dubbo.pojo.user.User;
import com.jinhaoxun.dubbo.module.user.model.request.*;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * @author jinhaoxun
     * @description 进行登录
     * @param userLoginServiceReq 登录信息参数
     * @return AddSessionResponse 登录操作结果
     * @throws Exception
     */
    AddSessionServiceRes addSession(UserLoginServiceReq userLoginServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 退出登录
     * @param userId 用户id
     * @return Boolean 是否退出成功
     */
    void deleteSession(Long userId) throws Exception;

    /**
     * @author jinhaoxun
     * @description 用户注册
     * @param userRegisterServiceReq 注册信息参数
     * @return
     * @throws Exception
     */
    void addUser(UserRegisterServiceReq userRegisterServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 用户注销
     * @param deleteUserServiceReq 用户id
     * @return
     * @throws Exception
     */
    void deleteUser(DeleteUserServiceReq deleteUserServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取验证码
     * @param getCodeServiceReq 获取验证码类型参数
     * @return
     * @throws Exception
     */
    void getCode(GetCodeServiceReq getCodeServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 验证码登录
     * @param codeUserLoginServiceReq 登录信息参数
     * @return AddSessionServiceRes
     * @throws Exception
     */
    AddSessionServiceRes addCodeSession(CodeUserLoginServiceReq codeUserLoginServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 修改密码
     * @param updatePasswordServiceReq 修改密码信息参数
     * @return
     * @throws Exception
     */
    void updatePassword(UpdatePasswordServiceReq updatePasswordServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 账号封禁
     * @param addBanServiceReq 用户id
     * @return
     * @throws Exception
     */
    void addBan(AddBanServiceReq addBanServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 账号解封
     * @param deleteBanServiceReq 用户id
     * @return
     * @throws Exception
     */
    void deleteBan(DeleteBanServiceReq deleteBanServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取用户信息
     * @param getUserServiceReq 用户id
     * @return GetUserServiceRes 获取到的用户信息
     * @throws Exception
     */
    GetUserServiceRes getUser(GetUserServiceReq getUserServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 更新用户信息
     * @param updateUserServiceReq 用户信息
     * @return
     * @throws Exception
     */
    void updateUser(UpdateUserServiceReq updateUserServiceReq) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @param servicePageableRequest 获取所有账号列表入参
     * @return GetUserListServiceRes 获取到的账号列表
     * @throws Exception
     */
    GetUserListServiceRes getUserList(ServicePageableRequest servicePageableRequest) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     * @throws Exception
     */
    String selectName(Long userId) throws Exception;

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    String selectPassword(Long userId) throws Exception;
}
