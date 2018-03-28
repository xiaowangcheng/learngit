package com.rainbow.laundry.modle;

/**
 * Created by wyc on 2018/1/2.
 */

public class Login   extends  Base{

    private String id;
    private String imgurl;
    private int balance;
    private String phoneNumber;
    private String token;
    private String loginName;
    private String integral;
    private String registrationId;
    private String addTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public int getBalance() {
        return balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getToken() {
        return token;
    }
}
