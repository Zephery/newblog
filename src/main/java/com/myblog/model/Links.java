package com.myblog.model;

import java.io.Serializable;

/**
 * @author
 */
public class Links implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer lid;
    private String name;
    private String url;
    private String logo;
    private Integer sort;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}