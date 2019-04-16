package com.cnstock.service;

import com.alibaba.fastjson.JSONObject;
import com.cnstock.entity.*;
import com.cnstock.mapper.TbErrorMapper;
import com.cnstock.mapper.TbJobCountMapper;
import com.cnstock.mapper.TbJobMapper;
import com.cnstock.mapper.TbLogMapper;
import com.cnstock.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author user01
 * @create 2019/2/21
 */

@Service
public class QueryDBServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(QueryDBServiceImpl.class);

    @Autowired
    private TbJobMapper mapper;

    @Autowired
    private TbJobCountMapper tbJobCountMapper;

    @Autowired
    private TbErrorMapper tbErrorMapper;

    @Autowired
    private TbLogMapper tbLogMapper;

    public JSONObject queryTbJob (Integer pageNumber,Integer pageSize,
                                     String jobName,String jobUrl,
                                     String isEnable){
//        List result = new ArrayList();
        List<TbJob> tbJobs = new ArrayList<>();
        JSONObject json = new JSONObject();
        try {
            TbJobExample example = new TbJobExample();
            example.setOrderByClause("jobUrl ASC");
            TbJobExample.Criteria criteria = example.createCriteria();
            PageHelper.startPage(StringUtils.isEmpty(pageNumber)?1:pageNumber,StringUtils.isEmpty(pageSize)?20:pageSize,true);
            if(!StringUtils.isEmpty(jobName) && StringUtils.isEmpty(isEnable) && StringUtils.isEmpty(jobUrl)){
                criteria.andJobNameLike("%" + jobName + "%");
            } else if(!StringUtils.isEmpty(jobName) && !StringUtils.isEmpty(isEnable) && StringUtils.isEmpty(jobUrl)){
                criteria.andJobNameLike("%" + jobName + "%").andIsEnableEqualTo(isEnable);
            } else if(StringUtils.isEmpty(jobName) && !StringUtils.isEmpty(isEnable) && !StringUtils.isEmpty(jobUrl)){
                criteria.andIsEnableEqualTo(isEnable).andJobUrlLike("%" + jobUrl.trim() + "%");
            } else if (!StringUtils.isEmpty(jobName) && StringUtils.isEmpty(isEnable) && !StringUtils.isEmpty(jobUrl)){
                criteria.andJobNameLike("%" + jobName + "%").andJobUrlLike("%" + jobUrl.trim() + "%");
            } else if (StringUtils.isEmpty(jobName) && !StringUtils.isEmpty(isEnable) && StringUtils.isEmpty(jobUrl)){
                criteria.andIsEnableEqualTo(isEnable);
            } else if (StringUtils.isEmpty(jobName) && StringUtils.isEmpty(isEnable) && !StringUtils.isEmpty(jobUrl)){
                criteria.andJobUrlLike("%" + jobUrl.trim() + "%");
            }
            tbJobs = mapper.selectByExample(example);
            PageInfo<TbJob> pageInfo = new PageInfo<>(tbJobs);
            long count = pageInfo.getTotal();
            json.put("rows", tbJobs);
            json.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 任务详情
     * @param jobId
     * @return
     */
    public TbJob getTbJob(String jobId){
        TbJob tbJob = null;
        try {
            tbJob = mapper.selectByPrimaryKey(jobId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbJob;
    }

    /**
     * 每日更新数量
     * @param jobId
     * @return
     */
    public List<TbJobCount> getCount(String jobId){
        TbJobCountExample example = new TbJobCountExample();
        example.setOrderByClause("date desc");
        TbJobCountExample.Criteria criteria = example.createCriteria();
        criteria.andJobIdEqualTo(jobId);
        List<TbJobCount> tbJobCounts = null;
        try {
            PageHelper.startPage(1,10);
            tbJobCounts = tbJobCountMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbJobCounts;
    }

    /**
     * 日志
     * @param jobId
     * @return
     */
    public List<TbLog> getLog(String jobId){
        TbLogExample example = new TbLogExample();
        example.setOrderByClause("updateTime desc");
        TbLogExample.Criteria criteria = example.createCriteria();
        criteria.andJobIdEqualTo(jobId);
        List<TbLog> tbLogs = null;
        PageHelper.startPage(1,10);
        try {
            tbLogs = tbLogMapper.selectByExample(example);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbLogs;
    }

    /**
     * 错误详情
     * @param jobId
     * @return
     */
    public List<TbError> getError(String jobId) {
        TbErrorExample example = new TbErrorExample();
        example.setOrderByClause("createTime desc");
        TbErrorExample.Criteria criteria = example.createCriteria();
        criteria.andJobIdEqualTo(jobId);
        List<TbError> tbErrors = null;
        PageHelper.startPage(1,10);
        try {
            tbErrors = tbErrorMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbErrors;
    }
}

