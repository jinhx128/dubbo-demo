package com.jinhaoxun.dubbo.common.aop;

import com.alibaba.fastjson.JSON;
import com.jinhaoxun.dubbo.common.login.UserServerLogin;
import com.jinhaoxun.dubbo.constant.LoginConstant;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.exception.CustomException;
import com.jinhaoxun.dubbo.exception.CustomRuntimeException;
import com.jinhaoxun.dubbo.model.action.ActionRequest;
import com.jinhaoxun.dubbo.model.action.ActionResponse;
import com.jinhaoxun.dubbo.model.http.HttpRequest;
import com.jinhaoxun.dubbo.model.http.HttpResponse;
import com.jinhaoxun.dubbo.util.datautil.BinaryUtil;
import com.jinhaoxun.dubbo.util.datautil.StringUtil;
import com.jinhaoxun.dubbo.util.encodeutil.EncodeUtil;
import com.jinhaoxun.dubbo.util.requestutil.HttpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Auther: jinhaoxun
 * @Date: 2019/1/22 16:28
 * @Description:
 */
@Aspect
public class HttpCheckAspact {

    /**
     * 输出到系统日志
     */
    private Logger sysLog = LoggerFactory.getLogger("sysLog");

    /**
     * redis
     */
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户操作对象
     */
    private UserServerLogin userServerLogin;

    public HttpCheckAspact(RedisTemplate<String, Object> redisTemplate, UserServerLogin userServerLogin) {
        this.redisTemplate = redisTemplate;
        this.userServerLogin = userServerLogin;
    }

    @Pointcut("@annotation(com.jinhaoxun.dubbo.common.aop.HttpCheck)")
    public void pointcut() {
    }

    /**
     *  前置处理
     * @param joinPoint
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HttpCheck annotation = method.getAnnotation(HttpCheck.class);
        // 获取request对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获取HttpRequest对象
        Object[] args = joinPoint.getArgs();
        HttpRequest httpRequest = null;
        if(!ArrayUtils.isEmpty(args)){
            for(Object arg: args){
                if(arg instanceof HttpRequest){
                    httpRequest = (HttpRequest)arg;
                }
            }
        }
        // 是否需要验证登陆
        boolean login = annotation.login();
        // 是否需要进行解密
        boolean isDecrypt = annotation.isDecrypt();
        // 解密的key
        String dectyptKey = annotation.decryptKey();
        Class<? extends ActionRequest> dataType = annotation.dataType();
        // 获取需要解密的key
        String key = StringUtil.isEmpty(dectyptKey) ? LoginConstant.REQUEST_KEY: dectyptKey;

        if(login){
            String cookie = HttpUtil.getLoginCookie(request);
            boolean isLogin = userServerLogin.isLogin(cookie);
            if(StringUtil.isEmpty(cookie) || isLogin == false){
                sysLog.error("AOP-用户未登陆" + method.getName() + "-" +cookie );
                throw new CustomException(ResponseMsg.USER_NOT_LOG_IN.getCode(), "AOP-用户未登陆"+method.getName()+ "-" +cookie, ResponseMsg.IDENTITY_INFORMATION_IS_EXPIRED.getMsg());
            }
        }
        if(isDecrypt){
            decryption(httpRequest, key, dataType);
        }

    }

    @AfterReturning(value = "pointcut()", returning = "response")
    public void doAfterReturning(JoinPoint joinPoint, Object response) throws Exception {
        if(response instanceof HttpResponse){
            HttpResponse httpResponse = (HttpResponse) response;
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            HttpCheck annotation = method.getAnnotation(HttpCheck.class);
            String encrypyKey = annotation.encryptKey();
            String key = StringUtil.isEmpty(encrypyKey)? LoginConstant.RESPONSE_KEY : encrypyKey;
            boolean isEncrypt = annotation.isEncrypt();
            if(isEncrypt){
                ActionResponse body =  httpResponse.getData();
                if (body != null) {
                      httpResponse.setSrs(EncodeUtil.encryptByAES(body.toString(), key));
                      httpResponse.setData(null);
                }
            }
        }
    }

    /**
     * 解密
     * @param httpRequest
     * @param key
     * @param dataType
     * @throws Exception
     */
    private void decryption(HttpRequest httpRequest, String key, Class<? extends ActionRequest> dataType) throws  Exception{
        String sdt = httpRequest.getSdt();
        if(StringUtil.isEmpty(sdt)){
            return;
        }
        String context = EncodeUtil.decryptByAES(sdt, key);
        if(StringUtil.isEmpty(context)){
            return;
        }
        httpRequest.setData(JSON.parseObject(context, dataType));
    }



}
