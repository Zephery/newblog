package com.myblog.service;

import com.myblog.model.IpLog;
import com.myblog.model.Myreading;
import jakarta.servlet.http.HttpServletRequest;


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

    /**
     * 统计耗时
     *
     * @param methodName
     * @param time
     */
    public void insertMethodTime(String methodName, Long time);

    public Object redisGet(String key, Long liveTime);

    public void redisPut(String keyStr, Object valueStr, Long liveTime);
}