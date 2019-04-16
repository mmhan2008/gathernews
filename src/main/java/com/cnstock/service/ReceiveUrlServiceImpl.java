package com.cnstock.service;


import com.cnstock.entity.*;
import com.cnstock.mapper.TbJobMapper;
import com.cnstock.utils.HtmlUtil;
import com.cnstock.utils.HttpUtils;
import com.cnstock.utils.RedisUtil;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author user01
 * @create 2019/1/28
 */
@Service
public class ReceiveUrlServiceImpl {

    public static Logger logger = LoggerFactory.getLogger(ReceiveUrlServiceImpl.class);

    @Autowired
    private TbJobMapper tbJobMapper;

    @Value("${pythonHost}")
    private String pyHost;

    /**
     * 判断url是否存在
     * @param url
     * @return
     */
    public TbJob ifExist(String url){
        TbJob tbJob = new TbJob();
        String filterUrl = null;
        TbJobExample example = new TbJobExample();
        TbJobExample.Criteria criteria = example.createCriteria();
        try {
            if (!StringUtils.isEmpty(url)) {
                filterUrl = filterUrl(url);
                criteria.andJobUrlEqualTo(filterUrl);
                List<TbJob> tbJobs = tbJobMapper.selectByExample(example);
                Document document = null;
                if (tbJobs.size() == 0) {
                    document = Jsoup.parse(new URL(filterUrl), 10000);
                    String title = document.title();
                    tbJob.setJobName(filterStr(title));
                    tbJob.setJobUrl(filterUrl);
                } else {
                    tbJob = tbJobs.get(0);
                }
            }
        } catch (HttpStatusException e) {
            logger.info("Jsoup无法解析网站--{}", filterUrl);
        } catch (UnknownHostException e){
            logger.info("无效URL--{}",filterUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return tbJob;
    }

    /**
     * 过滤特殊字符
     * @param str
     * @return
     * @throws Exception
     */
    public String filterStr(String str) throws Exception {
        String regEx = "[`~!@#$%^&*()+=|{}:;\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        return Pattern.compile(regEx).matcher(str).replaceAll("").trim();
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

    /**
     * @param url
     * @param jobName
     * @return   UrlEntity集合  返回给页面
     */
    public Map analysisUrl(String url,String jobName,String format,String type) {
        Map resultMap = new HashMap();
        List<UrlEntity> urlEntities = new ArrayList<>();
        try {
            if (StringUtils.isEmpty(format) || "undefined".equals(format)){
                format = "utf-8";
            }
            String filterUrl = filterUrl(url);
            String cacheKey = "Tb_job_url_analysis:" + filterUrl;
            List list = new ArrayList();
            int hashCode = 0;
//            if (HttpUtils.isOk(filterUrl)){
                String htmlContent;
                if (format.contains("COOKIE")) {
                    if (format.contains("PY")){
                        Map map = new HashMap();
                        map.put("url",filterUrl);
                        map.put("type",format);
                        htmlContent =  HttpUtils.doPost(pyHost,map,"utf-8");
                        if (!StringUtils.isEmpty(htmlContent)){
                            hashCode = htmlContent.hashCode();
                            list = HtmlUtil.ScanHtmlA(htmlContent, filterUrl);
                            if (!list.isEmpty()){
                                urlEntities = urlGrouping(list,cacheKey);
                                resultMap.put("resultData",urlEntities);
                            } else {
                                resultMap.put("msg","当前模版无法解析网页，请更换解析模版");
                            }
                        }
                    } else {
                        htmlContent = HttpUtils.doPost(filterUrl,null,format.split("-")[0]);
                        if (!StringUtils.isEmpty(htmlContent)){
                            hashCode = htmlContent.hashCode();
                            list = HtmlUtil.ScanHtmlA(htmlContent, filterUrl);
                            if (!list.isEmpty()){
                                urlEntities = urlGrouping(list,cacheKey);
                                resultMap.put("resultData",urlEntities);
                            } else {
                                resultMap.put("msg","当前模版无法解析网页，请更换解析模版");
                            }
                        }
                    }
                } else {
                    if (format.contains("PY")) {
                        Map map = new HashMap();
                        map.put("url", filterUrl);
                        map.put("type", format);
                        htmlContent = HttpUtils.doPost(pyHost, map, "utf-8");
                        hashCode = htmlContent.hashCode();
                        list = HtmlUtil.ScanHtmlA(htmlContent, filterUrl);
                        if (!list.isEmpty()){
                            urlEntities = urlGrouping(list,cacheKey);
                            resultMap.put("resultData",urlEntities);
                        } else {
                            resultMap.put("msg","当前模版无法解析网页，请更换解析模版");
                        }
                    } else {
                        htmlContent= HttpUtils.doGet(filterUrl,null,format);
                        hashCode = htmlContent.hashCode();
                        list = HtmlUtil.ScanHtmlA(htmlContent, filterUrl);
                        if (!list.isEmpty()){
                            urlEntities = urlGrouping(list,cacheKey);
                            resultMap.put("resultData",urlEntities);
                        } else {
                            resultMap.put("msg","当前模版无法解析网页，请更换解析模版");
                        }
                    }
                }
//            } else {
//                resultMap.put("msg","当前网页无法解析!");
//            }
            TbJob job = new TbJob();
            job.setJobUrl(filterUrl);
            job.setJobName(jobName);
            job.setJobModel(format);
            job.setType(type);
            job.setHashCode(String.valueOf(hashCode));
            TbJobExample example = new TbJobExample();
            TbJobExample.Criteria criteria = example.createCriteria();
            criteria.andJobUrlEqualTo(filterUrl);
            List<TbJob> tbJobs = tbJobMapper.selectByExample(example);
            int insert = 0;
            if (tbJobs.isEmpty()) {
                job.setJobId(UUID.randomUUID().toString().replace("-", ""));
                insert = tbJobMapper.insert(job);
            } else {
                insert = tbJobMapper.updateByExampleSelective(job, example);
            }
            if (insert < 1) logger.info("**********数据库连接异常，未能创建或更新任务信息**********");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     *
     * @param url
     * @return   返回给页面keyWords集合
     */
    public List getKeywords(String url){
        List<String> keyWords = new ArrayList<>();
        List<String> result = new ArrayList<>();
        Gson gson = new Gson();
        try {
            String filterUrl = filterUrl(url);
            String cacheKey = "Tb_job_url_analysis:" + filterUrl;
            Object o = RedisUtil.get(cacheKey);
            String jsonO = gson.toJson(o);
            if (!StringUtils.isEmpty(o)){
                List list = gson.fromJson(jsonO, List.class);
                List<String> urls = new ArrayList();
                for (Object li : list) {
                    String json = gson.toJson(li);
                    UrlEntity urlEntity = gson.fromJson(json, UrlEntity.class);
                    urls.add(urlEntity.getBaseUrl()
                            .replace("http://","")
                            .replace("https://",""));
                }
                for (String keyWord : urls) {
                    String[] split = keyWord.split("/");
                    for (int i = 0; i < split.length; i++) {
                        String s = split[i].replaceAll("\\d", "");
                        if (!StringUtils.isEmpty(s)) {
                            keyWords.add(s);
                        }
                    }
                }
                Map<String, Integer> count = count(keyWords);
                Set<Map.Entry<String, Integer>> entrySet = count.entrySet();
                List<KeyWords> lists = new ArrayList<>();
                for (Map.Entry<String,Integer> entry : count.entrySet()) {
                    KeyWords key = new KeyWords();
                    key.setKey(entry.getKey());
                    key.setCount(entry.getValue());
                    lists.add(key);
                }
                Collections.sort(lists, (o1, o2) -> {
                    //按关键词出现的次数排序  从大到小
                    return o2.getCount() - o1.getCount();
                });
                for (KeyWords key: lists) {
                    result.add(key.getKey());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    /**
     * @param list
     * @return   封装UrlEntity集合
     */
    public List<UrlEntity> urlGrouping(List list,String cacheKey) {
        List<UrlEntity> results = new ArrayList<>();
        try {
            for (Object urls : list) {
                if (urls.toString().contains("http://") || urls.toString().contains("https://")) {
                    String replaceUrl = urls.toString().replace("####", "");
                    String[] split = replaceUrl.split("###");
                    UrlEntity entity = new UrlEntity();
                        entity.setBaseUrl(split[0]);
                        if (split.length == 2) {
                            entity.setTitle(split[1]);
                        }
                    results.add(entity);
                };
            }
            if (!results.isEmpty())
            RedisUtil.set(cacheKey,results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 修改内容 返回TbJob
     * @param url
     * @return
     */
    public TbJob queryRule(String url){
        TbJobExample example = new TbJobExample();
        TbJobExample.Criteria criteria = example.createCriteria();
        criteria.andJobUrlEqualTo(url);
        TbJob tbJob = null;
        try {
            List<TbJob> tbJobs = tbJobMapper.selectByExample(example);
            if(tbJobs.size()>0){
                tbJob = tbJobs.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbJob;
    }


    /**
     * 去重list中重复元素
     * @param list
     * @return List
     */
    public List removeDuplicate (List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    public static void main(String[] args) {
//        ReceiveUrlServiceImpl impl = new ReceiveUrlServiceImpl();
//         impl.analysisUrl("http://www.nea.gov.cn/policy/qt.htm");
//        LocalDateTime ldt = LocalDateTime.now();
//        String format = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        System.out.println(format);
//        System.out.println(UUID.randomUUID());
//        String str="123assume345contribute";
//        System.out.println(str.replaceAll("\\d+",""));
//
//        List list = Arrays.asList("a","b","c","d","a","a","b","c","d","d","d","d","d");
//        Map<String,Integer> count = impl.count(list);
//        Set<Map.Entry<String, Integer>> entrySet = count.entrySet();
//        List<KeyWords> lists = new ArrayList<>();
//        for (Map.Entry<String,Integer> entry : count.entrySet()) {
//            KeyWords keyWords = new KeyWords();
//            keyWords.setKey(entry.getKey());
//            keyWords.setCount(entry.getValue());
//            lists.add(keyWords);
//        }
//        System.out.println("排序前+++++++");
//        for (KeyWords s: lists) {
//            System.out.println(s.getKey() +"--"+  s.getCount());
//        }
//        Collections.sort(lists, (o1, o2) -> {
//            //按关键词出现的次数排序
//            return o2.getCount() - o1.getCount();
//        });
//        System.out.println("排序前+++++++");
//        for (KeyWords s: lists) {
//            System.out.println(s.getKey() + "--"+ s.getCount());
//        }
//        String s  = "www.baidu.com/";
//        String last = s.substring(s.length()-1,s.length());
//        System.out.println(last.equals("/"));
//        Connection connect = Jsoup.connect("http://www.nea.gov.cn/policy/qt.htm");
//        Document document = null;
//        try {
//            document = connect.get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String title = document.title();
//
//        System.out.println(title);
//        String url = "http://www.nea.gov.cn/policy/qt.htm";
//        System.out.println(url.contains("http://") || url.contains("https://"));
//        try {
//            System.out.println(impl.filterStr("fdsdsads|#对方水电费 */-+——@……%&fasdsdsdqdasd1"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Map map = new HashMap();
        map.put("url","http://www.nea.gov.cn/policy/qt.htm");
        map.put("type","py-cookie");
        String s = HttpUtils.doPost("http://172.20.10.91:5000/scanHtml", map, null);
        System.out.println(s);
    }



}

