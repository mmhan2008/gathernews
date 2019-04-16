package com.cnstock.controller;

import com.alibaba.fastjson.JSON;
import com.cnstock.service.CustomizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author user01
 * @create 2019/4/8
 */

@Controller
public class CustomizeController {

    @Autowired
    private CustomizeServiceImpl customizeService;

    @PostMapping(value = "getData")
    @ResponseBody
    /**
     * 接收python客户端发送的数据
     */
    public String getData(@RequestBody List<String> resultlist){

        Map map = new HashMap();

        if (!resultlist.isEmpty()){
            customizeService.handleData(resultlist);
            map.put("msg","数据已成功接收");
        } else {
            map.put("msg","数据接收失败");
        }

        return JSON.toJSONString(map);
    }
}

