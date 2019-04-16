package com.cnstock.mapper;

import org.apache.ibatis.annotations.Mapper;


/**
 * Created by Administrator on 2019/1/14.
 */
@Mapper
public interface IndexMapper {
    String getQuery();
}
