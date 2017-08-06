package com.myblog.dao;

import com.myblog.model.IpLog;
import com.myblog.model.IpLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ipLogMapper")
public interface IpLogMapper {
    long countByExample(IpLogExample example);

    int deleteByExample(IpLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IpLog record);

    int insertSelective(IpLog record);

    List<IpLog> selectByExample(IpLogExample example);

    IpLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IpLog record, @Param("example") IpLogExample example);

    int updateByExample(@Param("record") IpLog record, @Param("example") IpLogExample example);

    int updateByPrimaryKeySelective(IpLog record);

    int updateByPrimaryKey(IpLog record);
}