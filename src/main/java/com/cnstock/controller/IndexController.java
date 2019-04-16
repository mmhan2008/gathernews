package com.cnstock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cnstock.entity.Es;
import com.cnstock.service.ESRestServiceImpl;
import com.cnstock.service.IndexServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/14.
 */
@Controller
public class IndexController {

    private Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    private IndexServiceImpl indexService;
    @Autowired
    private ESRestServiceImpl esRestService;

    @RequestMapping(path = {"/query"},method = RequestMethod.POST)
    @ResponseBody
    public String query(HttpServletRequest request){
        String urlMd5 = request.getParameter("urlMd5");
//        esRestService.getIndexData();
        return esRestService.getIndexData(urlMd5,"spidernews_index",Es.class).size()+"";
    }

    /**
     * localhost:8080/getParamDemo?param1=1或者直接表单提交参数
     * @RequestParam，代表请求参数，required属性说明了参数是否是必须的
     */
    @RequestMapping(path = {"/getParam"},method = RequestMethod.POST)
    @ResponseBody
    public String getParam (HttpServletRequest request){
        String param = request.getParameter("param");
        if(param!=null&&!param.equals("")){
            Map<String, String> params = JSONObject.parseObject(param, new TypeReference<Map<String, String>>(){});
            ArrayList<JSONObject> list  = JSON.parseObject(params.get("message"), ArrayList.class);
//            logger.info("get data workerId " + params.get("workerId")+" drone queuesize is"+params.get("queue")+"  param "+params.get("message"));
            if(list!=null&&list.size()>0)
                indexService.objQueue.offer(list);
        }
        return "success get param";
    }

    @RequestMapping("/run")
    @ResponseBody
    public String run(){
        logger.info("start jobQueue-------"+indexService.jobQueue.size());
        indexService.getAllRunJob();
        return "hello";
    }


    @RequestMapping(path = {"/getTaskList"},method = RequestMethod.POST)
    @ResponseBody
    public String getTaskList(HttpServletRequest request){
        String param = request.getParameter("param");
        Map<String,Object> maps = new HashMap<>();
        if(param!=null&&!param.equals("")){
            Map<String, String> params = JSONObject.parseObject(param, new TypeReference<Map<String, String>>(){});
            ArrayList<JSONObject> list  = JSON.parseObject(params.get("list"), ArrayList.class);
            logger.info("task list size -------- "+list.size());
            maps=indexService.getTaskList(list,params.get("workerId"));
        }else{
            maps.put("status","0");
        }
        return JSON.toJSONString(maps);
    }

    @RequestMapping(path = {"/updateTaskList"})
    @ResponseBody
    public String updateTaskList(){
        Map<String,Object> maps = new HashMap<>();
        maps.put("status","200");
        maps.put("message","更新成功");
        indexService.getAllRunJob();
        return JSON.toJSONString(maps);
    }

    @RequestMapping("/clearCache")
    @ResponseBody
    public  String clean(){
        Map map = new LinkedHashMap();
        map.put("清空之前的cache",indexService.cache.size());
        map.put("清空之前的workerCache",indexService.workerCache.size());
        map.put("清空之前的heartCache",indexService.heartCache.size());
        map.put("清空之前的jobCache",indexService.jobCache.size());
        map.put("清空之前的upJobCache",indexService.upJobCache.size());
        indexService.cache.clear();
        indexService.workerCache.clear();
        indexService.heartCache.clear();
        indexService.jobCache.clear();
        indexService.upJobCache.clear();
        map.put("清空之后的cache",indexService.cache.size());
        map.put("清空之后的workerCache",indexService.workerCache.size());
        map.put("清空之后的heartCache",indexService.heartCache.size());
        map.put("清空之后的jobCache",indexService.jobCache.size());
        map.put("清空之后的upJobCache",indexService.upJobCache.size());
        return JSON.toJSONString(map);
    }
}
