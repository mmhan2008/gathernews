package com.cnstock.mapper;

import com.cnstock.entity.TbJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author user01
 * @create 2019/2/28
 */
@Mapper
public interface StatisticsMapper {
    List getJobWithError(TbJob tbJob);

    List getJobWithCount(TbJob tbjob);
}

