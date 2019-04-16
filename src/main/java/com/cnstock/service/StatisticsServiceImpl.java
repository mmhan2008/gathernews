package com.cnstock.service;

import com.alibaba.fastjson.JSONObject;
import com.cnstock.entity.TbJob;
import com.cnstock.entity.TbJobCount;
import com.cnstock.entity.TbJobCountExample;
import com.cnstock.entity.TbJobExample;
import com.cnstock.mapper.StatisticsMapper;
import com.cnstock.mapper.TbJobCountMapper;
import com.cnstock.mapper.TbJobMapper;
import com.cnstock.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.internal.runners.statements.RunAfters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author user01
 * @create 2019/2/28
 */
@Service
public class StatisticsServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Autowired
    private StatisticsMapper statisticsMapper;

    public JSONObject getJobAndError(Integer pageNumber,Integer pageSize,
                                     String jobName,String jobUrl){
        List list = new ArrayList();
        JSONObject json = new JSONObject();
        try {
            PageHelper.startPage(StringUtils.isEmpty(pageNumber)?1:pageNumber,StringUtils.isEmpty(pageSize)?20:pageSize,true);
            TbJob tbJob = new TbJob();
            tbJob.setJobName(jobName);
            tbJob.setJobUrl(jobUrl.trim());
            list = statisticsMapper.getJobWithError(tbJob);
            PageInfo pageInfo = new PageInfo(list);
            long count = pageInfo.getTotal();
            json.put("rows", list);
            json.put("total", count);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("JobAndError查询异常" + e);
        }
        return json;
    }

    public JSONObject getJobWithCount (Integer pageNumber,Integer pageSize,
                                     String jobName,String jobUrl,
                                     String isEnable,String startTime,String endTime){
        JSONObject json = new JSONObject();
        List<TbJob> tbJobs = new ArrayList<>();
        try {
            TbJob tbJob = new TbJob();
            tbJob.setJobName(StringUtils.isEmpty(jobName)?null:jobName);
            tbJob.setJobUrl(StringUtils.isEmpty(jobUrl)?null:jobUrl);
            tbJob.setIsEnable(StringUtils.isEmpty(isEnable)?null:isEnable);
            tbJob.setStartTime(StringUtils.isEmpty(startTime)?null:TimeUtils.timeFormat(startTime));
            tbJob.setEndTime(StringUtils.isEmpty(endTime)?null:TimeUtils.endTimeFormat(endTime));
            PageHelper.startPage(StringUtils.isEmpty(pageNumber)?1:pageNumber,StringUtils.isEmpty(pageSize)?20:pageSize,true);

            tbJobs = statisticsMapper.getJobWithCount(tbJob);

            PageInfo<TbJob> pageInfo = new PageInfo<>(tbJobs);
            long count = pageInfo.getTotal();

            json.put("rows", tbJobs);
            json.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}

