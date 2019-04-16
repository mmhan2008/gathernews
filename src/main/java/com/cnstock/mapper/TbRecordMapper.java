package com.cnstock.mapper;

import com.cnstock.entity.TbRecord;
import com.cnstock.entity.TbRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRecordMapper {
    int countByExample(TbRecordExample example);

    int deleteByExample(TbRecordExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(TbRecord record);

    int insertSelective(TbRecord record);

    List<TbRecord> selectByExample(TbRecordExample example);

    TbRecord selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") TbRecord record, @Param("example") TbRecordExample example);

    int updateByExample(@Param("record") TbRecord record, @Param("example") TbRecordExample example);

    int updateByPrimaryKeySelective(TbRecord record);

    int updateByPrimaryKey(TbRecord record);
}