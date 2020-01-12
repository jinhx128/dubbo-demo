package com.jinhaoxun.dubbo.common.login;

import com.jinhaoxun.dubbo.model.base.Model;

/**
 * 服务端缓存的用户信息对象
 * @Auther: jinhaoxun
 * @Date: 2019/1/22 16:36
 * @Description:
 */
public class UserServer extends Model {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 微信OPENID
     */
    private String openId;
    /**
     * 用户名称（身份证）
     */
    private String name;
    /**
     * 昵称（用户表）
     */
    private String nickName;
    /**
     * 用户的电话号码（用户表）
     */
    private String phone;
    /**
     * 用户电话
     */
    private String mobile;
    /**
     * 身份证号
     */
    private String IDNo;
    /**
     * 所选号码
     */
    private String choiceMobile;
    /**
     * 生效方式（当月生效，次月生效，半月生效）
     */
    private String effectType;
    /**
     * 地址编号
     */
    private String addressId;
    /**
     * spu编号
     */
    private String spuId;
    /**
     * sku编号
     */
    private String skuId;
    /**
     * 发展人编号
     */
    private String developPersonId;
    /**
     * 省编码
     */
    private String provinceId;
    /**
     * 市编码
     */
    private String cityId;
    /**
     * 是否校验登陆
     */
    private boolean secondLogin = false;
    /* 老用户手机号 */
    private String usedMobile;
    
    private String check;

    private boolean userType=false;//是否新用户


    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public String getCheck() {
        return check;
    }
    
    public void setCheck(String check) {
        this.check = check;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIDNo() {
        return IDNo;
    }

    public void setIDNo(String IDNo) {
        this.IDNo = IDNo;
    }

    public String getChoiceMobile() {
        return choiceMobile;
    }

    public void setChoiceMobile(String choiceMobile) {
        this.choiceMobile = choiceMobile;
    }

    public String getEffectType() {
        return effectType;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getDevelopPersonId() {
        return developPersonId;
    }

    public void setDevelopPersonId(String developPersonId) {
        this.developPersonId = developPersonId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public boolean isSecondLogin() {
        return secondLogin;
    }

    public void setSecondLogin(boolean secondLogin) {
        this.secondLogin = secondLogin;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getUsedMobile() {
        return usedMobile;
    }
    
    public void setUsedMobile(String usedMobile) {
        this.usedMobile = usedMobile;
    }
}
