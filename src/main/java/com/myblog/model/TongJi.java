package com.myblog.model;

import java.io.Serializable;

/**
 * Created by Zephery on 2017/6/27.
 */
public class TongJi implements Serializable {
    //"pv_count,visitor_count,ip_count,bounce_ratio,avg_visit_time", 10879516, "20170625", "20170627")

    private String pv_count;
    private String visitor_count;
    private String ip_count;
    private String bounce_ratio;
    private String avg_visit_time;
    private String date;

    public String getPv_count() {
        return pv_count;
    }

    public void setPv_count(String pv_count) {
        this.pv_count = pv_count;
    }

    public String getVisitor_count() {
        return visitor_count;
    }

    public void setVisitor_count(String visitor_count) {
        this.visitor_count = visitor_count;
    }

    public String getIp_count() {
        return ip_count;
    }

    public void setIp_count(String ip_count) {
        this.ip_count = ip_count;
    }

    public String getBounce_ratio() {
        return bounce_ratio;
    }

    public void setBounce_ratio(String bounce_ratio) {
        this.bounce_ratio = bounce_ratio;
    }

    public String getAvg_visit_time() {
        return avg_visit_time;
    }

    public void setAvg_visit_time(String avg_visit_time) {
        this.avg_visit_time = avg_visit_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
