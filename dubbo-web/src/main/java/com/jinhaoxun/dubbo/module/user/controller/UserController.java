package com.jinhaoxun.dubbo.module.user.controller;

import com.jinhaoxun.dubbo.vo.action.ActionPageableRequest;
import com.jinhaoxun.dubbo.vo.action.ActionResponse;
import com.jinhaoxun.dubbo.vo.http.HttpRequest;
import com.jinhaoxun.dubbo.vo.http.HttpResponse;
import com.jinhaoxun.dubbo.module.user.vo.request.*;
import com.jinhaoxun.dubbo.module.user.vo.response.GetUserActionRes;
import com.jinhaoxun.dubbo.module.user.vo.response.GetUserListActionRes;
import com.jinhaoxun.dubbo.module.user.business.UserBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户前端控制器
 */
@RequestMapping("/user")
@RestController
@Api(value = "/user", tags = "用户接口")
public class UserController {

    @Resource
    private UserBusiness userBusiness;

    /**
     * @author jinhaoxun
     * @description 进行登录
     * @param userLoginActionReqHttpRequest 登录信息参数
     * @param httpServletResponse 请求响应体
     * @return ResponseResult 登录操作结果
     * @throws Exception
     */
    @PostMapping(value="/addsession", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "进行登录", notes = "进行登录接口描述")
    public HttpResponse<ActionResponse> addSession(@Validated @RequestBody HttpRequest<UserLoginActionReq> userLoginActionReqHttpRequest,
                                                   HttpServletResponse httpServletResponse) throws Exception {
        userBusiness.addSession(userLoginActionReqHttpRequest.getData(), httpServletResponse);
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 用户注册
     * @param userRegisterActionReqHttpRequest 注册信息参数
     * @return HttpResponse<ActionResponse> 是否注册成功
     * @throws Exception
     */
    @PostMapping(value="/adduser", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "用户注册", notes = "用户注册接口描述")
    public HttpResponse<ActionResponse> addUser(@Validated @RequestBody HttpRequest<UserRegisterActionReq> userRegisterActionReqHttpRequest) throws Exception {
        userBusiness.addUser(userRegisterActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 用户注销
     * @param deleteUserActionReqHttpRequest 用户id
     * @return HttpResponse<ActionResponse> 是否注销成功
     * @throws Exception
     */
    @PostMapping(value="/deleteuser", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "用户注销", notes = "用户注销接口描述")
    public HttpResponse<ActionResponse> deleteUser(@Validated @RequestBody HttpRequest<DeleteUserActionReq> deleteUserActionReqHttpRequest) throws Exception {
        userBusiness.deleteUser(deleteUserActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 获取验证码
     * @param getCodeActionReqHttpRequest 获取验证码类型参数
     * @return HttpResponse<ActionResponse> 成功提示信息
     * @throws Exception
     */
    @PostMapping(value="/getcode", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取验证码", notes = "获取验证码接口描述")
    public HttpResponse<ActionResponse> getCode(@Validated @RequestBody HttpRequest<GetCodeActionReq> getCodeActionReqHttpRequest) throws Exception {
        userBusiness.getCode(getCodeActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 验证码登录
     * @param codeUserLoginActionReqHttpRequest 登录信息参数
     * @param httpServletResponse 请求响应体
     * @return HttpResponse<ActionResponse> 登录操作结果
     * @throws Exception
     */
    @PostMapping(value="/addcodesession", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "验证码登录", notes = "验证码登录接口描述")
    public HttpResponse<ActionResponse> addCodeSession(@Validated @RequestBody HttpRequest<CodeUserLoginActionReq> codeUserLoginActionReqHttpRequest,
                                                       HttpServletResponse httpServletResponse) throws Exception {
        userBusiness.addCodeSession(codeUserLoginActionReqHttpRequest.getData(), httpServletResponse);
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 修改密码
     * @param updatePasswordActionReqHttpRequest 修改密码信息参数
     * @return HttpResponse<ActionResponse>
     * @throws Exception
     */
    @PostMapping(value="/updatepassword", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新密码", notes = "更新密码接口描述")
    public HttpResponse<ActionResponse> updatePassword(@Validated @RequestBody HttpRequest<UpdatePasswordActionReq> updatePasswordActionReqHttpRequest) throws Exception {
        userBusiness.updatePassword(updatePasswordActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 账号封禁
     * @param addBanActionReqHttpRequest 用户id
     * @return HttpResponse<ActionResponse> 是否封禁成功
     * @throws Exception
     */
    @PostMapping(value="/addban", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "账号封禁", notes = "账号封禁接口描述")
    public HttpResponse<ActionResponse> addBan(@Validated @RequestBody HttpRequest<AddBanActionReq> addBanActionReqHttpRequest) throws Exception {
        userBusiness.addBan(addBanActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 账号解封
     * @param deleteBanActionReqHttpRequest 用户id
     * @return HttpResponse<ActionResponse> 是否解封成功
     * @throws Exception
     */
    @PostMapping(value="/deleteban", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "账号解封", notes = "账号解封接口描述")
    public HttpResponse<ActionResponse> deleteBan(@Validated @RequestBody HttpRequest<DeleteBanActionReq> deleteBanActionReqHttpRequest) throws Exception {
        userBusiness.deleteBan(deleteBanActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 获取用户信息
     * @param getUserActionReqHttpRequest 用户id
     * @return GetUserActionRes 获取到的用户信息
     * @throws Exception
     */
    @PostMapping(value="/getuser", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口描述")
    public HttpResponse<GetUserActionRes> getUser(@Validated @RequestBody HttpRequest<GetUserActionReq> getUserActionReqHttpRequest) throws Exception {
        GetUserActionRes getUserActionRes = userBusiness.getUser(getUserActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess(getUserActionRes);
    }

    /**
     * @author jinhaoxun
     * @description 更新用户信息
     * @param updateUserActionReqHttpRequest 用户信息
     * @return HttpResponse<ActionResponse> 是否更新成功
     * @throws Exception
     */
    @PostMapping(value="/updateuser", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息接口描述")
    public HttpResponse<ActionResponse> updateUser(@Validated @RequestBody HttpRequest<UpdateUserActionReq> updateUserActionReqHttpRequest) throws Exception {
        userBusiness.updateUser(updateUserActionReqHttpRequest.getData());
        return HttpResponse.buildSuccess();
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @param actionRequestHttpRequest 统一请求实体
     * @return GetUserListActionRes 获取到的账号列表
     * @throws Exception
     */
    @PostMapping(value="/getuserlist", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取所有账号列表", notes = "获取所有账号列表接口描述")
    public HttpResponse<GetUserListActionRes> getUserList(@Validated @RequestBody HttpRequest<ActionPageableRequest> actionRequestHttpRequest) throws Exception {
        GetUserListActionRes getUserListActionRes = userBusiness.getUserList(actionRequestHttpRequest.getData());
        return HttpResponse.buildSuccess(getUserListActionRes);
    }
}

