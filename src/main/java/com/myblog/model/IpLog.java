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

    private String area;

    private String uri;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}