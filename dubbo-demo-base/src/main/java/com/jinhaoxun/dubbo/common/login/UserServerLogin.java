package com.jinhaoxun.dubbo.common.login;

import com.alibaba.druid.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 用户信息存储的工具类型
 * @Auther: jinhaoxun
 * @Date: 2019/1/22 16:43
 * @Description:
 */
public class UserServerLogin {

    public UserServerLogin(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 登陆临时会话2天有效
     */
    private static final long LOGIN_TIMEOUT = 2;
    /**
     * 存对象的前缀
     */
    private static final String LOGIN_USER = "smart:login:user:";
    /**
     * 组装key
     **/
    private String keepUserKey(String cookie){
        return  LOGIN_USER + cookie;
    }

    /**
     *用户是否登陆
     * @param cookie
     * @return
     */
    public boolean isLogin(String cookie){
        UserServer user =  getUserByCookie(cookie);
        return  user != null;
    }

    /**
     *  设置用户信息
     * @param cookie
     * @param userServer
     * @throws Exception
     */
    public void setUserToRedis(String cookie, UserServer userServer) throws Exception {
        if (cookie == null || StringUtils.equals("", cookie)) {
            throw new Exception("cookie为空");
        }
//        String userKey = keepUserKey(cookie);
        // 设置缓存对象
        redisTemplate.opsForHash().put(LOGIN_USER, userServer.getUserId(), userServer);
        redisTemplate.expire(LOGIN_USER + userServer.getUserId(), LOGIN_TIMEOUT, TimeUnit.DAYS);
    }

    /**
     * 通过cookie获取用户信息
     * @param cookie
     * @return
     */
    public UserServer getUserByCookie(String cookie){
        if(cookie == null || StringUtils.equals("",cookie)){
            return null;
        }
//        String userKey = keepUserKey(cookie);
        UserServer userServer = (UserServer) redisTemplate.opsForHash().get(LOGIN_USER, cookie);
        if(userServer == null){
            return null;
        }
        return userServer;
    }




}
