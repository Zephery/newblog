//package com.myblog.util;
//
//import org.apache.commons.configuration.Configuration;
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisConnectionException;
//
//import java.util.List;
//import java.util.Map;
//
//public class JedisUtil {
//
//    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
//    private static JedisUtil jedisUtil = null;
//    private static Configuration configuration;
//
//    static {
//        try {
//            configuration = new PropertiesConfiguration("load.properties");
//        } catch (ConfigurationException e) {
//            logger.error("读取配置文件错误", e);
//        }
//    }
//
//    private JedisPool jedisPool = null;
//
//    public JedisUtil() {
//        initJedisPool();
//    }
//
//    public static JedisUtil getInstance() {
//        if (jedisUtil == null) {
//            jedisUtil = createInstance();
//        }
//        return jedisUtil;
//    }
//
//    private synchronized static JedisUtil createInstance() {
//        if (jedisUtil == null) {
//            jedisUtil = new JedisUtil();
//        }
//        return jedisUtil;
//    }
//
//    private void initJedisPool() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(configuration.getInt("maxIdle"));
//        config.setMinIdle(configuration.getInt("minIdle"));
//        config.setMaxWaitMillis(configuration.getInt("maxWait"));
//        jedisPool = new JedisPool(config, configuration.getString("host"), configuration.getInt("port"), configuration.getInt("timeout"), configuration.getString("password"));
//    }
//
//    /**
//     * 获取redis实例
//     */
//    private synchronized Jedis getJedis() {
//        if (jedisPool == null) {
//            initJedisPool();
//        }
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//        } catch (Exception e) {
//            logger.error("get resource:", e);
//        }
//        return jedis;
//    }
//
//    /**
//     * 释放 redis资源
//     */
//    private void returnJedisResource(final Jedis jedis) {
//        if (jedis != null) {
//            jedis.close();
//        }
//    }
//
//    /**
//     * 关闭链接池
//     */
//    public void shutdown() {
//        if (jedisPool != null) {
//            jedisPool.destroy();
//        }
//    }
//
//    public void set(String key, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                jedis.set(key, value);
//            } else {
//                logger.error("set opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("set connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public void mset(Map<String, String> map) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                if (map != null) {
//                    int len = map.size();
//                    if (len > 0) {
//                        String[] keys = map.keySet().toArray(new String[0]);
//                        String[] strings = new String[len * 2];
//
//                        for (int i = 0; i < len; i++) {
//                            strings[2 * i] = keys[i];
//                            strings[2 * i + 1] = map.get(keys[i]);
//                        }
////						logger.info(Arrays.toString(strings));
//                        jedis.mset(strings);
//                    }
//                }
//            } else {
//                logger.error("mset opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("mset connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public String get(String key) {
//        Jedis jedis = null;
//        String result = "";
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                result = jedis.get(key);
//            } else {
//                logger.error("get opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("get value connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return result;
//    }
//
//    public void set(String key, Map<String, String> map) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                jedis.hmset(key, map);
//            } else {
//                logger.error("hmset opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("hmset connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public void hset(String key, String field, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                jedis.hset(key, field, value);
//            } else {
//                logger.error("hset opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("hmset connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public Map<String, String> getHash(String key) {
//        Jedis jedis = null;
//        Map<String, String> result = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                result = jedis.hgetAll(key);
//            } else {
//                logger.error("hgetall opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("hgetall value connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return result;
//    }
//
//    public void del(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                jedis.del(key);
//            } else {
//                logger.error("del opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("delete connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public boolean exists(String key) {
//        Jedis jedis = null;
//        boolean exists = false;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                exists = jedis.exists(key);
//            } else {
//                logger.error("exists opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("exists connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return exists;
//    }
//
//    public long increame(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                return jedis.incr(key);
//            } else {
//                logger.error("increame opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("increame connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return 0L;
//    }
//
//    public void expire(String key, int seconds) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis != null) {
//                jedis.expire(key, seconds);
//            } else {
//                logger.error("increame opt connection null error!");
//            }
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("increame connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public void lpush(String key, String ele) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis failed.");
//            }
//            jedis.lpush(key, ele);
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("increame connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public String rpop(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis failed.");
//                return null;
//            }
//            return jedis.rpop(key);
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//                jedis = null;
//            }
//            logger.error("increame connect error:", e);
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return null;
//    }
//
//    public Long llen(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis fail");
//                return null;
//            }
//            return jedis.llen(key);
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//            }
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return null;
//    }
//
//    public void zadd(String key, double score, String member) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis fail");
//            }
//            jedis.zadd(key, score, member);
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//            }
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public Long zcard(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis fail");
//                return null;
//            }
//            return jedis.zcard(key);
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//            }
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return null;
//    }
//
//    public void zrem(String key, String member) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis fail");
//            }
//            jedis.zrem(key, member);
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//            }
//        } finally {
//            returnJedisResource(jedis);
//        }
//    }
//
//    public List<String> lrange(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            if (jedis == null) {
//                logger.error("get jedis fail");
//            }
//            List<String> list = jedis.lrange(key, 0, 50);
//            return list;
//        } catch (JedisConnectionException e) {
//            if (jedis != null) {
//                jedis.close();
//            }
//        } finally {
//            returnJedisResource(jedis);
//        }
//        return null;
//    }
//}