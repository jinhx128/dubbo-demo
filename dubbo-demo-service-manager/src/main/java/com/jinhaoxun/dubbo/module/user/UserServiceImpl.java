package com.jinhaoxun.dubbo.module.user;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.model.service.ServicePageableRequest;
import com.jinhaoxun.dubbo.module.user.model.response.AddSessionServiceRes;
import com.jinhaoxun.dubbo.module.user.model.response.GetUserListServiceRes;
import com.jinhaoxun.dubbo.module.user.model.response.GetUserServiceRes;
import com.jinhaoxun.dubbo.pojo.user.User;
import com.jinhaoxun.dubbo.module.user.model.request.*;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.thirdparty.notify.model.request.GetEmailCodeServiceReq;
import com.jinhaoxun.dubbo.thirdparty.notify.model.request.GetPhoneCodeServiceReq;
import com.jinhaoxun.dubbo.thirdparty.notify.model.response.GetEmailCodeServiceRes;
import com.jinhaoxun.dubbo.thirdparty.notify.model.response.GetPhoneCodeServiceRes;
import com.jinhaoxun.dubbo.util.encodeutil.BcryptUtil;
import com.jinhaoxun.dubbo.util.idutil.IdUtil;
import com.jinhaoxun.dubbo.mapper.user.UserMapper;
import com.jinhaoxun.dubbo.thirdparty.notify.service.NotifyService;
import com.jinhaoxun.dubbo.module.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户服务类
 */
@Service
@Slf4j
@Component
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Reference
    private NotifyService notifyService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ExceptionFactory exceptionFactory;

    /**
     * @author jinhaoxun
     * @description 进行登录
     * @param userLoginServiceReq 登录信息参数
     * @return AddSessionResponse 登录操作结果
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public AddSessionServiceRes addSession(UserLoginServiceReq userLoginServiceReq) throws Exception {
        Long userId;
        if(userLoginServiceReq.getType().equals(AbstractConstant.USER_REGISTER_TYPE_PHONE)){
            String phone = userLoginServiceReq.getPhone();
            userId = userMapper.selectIdByPhone(phone);
        }else{
            String email = userLoginServiceReq.getEmail();
            userId = userMapper.selectIdByEmail(email);
        }
        String password = userLoginServiceReq.getPassword();
/*        Assert.notNull(userName, "用户名不能为空！");
        Assert.notNull(password, "密码不能为空！");*/
        if(userId == null){
            throw exceptionFactory.build(ResponseMsg.USER_NOT_EXIST.getCode(),"账号或者密码错误");
        }
        String realPassword = userMapper.selectPassword(userId);
        if (!BcryptUtil.checkPasswordByBcrypt(password, realPassword)) {
            throw exceptionFactory.build(ResponseMsg.PASSWORD_WRONG.getCode(),"账号或者密码错误");
        }
        Boolean ban = userMapper.selectBan(userId);
        Boolean status = userMapper.selectStatus(userId);
        //账号是否被封
        if (ban) {
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_IS_BLOCKED.getCode(),ResponseMsg.ACCOUNT_IS_BLOCKED.getMsg());
        }
        //账号是否注销
        if (!status) {
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_IS_CANCELLED.getCode(),ResponseMsg.ACCOUNT_IS_CANCELLED.getMsg());
        }
        //验证成功后处理
        //this.loginSuccess(userId, realPassword, response);
        AddSessionServiceRes addSessionResponse = new AddSessionServiceRes();
        addSessionResponse.setUserId(userId);
        addSessionResponse.setRealPassword(realPassword);
        //获取用户推送消息
