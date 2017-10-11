package com.myblog.service.impl;

import com.myblog.dao.BlogMapper;
import com.myblog.dao.IpLogMapper;
import com.myblog.dao.MyreadingMapper;
import com.myblog.model.IpLog;
import com.myblog.model.Myreading;
import com.myblog.service.IAsyncService;
import com.myblog.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/26 18:32
 * Description:Spring中异步
 */
@Service("asyncService")
@Transactional
public class AsyncServiceImpl implements IAsyncService {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Resource
    private IpLogMapper ipLogMapper;
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private IMessageService messageService;
    @Resource
    private MyreadingMapper myreadingMapper;

    @Async
    @Override
    public void insertIpLog(IpLog ipLog) {
        try {
            messageService.pushToMessageQueue("rabbit_queue_one", ipLog.toString());
            ipLogMapper.insertSelective(ipLog);      //记录每一条日志
        } catch (Exception e) {
            logger.error("ip插入错误", e);
        }
    }

    @Async
    @Override
    public void updatebloghits(Integer blogid) {
        try {
            blogMapper.updatehits(blogid);
        } catch (Exception e) {
            logger.error("更新阅读次数错误", e);
        }
    }

    @Async
    @Override
    public void insertlibrary(Myreading myreading) {
        try {
            myreadingMapper.insert(myreading);
        } catch (Exception e) {
            logger.error("借阅错误", e);
        }
    }
}