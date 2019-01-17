package com.lq.muyingmall.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String nickName;
    @Column(nullable = false)
    private String passWord;
    /**
     * 用户类型 1.普通用户 2.商家 3.超级管理员。默认为1
     */
    @Column(nullable = false)
    private int type = 1;

    public User() {
    }

    public User(String userName, String nickName, String passWord) {
        this.userName = userName;
        this.nickName = nickName;
        this.passWord = passWord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", type=" + type +
                '}';
    }
}
