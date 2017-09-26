package com.myblog.service;

import com.myblog.model.IpLog;

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
}