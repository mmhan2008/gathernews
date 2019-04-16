package com.cnstock.controller;

import com.alibaba.fastjson.JSON;
import com.cnstock.service.UploadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author user01
 * @create 2019/2/27
 */
@Controller
public class UploadController {

    @Autowired
    private UploadServiceImpl uploadService;

    @PostMapping(value = "/upload")
    @ResponseBody
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        Map map = new HashMap();
        if (StringUtils.isEmpty(file)){
            map.put("msg","请提交有效的文件");
        }
        boolean upload = uploadService.upload(file);
        if (upload == true) {
            map.put("msg","文件上传成功！");
        } else {
            map.put("msg","文件上传失败！");
        }
        return JSON.toJSONString(map);
    }
}