/*        ResponseResult<List<Message>> responseResult = iMessageService.getSystemMessageByUserId(userId);
        if(responseResult.getData().size() > 0 ){
            ListIterator listIterator = responseResult.getData().listIterator();
            while(listIterator.hasNext()){
                iMessageService.sendSystemMessage((SystemMessage)listIterator.next());
            }
        }*/
        return addSessionResponse;
    }

    /**
     * @author jinhaoxun
     * @description 退出登录
     * @param userId 用户id
     * @return Boolean 是否退出成功
     */
    @HystrixCommand
    @Override
    public void deleteSession(Long userId) {
        // 清除可能存在的Shiro权限信息缓存
        String tokenKey= AbstractConstant.SHIRO_ROLE_PERMISSION_KEY + userId;
        if (redisTemplate.hasKey(tokenKey)) {
            redisTemplate.delete(tokenKey);
        }
        log.info(String.format("用户 {} 退出成功", userId));
    }

    /**
     * @author jinhaoxun
     * @description 用户注册
     * @param userRegisterServiceReq 注册信息参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void addUser(UserRegisterServiceReq userRegisterServiceReq) throws Exception {
        User user = new User();
        if(StringUtils.equals(AbstractConstant.USER_REGISTER_TYPE_PHONE,userRegisterServiceReq.getType())){
            user.setPhone(userRegisterServiceReq.getPhone());
        }else{
            user.setEmail(userRegisterServiceReq.getEmail());
        }
        Long userId = IdUtil.getId();
        user.setUserId(userId);
        String password = BcryptUtil.encoderByBcrypt(userRegisterServiceReq.getPassword());
        user.setPassword(password);
        user.setName(userRegisterServiceReq.getName());
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdaterId(userId);
        user.setUpdateTime(now);
        user.setBan(false);
        user.setStatus(true);
        user.setArticleComment(0);
        user.setArticlePraise(0);
        user.setArticle(0);
        int count = userMapper.insert(user);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_REGISTRATION_FAIL.getCode(),ResponseMsg.ACCOUNT_REGISTRATION_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 用户注销
     * @param deleteUserServiceReq 用户id
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void deleteUser(DeleteUserServiceReq deleteUserServiceReq) throws Exception {
        int count = userMapper.updateStatus(deleteUserServiceReq.getUserId());
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_CANAEL_FAIL.getCode(),ResponseMsg.ACCOUNT_CANAEL_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 获取验证码
     * @param getCodeServiceReq 获取验证码类型参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void getCode(GetCodeServiceReq getCodeServiceReq) throws Exception {
        switch (getCodeServiceReq.getType()){
            case AbstractConstant.USER_REGISTER_TYPE_PHONE:
                String userPhoneLogInCodeKey = AbstractConstant.USER_PHONE_LOG_IN_CODE + getCodeServiceReq.getPhone();
                if (redisTemplate.hasKey(userPhoneLogInCodeKey)) {
                    exceptionFactory.build(ResponseMsg.REPEAT_GET_USER_LOG_IN_CODE.getCode(), ResponseMsg.REPEAT_GET_USER_LOG_IN_CODE.getMsg());
                }

                GetPhoneCodeServiceReq getPhoneCodeServiceReq = new GetPhoneCodeServiceReq();
                getPhoneCodeServiceReq.setPhone(getCodeServiceReq.getPhone());

                GetPhoneCodeServiceRes getPhoneCodeServiceRes = notifyService.getPhoneCode(getPhoneCodeServiceReq);
                redisTemplate.opsForValue().set(userPhoneLogInCodeKey, getPhoneCodeServiceRes.getCode(),
                        AbstractConstant.USER_LOG_IN_CODE_EXPIRATION_TIME, TimeUnit.SECONDS);
                break;
            case AbstractConstant.USER_REGISTER_TYPE_EMAIL:
                String userEmailLogInCodeKey = AbstractConstant.USER_EMAIL_LOG_IN_CODE + getCodeServiceReq.getEmail();
                if (redisTemplate.hasKey(userEmailLogInCodeKey)) {
                    exceptionFactory.build(ResponseMsg.REPEAT_GET_USER_LOG_IN_CODE.getCode(), ResponseMsg.REPEAT_GET_USER_LOG_IN_CODE.getMsg());
                }

                GetEmailCodeServiceReq getEmailCodeServiceReq = new GetEmailCodeServiceReq();
                getEmailCodeServiceReq.setEmail(getCodeServiceReq.getEmail());

                GetEmailCodeServiceRes getEmailCodeServiceRes = notifyService.getEmailCode(getEmailCodeServiceReq);
                redisTemplate.opsForValue().set(userEmailLogInCodeKey, getEmailCodeServiceRes.getCode(),
                        AbstractConstant.USER_LOG_IN_CODE_EXPIRATION_TIME, TimeUnit.SECONDS);
                break;
            default:break;
        }
    }

    /**
     * @author jinhaoxun
     * @description 验证码登录
     * @param codeUserLoginServiceReq 登录信息参数
     * @return AddSessionServiceRes
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public AddSessionServiceRes addCodeSession(CodeUserLoginServiceReq codeUserLoginServiceReq) throws Exception {

        String userLogInCodeKey = null;
        Long userId = null;
        switch (codeUserLoginServiceReq.getType()){
            case AbstractConstant.USER_REGISTER_TYPE_PHONE:
                userLogInCodeKey = AbstractConstant.USER_PHONE_LOG_IN_CODE + codeUserLoginServiceReq.getPhone();
                userId = userMapper.selectIdByPhone(codeUserLoginServiceReq.getPhone());
                break;
            case AbstractConstant.USER_REGISTER_TYPE_EMAIL:
                userLogInCodeKey = AbstractConstant.USER_EMAIL_LOG_IN_CODE + codeUserLoginServiceReq.getEmail();
                userId = userMapper.selectIdByEmail(codeUserLoginServiceReq.getEmail());
                break;
            default:break;
        }

        if (!redisTemplate.hasKey(userLogInCodeKey)) {
            exceptionFactory.build(ResponseMsg.USER_LOG_IN_CODE_EXPIRATIONED.getCode(),ResponseMsg.USER_LOG_IN_CODE_EXPIRATIONED.getMsg());
        }
        String code = redisTemplate.opsForValue().get(userLogInCodeKey).toString();
        if(!StringUtils.equals(code, codeUserLoginServiceReq.getCode())){
            exceptionFactory.build(ResponseMsg.USER_LOG_IN_CODE_WRONG.getCode(),ResponseMsg.USER_LOG_IN_CODE_WRONG.getMsg());
        }

        Boolean ban = userMapper.selectBan(userId);
        Boolean status = userMapper.selectStatus(userId);
        //账号是否被封
        if (ban) {
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_IS_BLOCKED.getCode(),ResponseMsg.ACCOUNT_IS_BLOCKED.getMsg());
        }
        //账号是否注销
        if (!status) {
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_IS_CANCELLED.getCode(),ResponseMsg.ACCOUNT_IS_CANCELLED.getMsg());
        }
        String password = userMapper.selectPassword(userId);
        //验证成功后处理
        //this.loginSuccess(userId, password, response);
        AddSessionServiceRes addSessionResponse = new AddSessionServiceRes();
        addSessionResponse.setUserId(userId);
        addSessionResponse.setRealPassword(password);
        redisTemplate.delete(userLogInCodeKey);
        //获取用户推送消息
/*        ResponseResult<List<Message>> responseResult = iMessageService.getSystemMessageByUserId(userId);
        if(responseResult.getData().size() > 0 ){
            return ResponseFactory.buildSuccessResponse(responseResult.getData(),"登录成功!");
        }*/
        return addSessionResponse;
    }

    /**
     * @author jinhaoxun
     * @description 修改密码
     * @param updatePasswordServiceReq 修改密码信息参数
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void updatePassword(UpdatePasswordServiceReq updatePasswordServiceReq) throws Exception {
        String password = BcryptUtil.encoderByBcrypt(updatePasswordServiceReq.getPassword());
        int count = userMapper.updatePassword(updatePasswordServiceReq.getUserId(),password);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.PASSWORD_CHANGE_FAIL.getCode(),ResponseMsg.PASSWORD_CHANGE_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 账号封禁
     * @param addBanServiceReq 用户id
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void addBan(AddBanServiceReq addBanServiceReq) throws Exception {
        int count = userMapper.updateBan(addBanServiceReq.getUserId(),true);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_BLOCK_FAIL.getCode(),ResponseMsg.ACCOUNT_BLOCK_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 账号解封
     * @param deleteBanServiceReq 用户id
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void deleteBan(DeleteBanServiceReq deleteBanServiceReq) throws Exception {
        int count = userMapper.updateBan(deleteBanServiceReq.getUserId(),false);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.ACCOUNT_UNSEALING_FAIL.getCode(),ResponseMsg.ACCOUNT_UNSEALING_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 获取用户信息
     * @param getUserServiceReq 用户id
     * @return GetUserServiceRes 获取到的用户信息
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public GetUserServiceRes getUser(GetUserServiceReq getUserServiceReq) throws Exception {
        User user = userMapper.selectById(getUserServiceReq.getUserId());
        if(user == null){
            throw exceptionFactory.build(ResponseMsg.GET_USERINFO_FAIL.getCode(),ResponseMsg.GET_USERINFO_FAIL.getMsg());
        }
        GetUserServiceRes getUserServiceRes = new GetUserServiceRes();
        BeanUtils.copyProperties(user, getUserServiceRes);
        return getUserServiceRes;
    }

    /**
     * @author jinhaoxun
     * @description 更新用户信息
     * @param updateUserServiceReq 用户信息
     * @return
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public void updateUser(UpdateUserServiceReq updateUserServiceReq) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(updateUserServiceReq, user);
        int count = userMapper.updateById(user);
        if(count != 1){
            throw exceptionFactory.build(ResponseMsg.UPDATE_USERINFO_FAIL.getCode(),ResponseMsg.UPDATE_USERINFO_FAIL.getMsg());
        }
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @param servicePageableRequest 获取所有账号列表入参
     * @return getUserListServiceRes 获取到的账号列表
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public GetUserListServiceRes getUserList(ServicePageableRequest servicePageableRequest) throws Exception{
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.orderByDesc(AbstractConstant.USER_CREATE_TIME);
        Page<User> page = new Page<>(servicePageableRequest.getPage(), servicePageableRequest.getSize());
        List<User> userList = userMapper.selectPage(page,qw).getRecords();
        int totals = userMapper.selectCount(qw);
        GetUserListServiceRes getUserListServiceRes = new GetUserListServiceRes();
        getUserListServiceRes.setArticleList(userList);
        getUserListServiceRes.setTotals(totals);
        return getUserListServiceRes;
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    @HystrixCommand
    @Override
    public String selectName(Long userId) throws Exception {
        return userMapper.selectName(userId);
    }

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    @HystrixCommand
    @Override
    public String selectPassword(Long userId) throws Exception {
        return userMapper.selectPassword(userId);
    }

}
