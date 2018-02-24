package com.myblog.dubbo;

import com.myblog.model.Blog;

/**
 * @author Zephery
 * @since 2018/1/13 15:57
 */
public interface DubboService {
    public String sayHello(String hello);

    public Blog get(Integer blogid);
}