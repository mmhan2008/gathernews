package com.cnstock.mapper;

import com.cnstock.entity.TbJob;
import com.cnstock.entity.TbJobExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TbJobMapper {
    int countByExample(TbJobExample example);

    int deleteByExample(TbJobExample example);

    int deleteByPrimaryKey(String jobId);

    int insert(TbJob record);

    int insertSelective(TbJob record);

    List<TbJob> selectByExample(TbJobExample example);

    List<TbJob> selectByCondition(TbJob tbJob);

    TbJob selectByPrimaryKey(String jobId);

    int updateByExampleSelective(@Param("record") TbJob record, @Param("example") TbJobExample example);

    int updateByExample(@Param("record") TbJob record, @Param("example") TbJobExample example);

    int updateByPrimaryKeySelective(TbJob record);

    int updateByPrimaryKey(TbJob record);
}