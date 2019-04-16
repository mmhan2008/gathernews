package com.cnstock.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.cnstock.entity.TbError;
import com.cnstock.entity.TbJob;
import com.cnstock.entity.TbJobCount;
import com.cnstock.entity.TbLog;
import com.cnstock.service.QueryDBServiceImpl;
import com.cnstock.service.StatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author user01
 * @create 2019/2/28
 */
@Controller
public class StatisticsController {

    @Autowired
    private StatisticsServiceImpl statisticsServiceImpl;

    @Autowired
    private QueryDBServiceImpl queryDBServiceImpl;

    @RequestMapping("/getFail")
    @ResponseBody
    public JSONObject getFail(@RequestParam Integer pageNumber,
                              @RequestParam Integer pageSize,
                              @RequestParam String jobName,
                              @RequestParam String jobUrl){
        JSONObject jobAndError = statisticsServiceImpl.getJobAndError(pageNumber, pageSize,jobName,jobUrl);
        return jobAndError;
    }

    @RequestMapping("/details")
    public String getDetails(){
        return  "details";
    }

    @RequestMapping("/getJobAndCount")
    @ResponseBody
    public JSONObject queryAll(@RequestParam Integer pageNumber,
                               @RequestParam Integer pageSize ,
                               @RequestParam String jobName,
                               @RequestParam String jobUrl,
                               @RequestParam String isEnable,
                               @RequestParam String startTime,
                               @RequestParam String endTime
    ){
        JSONObject jsonObject = statisticsServiceImpl.getJobWithCount(pageNumber, pageSize, jobName,jobUrl,isEnable,startTime,endTime);
        return jsonObject;
    }

    @RequestMapping("/getJob")
    @ResponseBody
    public JSONObject getJob(@RequestParam String id){
        JSONObject json = new JSONObject();
        if (StringUtils.isEmpty(id)){
            json.put("msg","jobId不能为空");
            return  json;
        }
        TbJob tbJob = queryDBServiceImpl.getTbJob(id);
        json.put("msg",200);
        json.put("result",tbJob);
        return  json;
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public JSONObject getCount(@RequestParam String id){
        JSONObject json = new JSONObject();
        if (StringUtils.isEmpty(id)){
            json.put("msg","jobId不能为空");
            return  json;
        }
        List<TbJobCount> count = queryDBServiceImpl.getCount(id);
        json.put("msg",200);
        json.put("result",count);
        return json;
    }

    @RequestMapping("/getLog")
    @ResponseBody
    public JSONObject getLog(@RequestParam String id){
        JSONObject json = new JSONObject();
        if (StringUtils.isEmpty(id)){
            json.put("msg","jobId不能为空");
            return  json;
        }
        List<TbLog> log = queryDBServiceImpl.getLog(id);
        json.put("msg",200);
        json.put("result",log);
        return json;
    }

    @RequestMapping("/getError")
    @ResponseBody
    public JSONObject getError(@RequestParam String id){
        JSONObject json = new JSONObject();
        if (StringUtils.isEmpty(id)){
            json.put("msg","jobId不能为空");
            return  json;
        }
        List<TbError> error = queryDBServiceImpl.getError(id);
        json.put("msg",200);
        json.put("result",error);
        return json;
    }
}

