package com.jinhaoxun.dubbo.util;

import org.apache.commons.codec.binary.Hex;
import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 加密工具类
 */
public class EncodeUtil {

    /**
     * @author jinhaoxun
     * @description 进行加密方法
     * @param str 传入未加密字段
     * @return String
     */
    public static String encoderByBcrypt(String str){
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    /**
     * @author jinhaoxun
     * @description 判断用户密码是否正确方法
     * @param str 用户输入的密码
     * @param oldpassword 正确密码
     * @return Boolean
     */
    public static boolean checkByBcrypt(String str, String oldpassword){
        return BCrypt.checkpw(str, oldpassword);
    }

    /**
     * @author jinhaoxun
     * @description 进行加密方法
     * @param str 传入未加密字段
     * @return String
     */
    public static String encoderByMD5(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] bytes = md.digest(str.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * @author jinhaoxun
     * @description 判断用户密码是否正确方法
     * @param str 用户输入的密码
     * @param oldpassword 正确密码
     * @return Boolean
     */
    public static boolean checkByMD5(String str, String oldpassword) throws Exception {
        return oldpassword.equals(encoderByMD5(str));
    }

    /**
     * @author jinhaoxun
     * @description 进行加密方法
     * @param str 传入未加密字段
     * @return String
     */
    public static String encoderBySHA256(String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
        return Hex.encodeHexString(hash);
    }

    /**
     * @author jinhaoxun
     * @description 判断用户密码是否正确方法
     * @param password 用户输入的密码
     * @param oldpassword 正确密码
     * @return Boolean
     */
    public static boolean checkBySHA256(String password, String oldpassword) throws Exception {
        return oldpassword.equals(encoderByMd5(password));
    }
}
