package com.myblog.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Zephery
 * @since 2018/1/17 20:41
 */
@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append(".");
        sb.append(method.getName());
        for (Object obj : objects) {
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}