package com.myblog.model;

import java.io.Serializable;

/**
 * Created by Zephery on 2017/6/21.
 */
public class KeyAndValue implements Serializable {
    private String key;
    private String value;

    public KeyAndValue() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
