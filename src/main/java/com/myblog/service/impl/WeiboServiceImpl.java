package com.myblog.service.impl;

import com.myblog.dao.ImageMapper;
import com.myblog.dao.WeiboMapper;
import com.myblog.model.Weibo;
import com.myblog.service.IWeiboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/20 0:05
 * Description:
 */
@Service("weiboService")
public class WeiboServiceImpl implements IWeiboService {
    @Resource
    private WeiboMapper weiboMapper;

    @Override
    public List<Weibo> getAllWeibo() {
        return weiboMapper.getAllWeibo();
    }
}