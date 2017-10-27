package com.myblog.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer id;

    private String adminname;

    private String adminpasswd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpasswd() {
        return adminpasswd;
    }

    public void setAdminpasswd(String adminpasswd) {
        this.adminpasswd = adminpasswd;
    }
}