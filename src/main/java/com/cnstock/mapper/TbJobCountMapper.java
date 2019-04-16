package com.cnstock.mapper;

import com.cnstock.entity.TbJobCount;
import com.cnstock.entity.TbJobCountExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TbJobCountMapper {
    int countByExample(TbJobCountExample example);

    int deleteByExample(TbJobCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbJobCount record);

    int insertSelective(TbJobCount record);

    int insertForeach(List<TbJobCount> list);

    List<TbJobCount> selectByExample(TbJobCountExample example);

    TbJobCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbJobCount record, @Param("example") TbJobCountExample example);

    int updateByExample(@Param("record") TbJobCount record, @Param("example") TbJobCountExample example);

    int updateByPrimaryKeySelective(TbJobCount record);

    int updateByPrimaryKey(TbJobCount record);
}