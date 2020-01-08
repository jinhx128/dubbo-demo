package com.jinhaoxun.dubbo.module.shiro;

import com.jinhaoxun.dubbo.constant.AbstractConstant;
import com.jinhaoxun.dubbo.exception.ExceptionFactory;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.module.shiro.service.SyncCacheService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description Redis锁服务类
 */
@Slf4j
@Service
@Component
public class SyncCacheServiceImpl implements SyncCacheService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ExceptionFactory exceptionFactory;

    /**
     * @author jinhaoxun
     * @description 获取redis中key的锁，乐观锁实现
     * @param lockName 获取锁的名字
     * @param expireTime 锁的失效时间
     * @return Boolean 是否获取成功
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public boolean getLock(String lockName, int expireTime) throws Exception {
        boolean result = false;
        try {
            boolean isExist = redisTemplate.hasKey(lockName);
            if(!isExist){
                redisTemplate.opsForValue().increment(lockName,0);
                redisTemplate.expire(lockName,expireTime<=0? AbstractConstant.REFRESH_TOKEN_CHECK_EXPIRATION_TIME:expireTime, TimeUnit.SECONDS);
            }
            long reVal =  redisTemplate.opsForValue().increment(lockName,1);
            if(1L ==reVal){
                //获取锁
                result = true;
                log.info("获取redis锁:"+lockName+",成功");
            }else {
                log.info("获取redis锁:"+lockName+",失败"+reVal);
            }
        } catch (Exception e) {
            throw exceptionFactory.build(ResponseMsg.REDIS_GET_LOCK_FAIL.getCode(),"获取redis锁失败:"+lockName+e.getMessage());
        }
        return result;
    }

    /**
     * @author jinhaoxun
     * @description 释放锁，直接删除key(直接删除会导致任务重复执行，所以释放锁机制设为超时20s)
     * @param lockName 释放锁的名字
     * @return Boolean 是否释放成功
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public boolean releaseLock(String lockName) throws Exception {
        try {
            redisTemplate.expire(lockName, AbstractConstant.RELEASE_LOCK_TIME, TimeUnit.SECONDS);
            log.info("释放redis锁:"+lockName+",成功");
        } catch (Exception e) {
            throw exceptionFactory.build(ResponseMsg.REDIS_RELEASE_LOCK_FAIL.getCode(),"释放redis锁失败:"+lockName+e.getMessage());
        }
        return false;
    }

    /**
     * @author jinhaoxun
     * @description 释放锁，直接删除key(直接删除会导致任务重复执行，所以释放锁机制设为超时20s)
     * @param lockName 释放锁的名字
     * @param releaseLockTime (单位：秒)
     * @return Boolean 是否释放成功
     * @throws Exception
     */
    @HystrixCommand
    @Override
    public boolean releaseLock(String lockName, int releaseLockTime) throws Exception {
        try {
            redisTemplate.expire(lockName, AbstractConstant.RELEASE_LOCK_TIME, TimeUnit.SECONDS);
            log.info("释放redis锁:"+lockName+",成功");
        } catch (Exception e) {
            throw exceptionFactory.build(ResponseMsg.REDIS_RELEASE_LOCK_FAIL.getCode(),"释放redis锁失败:"+lockName+e.getMessage());
        }
        return false;
    }
}
