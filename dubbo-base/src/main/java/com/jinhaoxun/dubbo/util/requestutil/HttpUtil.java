package com.jinhaoxun.dubbo.util.requestutil;

import com.jinhaoxun.dubbo.constant.LoginConstant;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: jinhaoxun
 * @Date: 2019/1/22 17:37
 * @Description:
 */
public class HttpUtil {
    /**
     * 获取登陆的cookie
     * @param request
     * @return
     */
    public static String getLoginCookie(HttpServletRequest request){
        return  getCookieByCookieName(request, LoginConstant.COOKIE_NAME);
    }

    /**
     * 获取请求头信息
     * @param request
     * @param headerName
     * @return
     */
    public static String getHeaderByHeaderName(HttpServletRequest request, String headerName){
        String headerValue = request.getHeader(headerName);
        return headerValue;
    }

    /**
     * 获取cookie
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieByCookieName(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(ArrayUtils.isEmpty(cookies)){
            return null;
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
