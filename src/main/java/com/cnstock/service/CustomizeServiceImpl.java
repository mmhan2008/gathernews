package com.cnstock.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnstock.entity.Es;
import com.cnstock.entity.GovernmentES;
import com.cnstock.utils.AESUtils;
import com.cnstock.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author user01
 * @create 2019/4/8
 */

@Service
public class CustomizeServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(CustomizeServiceImpl.class);

    @Autowired
    private ESRestServiceImpl esRestService;

    @Value("${checkCompanyHost}")
    private String checkCompanyHost;

    public void handleData(List<String> list){
        logger.info("=====================开始解析政府判决网站连接=====================");
        logger.info(JSON.toJSONString(list));
        int i = 0;
        for (String data:list) {
            GovernmentES es = new GovernmentES();
            String[] split = data.split("###");
            if (ifContainCompany(split[1])){
                i ++;
                es.setCreateTime(new Date());
                es.setLink(split[0]);
                es.setTitle(split[1]);
                es.setPublishTime(split[2].replace("发布日期：","").trim());
                es.setUrlMd5(AESUtils.encryption(split[0]));
                List<GovernmentES> esList = esRestService.getIndexData(es.getUrlMd5(), "gather_pjws", GovernmentES.class);
                if(esList.size()==0 || esList.isEmpty()){
                    String id = UUID.randomUUID().toString().replaceAll("-", "");
                    //添加数据
                    boolean result = esRestService.putSync("gather_pjws","gather_type",id,es);
                }
            }
        }
        logger.info("======================本次符合条件的数据总共有{}条======================",i);
    }

    public Boolean ifContainCompany(String text){
        String url = checkCompanyHost + "text";
        String result = HttpUtils.doGet(url, null, "utf-8");
        JSONObject jsonObject = JSON.parseObject(result);
        if ("2".equals(jsonObject.get("code"))){
            return true;
        };
        return false;
    }

    public static void main(String[] args) {
        List<String> lists = Arrays.asList(
            "http://wenshu.court.gov.cn/content/content?DocID=05375f7c-982f-4c70-955f-aa2900ae9934&KeyWord=###(2019)湘1022民初615号民事裁定书###      发布日期：2019-04-08",
            "http://wenshu.court.gov.cn/content/content?DocID=919fe6c5-10bd-48a1-9177-aa2900ae98d2&KeyWord=###(2019)湘1022民初614号民事裁定书###      发布日期：2019-04-08",
            "http://wenshu.court.gov.cn/content/content?DocID=510f4c91-34ad-44fa-9ba4-aa2900ae99a5&KeyWord=###(2019)湘1022民初617号民事裁定书###      发布日期：2019-04-08"
        );
    }
}

