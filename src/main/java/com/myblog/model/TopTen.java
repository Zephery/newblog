package com.myblog.model;

import java.io.Serializable;

/**
 * Created by Zephery on 2017/6/28.
 */
public class TopTen implements Serializable {
    private String name;
    private Integer average_stay_time;
    private Integer visitor_count;
    private Integer pv_count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAverage_stay_time() {
        return average_stay_time;
    }

    public void setAverage_stay_time(Integer average_stay_time) {
        this.average_stay_time = average_stay_time;
    }

    public Integer getVisitor_count() {
        return visitor_count;
    }

    public void setVisitor_count(Integer visitor_count) {
        this.visitor_count = visitor_count;
    }

    public Integer getPv_count() {
        return pv_count;
    }

    public void setPv_count(Integer pv_count) {
        this.pv_count = pv_count;
    }
}
