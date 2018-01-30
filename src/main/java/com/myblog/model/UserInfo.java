package com.myblog.model;

import java.io.Serializable;

/**
 * @author Zephery
 * @since 2017/11/30 13:28
 */
public class UserInfo implements Serializable {
    public int is_login;//是否登录，0表示未登录，1表示已登录

    public User user; //用户信息

    public int getIs_login() {
        return is_login;
    }

    public void setIs_login(int is_login) {
        this.is_login = is_login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}