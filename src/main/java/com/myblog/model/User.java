package com.myblog.model;

import java.io.Serializable;

/**
 * @author Zephery
 * @since 2018/1/30 18:49
 */
public class User implements Serializable {
    public int user_id;//用户在自己网站的id
    public String sign;//签名
    private String nickname;//用户昵称
    private String img_url;//用户头像地址
    private String profile_url;//用户主页地址

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "user_id:" + String.valueOf(user_id) + "nickname:" + nickname;
    }
}