package com.myblog.http;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/15 9:11
 * Description:
 */
public class IdleConnectionEvictor extends Thread {
    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();// 启动线程
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    // 每隔5秒执行一个，关闭失效的http连接
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}