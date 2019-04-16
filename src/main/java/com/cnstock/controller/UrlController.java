package com.cnstock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnstock.entity.TbJob;
import com.cnstock.entity.UrlEntity;
import com.cnstock.service.QueryDBServiceImpl;
import com.cnstock.service.ReceiveUrlServiceImpl;
import com.cnstock.service.UpdateDBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author user01
 * @create 2019/1/29
 */
@Controller
public class UrlController {

    @Autowired
    private ReceiveUrlServiceImpl receiveUrlService;

    @Autowired
    private UpdateDBServiceImpl updateDBService;

    @Autowired
    private QueryDBServiceImpl queryDBService;

    @RequestMapping("/getUrl")
    @ResponseBody
    public String analysisUrl(@RequestParam String url,
                              @RequestParam String jobName,
                              @RequestParam String format,
                              @RequestParam String type){
        if (StringUtils.isEmpty(url)){
            return null;
        }
        Map map = receiveUrlService.analysisUrl(url, jobName, format,type);
        return JSON.toJSONString(map);
    }


    @RequestMapping("/getKey")
    public String getKeyWords(@RequestParam String url,Model model){

        List keywords = receiveUrlService.getKeywords(url);
        model.addAttribute("keyWords",keywords);
        return "rule";
    }

    @RequestMapping("/updateDB")
    @ResponseBody
    public String changeKey(@RequestBody TbJob tbJob){
        Map map =  new HashMap();
        Boolean update = updateDBService.update(tbJob);
        if (update == true ) {
            map.put("msg","创建任务成功");
        } else {
            map.put("msg","创建任务失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getRule")
    @ResponseBody
    public String queryRule(@RequestParam String url){
        if (StringUtils.isEmpty(url)){
            return null;
        }
        TbJob tbJob = receiveUrlService.queryRule(url);
        return JSON.toJSONString(tbJob);
    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONObject query(@RequestParam Integer pageNumber,
                               @RequestParam Integer pageSize ,
                               @RequestParam String jobName,
                               @RequestParam String jobUrl,
                               @RequestParam String isEnable
    ){
        JSONObject jsonObject = queryDBService.queryTbJob(pageNumber, pageSize, jobName,jobUrl,isEnable);
        return jsonObject;
    }

    @RequestMapping("/queryExist")
    @ResponseBody
    public String ifExist(@RequestParam String url){
        if(StringUtils.isEmpty(url)){
            return "url不能为空";
        }
        TbJob tbJob = receiveUrlService.ifExist(url);
        return JSON.toJSONString(tbJob);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(@RequestParam String id){
        Boolean del = updateDBService.del(id);
        return JSON.toJSONString(del);
    }

    @RequestMapping("/preview")
    public String new1() {
        return "preview";
    }

    @RequestMapping("/ruleconf")
    public String new2() {
        return "ruleconf";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/fail")
    public String fail(){
        return "failure";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}

