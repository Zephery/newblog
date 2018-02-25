package com.myblog.dubbo;

import com.myblog.model.Blog;
import com.myblog.model.Weibo;

import java.util.List;

/**
 * @author Zephery
 * @since 2018/1/13 15:57
 */
public interface DubboService {
    public String sayHello(String hello);

    public Blog get(Integer blogid);

    public List<Weibo> getAllWeiboToday();

}