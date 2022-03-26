package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Myreading;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyreadingMapper extends BaseMapper<Myreading> {
    int deleteByPrimaryKey(Integer id);

    int insert(Myreading record);

    int insertSelective(Myreading record);

    Myreading selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Myreading record);

    int updateByPrimaryKey(Myreading record);

    List<Myreading> getAllReading();
}