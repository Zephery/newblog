package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.IpLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IpLogMapper extends BaseMapper<IpLog> {
    int deleteByPrimaryKey(Integer id);

    int insert(IpLog record);

    int insertSelective(IpLog record);

    IpLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpLog record);

    int updateByPrimaryKey(IpLog record);
}