package com.cnstock.service;

import com.cnstock.entity.TbJob;
import com.cnstock.entity.TbJobExample;
import com.cnstock.mapper.TbJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.List;

/**
 * @author user01
 * @create 2019/3/18
 */
@Service
public class ExportTbJob {

    private static Logger logger = LoggerFactory.getLogger(ExportTbJob.class);

    @Autowired
    private TbJobMapper tbJobMapper;

    public Boolean exportTxt (HttpServletResponse response){
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=tbJob.txt");
        TbJobExample example = new TbJobExample();
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        StringBuilder builder = new StringBuilder();
        try {
            List<TbJob> tbJobs = tbJobMapper.selectByExample(example);
            for (TbJob tbjob:tbJobs) {
                builder.append("url:" + tbjob.getJobUrl() + "\t模版:" + tbjob.getJobModel() + "\r\n");
            }
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(builder.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

