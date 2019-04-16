package com.cnstock.mapper;

import com.cnstock.entity.TbError;
import com.cnstock.entity.TbErrorExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TbErrorMapper {
    int countByExample(TbErrorExample example);

    int deleteByExample(TbErrorExample example);

    int deleteByPrimaryKey(String errorId);

    int insert(TbError record);

    int insertSelective(TbError record);

    List<TbError> selectByExample(TbErrorExample example);

    List<TbError> selectByJobId(TbErrorExample example);

    TbError selectByPrimaryKey(String errorId);

    int updateByExampleSelective(@Param("record") TbError record, @Param("example") TbErrorExample example);

    int updateByExample(@Param("record") TbError record, @Param("example") TbErrorExample example);

    int updateByPrimaryKeySelective(TbError record);

    int updateByPrimaryKey(TbError record);
}