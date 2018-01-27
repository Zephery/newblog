package com.myblog.service.impl;

import com.myblog.dao.BlogMapper;
import com.myblog.dao.IMongoDao;
import com.myblog.dao.IpLogMapper;
import com.myblog.dao.MyreadingMapper;
import com.myblog.model.IpLog;
import com.myblog.model.Myreading;
import com.myblog.service.IAsyncService;
import com.myblog.service.IMessageService;
import com.myblog.util.LibraryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/26 18:32
 * Description:Spring中异步
 */
@Service("asyncService")
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
    @Resource
    private IMongoDao mongoDao;
    @Resource
    private RedisTemplate redisTemplate;

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
    public void start() {
        List<Myreading> list = LibraryUtil.htmltoJavaBean();
        for (Myreading myreading : list) {
            myreadingMapper.insertSelective(myreading);
        }
        logger.info("start");
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

    @Async
    @Override
    public void insertMongo(HttpServletRequest request) {
        mongoDao.insert("{\"name\":\"菜鸟教程\"}", "requestlog");
    }

    @Async
    @Override
    public void insertMongo(String key, String value) {
        mongoDao.insert(key, value);
    }

    @Async
    @Override
    @SuppressWarnings("unchecked")
    public void insertMethodTime(String methodName, Long time) {
        redisTemplate.opsForHash().put("method", methodName, time);
    }

    @Async
    @Override
    public Object redisGet(String key,Long liveTime) {
        byte[] key1 = key.getBytes();
        return redisTemplate.execute(connection -> {
            byte[] value1 = connection.get(key1);
            if (value1 == null) {
                return null;
            }
            // 每次获得延迟时间
            if (liveTime > 0) {
                connection.expire(key1, liveTime);
            }
            return toObject(value1);
        }, true);
    }

    @Async
    @Override
    public void redisPut(String keyStr,Object valueStr,Long liveTime){
        redisTemplate.execute(connection -> {
            byte[] keyb = keyStr.getBytes();
            byte[] valueb = toByteArray(valueStr);
            connection.set(keyb, valueb);
            if (liveTime > 0) {
                connection.expire(keyb, liveTime);
            }
            return 1L;
        }, true);
    }


    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}