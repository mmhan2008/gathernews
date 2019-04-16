package com.cnstock.controller;

import com.cnstock.service.ExportTbJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author user01
 * @create 2019/3/18
 */
@Controller
public class ExportController {

    @Autowired
    private ExportTbJob exportTbJob;

    @RequestMapping("export")
    @ResponseBody
    public String getTxt(HttpServletResponse response){
        return exportTbJob.exportTxt(response).toString();
    }
}

