package com.cnstock.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cnstock.entity.Es;
import com.cnstock.entity.TbJobCount;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2019/1/16.
 */
@Service
public class ESRestServiceImpl {
    private Logger logger = Logger.getLogger(ESRestServiceImpl.class);
    private static  RestClient restClient = null;

    @Value(value = "${com.cnstock.esport}")
    private static int PORT;
    @Value(value = "${com.cnstock.eshost}")
    private static String HOST;

    public static RestClient getInstance(){
        if (restClient == null){
            synchronized (ESRestServiceImpl.class){
                if (restClient == null){
                    restClient = RestClient.builder(new HttpHost("sh.es.ssnews.com.cn", 9200, "http")).build();
                }
            }
        }
        return restClient;

    }

    public <T> boolean putSync(String index, String type, String id, T es) {
        HttpEntity entity = new StringEntity(JSON.toJSONString(es), ContentType.APPLICATION_JSON);
        Response indexResponse = null;
        try {
            indexResponse = getInstance().performRequest(
                               "PUT",
                                "/" + index + "/" + type + "/" + id,
                                Collections.<String, String>emptyMap(),
                                entity);
           } catch (IOException e) {
               e.printStackTrace();
        }
      return (indexResponse != null);
    }

    public int getIndexDataSize(){
        int result = 0;
        Map<String, String> params = Collections.emptyMap();
        String queryString = "{\n" +
                "\"query\": { \"match_all\": {} }\n" +
                "}";
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Response response = getInstance().performRequest("GET", "/spidernews_index/_search", params, entity);
            System.out.println(response.getStatusLine().getStatusCode());
            String responseBody = null;
            responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);
            Map<String, String> hitsMap = JSONObject.parseObject(jsonObject.get("hits").toString(), new TypeReference<Map<String, String>>(){});
            result = Integer.parseInt(hitsMap.get("total"));

        }catch (ResponseException e){
            e.printStackTrace();
        }catch (IOException e ){
            e.printStackTrace();
        }
        return result;
    }

    public List<Es> getIndexData(){
        int size = getIndexDataSize();
        List<Es> esList = new ArrayList<>();
        Map<String, String> params = Collections.emptyMap();
        String queryString="";
        if(size>0){
            queryString = "{\n" +
                    "\"query\": { \"match_all\": {} },\n" +
                    "\"size\" : "+size+"\n" +
                    "}";
        }

        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Response response = getInstance().performRequest("GET", "/spidernews_index/_search", params, entity);
            String responseBody = null;
            responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);
            Map<String, String> hitsMap = JSONObject.parseObject(jsonObject.get("hits").toString(), new TypeReference<Map<String, String>>(){});

            JSONArray array = JSON.parseArray(hitsMap.get("hits"));
            logger.info("search es data "+array.size());
            for (int i = 0; i < array.size(); i++) {
                //JSONArray中的数据转换为String类型需要在外边加"";不然会报出类型强转异常！
                String str = array.get(i)+"";
                Es es = JSON.parseObject(JSON.parseObject(str).get("_source").toString(), Es.class);
                esList.add(es);
            }
        }catch (ResponseException e){
            return esList;
        }catch (IOException e ){
            return esList;
        }
        return esList;
    }



    public <T> List<T> getIndexData(String urlMd5,String index,Class<T> clazz){
        List<T> esList = new ArrayList<>();
        Map<String, String> params = Collections.emptyMap();
        String queryString = "{\n" +
                    "\"query\": { \"match\": {\"urlMd5\":\""+urlMd5+"\"} }\n" +

                    "}";
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Response response = getInstance().performRequest("GET", "/" + index + "/_search", params, entity);
            String responseBody = null;
            responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);
            Map<String, String> hitsMap = JSONObject.parseObject(jsonObject.get("hits").toString(), new TypeReference<Map<String, String>>(){});

            JSONArray array = JSON.parseArray(hitsMap.get("hits"));
            for (int i = 0; i < array.size(); i++) {
                //JSONArray中的数据转换为String类型需要在外边加"";不然会报出类型强转异常！
                String str = array.get(i)+"";
                T es = JSON.parseObject(JSON.parseObject(str).get("_source").toString(), clazz);
                esList.add(es);
            }
        }catch (ResponseException e){
            return esList;
        }catch (IOException e ){
            return esList;
        }
        return esList;
    }


    public List<Es> getTodayEsData(){
        List<Es> esList = new ArrayList<>();
        Map<String, String> params = Collections.emptyMap();
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        String queryString = "{\"query\":{\"range\":{\"createTime\":{\"gte\":\""+zero+"\",\"lte\":\""+twelve+"\"}}},\"size\":10000}";
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Response response = getInstance().performRequest("GET", "/spidernews_index/_search", params, entity);
            String responseBody = null;
            responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);
            Map<String, String> hitsMap = JSONObject.parseObject(jsonObject.get("hits").toString(), new TypeReference<Map<String, String>>(){});

            JSONArray array = JSON.parseArray(hitsMap.get("hits"));
            for (int i = 0; i < array.size(); i++) {
                //JSONArray中的数据转换为String类型需要在外边加"";不然会报出类型强转异常！
                String str = array.get(i)+"";
                Es es = JSON.parseObject(JSON.parseObject(str).get("_source").toString(), Es.class);
                esList.add(es);
            }
        }catch (ResponseException e){


            return esList;
        }catch (IOException e ){
            return esList;
        }
        return esList;
    }


    public List<TbJobCount> getJobOfCount(List<Es> list){
        List<String> jobIds = new ArrayList();
        List<TbJobCount> lists = new ArrayList();
        if (list.size()>0) {
            for (Es es : list) {
                jobIds.add(es.getId());
            }
            Map<String, Integer> countMap = count(jobIds);
            Set<Map.Entry<String, Integer>> entrySet = countMap.entrySet();
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                TbJobCount jobCount = new TbJobCount();
                jobCount.setJobId(entry.getKey());
                jobCount.setDateCount(entry.getValue());
                lists.add(jobCount);
            }
        }
        return lists;
    }

    /**
     * 计算list中元素出现的次数
     * @param list
     * @return
     */
    public <T> Map<String,Integer> count(List<T> list) {
        Set<T> uniqueSet = new HashSet(list);
        Map map = new HashMap();
        //计算list中元素出现的次数
        for (T temp : uniqueSet) {
            map.put(temp,Collections.frequency(list, temp));
        }
        return map;
    }


    @PostConstruct
    public void init(){
        restClient = RestClient.builder(new HttpHost("sh.es.ssnews.com.cn", 9200, "http")).build();

     }

    @PreDestroy
    public void destroy(){
        if(restClient != null){
            try {
                restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        System.out.println(zero + "\n" + twelve);
    }
}