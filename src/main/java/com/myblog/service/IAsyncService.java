package com.myblog.service;

import com.myblog.model.IpLog;
import com.myblog.model.Myreading;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/26 18:32
 * Description:
 */
public interface IAsyncService {
    /**
     * 异步记录ip
     * * @param ipLog
     */
    public void insertIpLog(IpLog ipLog);

    /**
     * 异步更新阅读次数
     *
     * @param blogid
     * @return
     */
    public void updatebloghits(Integer blogid);

    /**
     * 借书记录
     *
     * @param myreading
     */
    public void insertlibrary(Myreading myreading);

    public void start();

    public void insertMongo(HttpServletRequest request);

    public void insertMongo(String key, String value);
}