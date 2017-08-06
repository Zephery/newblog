package com.myblog.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class IpLog implements Serializable {
    private Integer id;

    private String ip;

    private Date ipTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getIpTime() {
        return ipTime;
    }

    public void setIpTime(Date ipTime) {
        this.ipTime = ipTime;
    }
}