package com.myblog.cache;

import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.util.concurrent.Callable;

/**
 * @author Zephery
 * @since 2018/1/24 9:22
 */
public class EhRedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(EhRedisCache.class);

    private String name;

    /*** 一定容量的LRU队列 */
    @Resource
    private net.sf.ehcache.Cache ehCache;

    /*** 无容量限制key带时效性 */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private long liveTime = 60 * 60; //seconds

    private int activeCount = 10;

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    /**
     * 获取自定义缓存
     */
    @Override
    public ValueWrapper get(Object key) {
        Element value = ehCache.get(key);
        logger.info("===========================Cache L1 (ehcache) :{" + name + "}{" + key + "}={" + value + "}");
        if (value != null) {
            // TODO 访问10次EhCache 强制访问一次redis 使得数据不失效
//          if (value.getHitCount() < activeCount) {
            return new SimpleValueWrapper(value.getObjectValue());
//          } else {
//              value.resetAccessStatistics();
//          }
        }
        final String keyStr = name + "_" + key.toString();
        Object objectValue = redisTemplate.execute(connection -> {
            byte[] key1 = keyStr.getBytes();
            byte[] value1 = connection.get(key.toString().getBytes());
            if (value1 == null) {
                return null;
            }
            // 每次获得延迟时间
            if (liveTime > 0) {
                connection.expire(key1, liveTime);
            }
            return toObject(value1);
        }, true);
        ehCache.put(new Element(key, objectValue));// 取出来之后缓存到本地
        logger.info("===================Cache L2 (redis) :{" + name + "}{" + key + "}={" + objectValue + "}");
        return (objectValue != null ? new SimpleValueWrapper(objectValue) : null);

    }

    /**
     * 更新自定义缓存
     */
    @Override
    public void put(Object key, Object value) {
        ehCache.put(new Element(key, value));
        final String keyStr = key.toString();
        redisTemplate.execute(connection -> {
            byte[] keyb = keyStr.getBytes();
            byte[] valueb = toByteArray(value);
            connection.set(keyb, valueb);
            if (liveTime > 0) {
                connection.expire(keyb, liveTime);
            }
            return 1L;
        }, true);
    }

    /**
     * 删除指定key缓存
     */
    @Override
    public void evict(Object key) {
        ehCache.remove(key);
        final String keyStr = key.toString();
        redisTemplate.execute(connection -> connection.del(keyStr.getBytes()), true);
    }

    /**
     * 清除缓存
     */
    @Override
    public void clear() {
        ehCache.removeAll();
        redisTemplate.execute(connection -> {
            connection.flushDb();
            return "clear done.";
        }, true);
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

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.cache.Cache#get(java.lang.Object,
     * java.lang.Class)
     */
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (key == null || null == type) {
            return null;
        } else {
//          final String finalKey;
            final Class<T> finalType = type;
//          if (key instanceof String) {
//              finalKey = (String) key;
//          } else {
//              finalKey = key.toString();
//          }
//          final Object object = this.get(finalKey);
            final Object object = this.get(key);
            if (finalType != null && finalType.isInstance(object)
                    && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.cache.Cache#putIfAbsent(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
//      final String finalKey;
        if (key == null || value == null) {
            return null;
        } else {
//          if (key instanceof String) {
//              finalKey = (String) key;
//          } else {
//              finalKey = key.toString();
//          }
//          if (!StringUtils.isEmpty(finalKey)) {
//              final Object finalValue = value;
            this.put(key, value);
//          }
        }
        return new SimpleValueWrapper(value);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }
}