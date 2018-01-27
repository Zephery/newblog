package com.myblog.service.impl;

import com.myblog.dao.MyreadingMapper;
import com.myblog.model.Myreading;
import com.myblog.service.IMyReadingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/9 16:05
 * Description:
 */
@Service("myReadingService")
public class MyReadingServiceImpl implements IMyReadingService {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(MyReadingServiceImpl.class);
    @Resource
    private MyreadingMapper myreadingMapper;

    @Override
    @Cacheable(value = "myCache", keyGenerator = "customKeyGenerator")
    public Set<Myreading> getAllReading() {
        List<Myreading> list = myreadingMapper.getAllReading();
        Set<Myreading> set = new HashSet<>(list);
        try {
            for (Myreading myreading : list) {
                myreading.setRentdate(myreading.getRentdate() != null ? myreading.getRentdate() : "");
                myreading.setReturndate(myreading.getReturndate() != null ? myreading.getReturndate() : "");
            }
        } catch (Exception e) {
            logger.error("阅读记录", e);
        }
        return set;
    }
}