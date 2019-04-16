package com.cnstock.service;

import com.cnstock.entity.TbJob;
import com.cnstock.entity.TbJobExample;
import com.cnstock.mapper.TbJobMapper;
import com.cnstock.utils.HttpUtils;
import com.cnstock.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * @author user01
 * @create 2019/2/13
 */
@Service
public class UpdateDBServiceImpl {

    public static Logger logger = LoggerFactory.getLogger(UpdateDBServiceImpl.class);

    @Autowired
    private TbJobMapper mapper;

    public Boolean update(TbJob tbJob) {
        int i = 0;
        try {
            if (StringUtils.isEmpty(tbJob.getJobUrl())) {
                return false;
            }
            tbJob.setJobUrl(filterUrl(tbJob.getJobUrl()));
            tbJob.setCreateTime(TimeUtils.getTime(new Date()));
            tbJob.setStatus(1);
            TbJobExample example = new TbJobExample();
            TbJobExample.Criteria criteria = example.createCriteria();
            criteria.andJobUrlEqualTo(filterUrl(tbJob.getJobUrl()));
            i = mapper.updateByExampleSelective(tbJob,example);
        } catch (Exception e) {
            logger.info("数据更新失败" + e);
            return false;
        }
        return i > 0;
    }


    public Boolean del(String id){
        int i = 0;
        try {
            if (StringUtils.isEmpty(id)) {
                return false;
            }
            i = mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.info("数据删除失败" + e);
            return false;
        }
        return i>0;
    }

    /**
     * 过滤URL
     * @param url
     * @return
     */
    public String filterUrl(String url){
        String newUrl = "";
        StringBuilder builder = new StringBuilder();
        String builderUrl = null;
        try {
            if (url.endsWith("/")){
                newUrl = url;
            } else {
                //最后一个斜杠的位置
                int last = url.lastIndexOf("/");
                String substring = url.substring(last + 1, url.length());
                if (!substring.contains(".")){
                    newUrl = url + "/";
                } else {
                    newUrl = url;
                }
            }
            if (newUrl.contains("http://") || newUrl.contains("https://")) {
                return newUrl;
            } else {
                builder.append("http://" + newUrl);
                if (HttpUtils.isOk(builder.toString()) == true) {
                    builderUrl = builder.toString();
                } else {
                    builder.setLength(0);
                    builder.append("https://" + newUrl);
                    builderUrl = builder.toString();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return builderUrl;
    }
}

