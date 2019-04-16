package com.cnstock.mapper;

import com.cnstock.entity.TbConfigure;
import com.cnstock.entity.TbConfigureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbConfigureMapper {
    int countByExample(TbConfigureExample example);

    int deleteByExample(TbConfigureExample example);

    int deleteByPrimaryKey(Integer configureId);

    int insert(TbConfigure record);

    int insertSelective(TbConfigure record);

    List<TbConfigure> selectByExample(TbConfigureExample example);

    TbConfigure selectByPrimaryKey(Integer configureId);

    int updateByExampleSelective(@Param("record") TbConfigure record, @Param("example") TbConfigureExample example);

    int updateByExample(@Param("record") TbConfigure record, @Param("example") TbConfigureExample example);

    int updateByPrimaryKeySelective(TbConfigure record);

    int updateByPrimaryKey(TbConfigure record);
}