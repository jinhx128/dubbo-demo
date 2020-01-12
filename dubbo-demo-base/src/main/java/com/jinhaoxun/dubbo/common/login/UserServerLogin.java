package com.jinhaoxun.dubbo.common.login;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jinhaoxun.dubbo.util.datautil.StringUtil;
import com.unicom.smartterminal.util.StringUtil;
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
     * @param user
     * @throws Exception
     */
    public void setUserToRedis(String cookie, UserServer user) throws Exception {
        if (cookie == null || StringUtils.equals("",cookie)) {
            throw new Exception("cookie为空");
        }
        String userKey = keepUserKey(cookie);
        // 设置缓存对象
        redisTemplate.opsForValue().set(userKey, user.toString(), LOGIN_TIMEOUT, TimeUnit.DAYS);
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
        String userKey = keepUserKey(cookie);
        String userCache = redisTemplate.opsForValue().get(userKey);
        if(StringUtil.isEmpty(userCache)){
            return null;
        }
        return  JSON.parseObject(userCache, UserServer.class);
    }




}
