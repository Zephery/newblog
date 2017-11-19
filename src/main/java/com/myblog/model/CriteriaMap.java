package com.myblog.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Zephery
 * @since 2017/11/19 18:50
 */
public class CriteriaMap {
    private Map<String, Data> map = new HashMap<>();

    public void put(String key, Object value) {
        map.put(key, new Data(value));
    }

    public Data get(String key) {
        return map.get(key);
    }

    public void put(String key, Object value, Type type) {
        map.put(key, new Data(value, type));
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public enum Type {
        is, regex, gt, gte, lt, lte
    }

    public class Data {

        private Type type;
        private Object obj;

        Data(Object obj) {
            this.obj = obj;
            this.type = Type.is;
        }

        Data(Object obj, Type type) {
            this.obj = obj;
            this.type = type;
        }


        public Object getObj() {
            return obj;
        }

        public Type getType() {
            return type;
        }


    }

}