package com.myblog.service.impl;

import com.myblog.dao.LinksMapper;
import com.myblog.model.Links;
import com.myblog.service.ILinksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/28 18:31
 * Description:
 */
@Service("linksService")
public class LinksServiceImpl implements ILinksService {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(LinksServiceImpl.class);
    @Resource
    private LinksMapper linksMapper;

    @Override
    @Cacheable(value = "myCache", keyGenerator = "customKeyGenerator")
    public List<Links> getAllLinks() {
        return linksMapper.getAllLinks();
    }
}