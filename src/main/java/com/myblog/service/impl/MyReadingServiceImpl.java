package com.myblog.service.impl;

import com.myblog.dao.MyreadingMapper;
import com.myblog.model.Myreading;
import com.myblog.service.IMyReadingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/9 16:05
 * Description:
 */
@Slf4j
@Service("myReadingService")
public class MyReadingServiceImpl implements IMyReadingService {
    @Resource
    private MyreadingMapper myreadingMapper;

    @Override
    public Set<Myreading> getAllReading() {
        List<Myreading> list = myreadingMapper.getAllReading();
        Set<Myreading> set = new HashSet<>(list);
        try {
            for (Myreading myreading : list) {
                myreading.setRentdate(myreading.getRentdate() != null ? myreading.getRentdate() : "");
                myreading.setReturndate(myreading.getReturndate() != null ? myreading.getReturndate() : "");
            }
        } catch (Exception e) {
            log.error("阅读记录", e);
        }
        return set;
    }
}