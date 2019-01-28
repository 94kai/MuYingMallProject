package com.lq.muyingmall.domain;

/**
 * @author xuekai1
 * @date 2019/1/28
 */
public class LoginState {
    public String token;

    public LoginState() {
    }

    public LoginState(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
