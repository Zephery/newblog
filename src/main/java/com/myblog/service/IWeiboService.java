package com.myblog.service;

import com.google.gson.JsonObject;
import com.myblog.model.Weibo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/20 0:03
 * Description:
 */
public interface IWeiboService {
    public List<Weibo> getAllWeiboToday();

    /**
     * 需要先启动本地的python脚本
     *
     * @param sentence
     * @return
     */
    public JsonObject getWeiboDetail(String sentence);
}