package com.cnstock.mapper;

import com.cnstock.entity.TbLog;
import com.cnstock.entity.TbLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TbLogMapper {
    int countByExample(TbLogExample example);

    int deleteByExample(TbLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(TbLog record);

    int insertSelective(TbLog record);

    List<TbLog> selectByExample(TbLogExample example);

    TbLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") TbLog record, @Param("example") TbLogExample example);

    int updateByExample(@Param("record") TbLog record, @Param("example") TbLogExample example);

    int updateByPrimaryKeySelective(TbLog record);

    int updateByPrimaryKey(TbLog record);
}