package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Weibo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeiboMapper extends BaseMapper<Weibo> {
    int deleteByPrimaryKey(Integer id);

    int insert(Weibo record);

    int insertSelective(Weibo record);

    Weibo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weibo record);

    int updateByPrimaryKeyWithBLOBs(Weibo record);

    int updateByPrimaryKey(Weibo record);

    List<Weibo> getAllWeiboToday();
}