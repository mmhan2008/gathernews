package com.cnstock.controller;

import com.cnstock.entity.TbJob;
import com.cnstock.entity.TbJobExample;
import com.cnstock.mapper.TbJobMapper;
import com.cnstock.service.ReceiveUrlServiceImpl;
import com.cnstock.utils.HtmlUtil;
import com.cnstock.utils.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlControllerTest.class)
@WebAppConfiguration
@ComponentScan(value = "com.cnstock")
public class UrlControllerTest {
    @Autowired
    private ReceiveUrlServiceImpl service;

    @Autowired
    private TbJobMapper tbJobMapper;

    @Test
    public void analysisUrl() {
        String projectPath = new File("").getAbsolutePath();
        TbJobExample example = new TbJobExample();
        TbJobExample.Criteria criteria = example.createCriteria();
        criteria.andIsEnableEqualTo("停用");
        List<TbJob> tbJobs = tbJobMapper.selectByExample(example);
        int j = 0;
        for (TbJob tbJob:tbJobs){
            int i = 0;
            j ++;
            try {
            i = doGet(tbJob.getJobUrl(), null, "utf-8");
            if (i == 0){
                i = doPost(tbJob.getJobUrl(), null, "utf-8");
            }
            FileWriter fileWriter = null;
            fileWriter = new FileWriter( projectPath + "/config/zhuangtai.txt",true);
            fileWriter.write(j +"、"+ tbJob.getJobUrl() + "\t\t状态码：" + i + "\r\n");
            fileWriter.flush();
            fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addUrl(){
        String projectPath = new File("").getAbsolutePath();
        String pathname = projectPath + "/config/http.txt";
        try (
                FileReader reader = new FileReader(pathname);
                BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            List <String>  list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String url = "";
                if(line.contains("##")){
                    url = line.split("##")[1];
                    list.add(url);
                }else{
                    url = line;
                    list.add(url);
                }
            }
            int i = 0;
            for (String ss:list) {
                i ++;
                System.out.println("当前正在解析第" + i + "条URL：" + ss);
                TbJob tbJob = new TbJob();
                String filterUrl = filterUrl(ss);
                TbJobExample example = new TbJobExample();
                TbJobExample.Criteria criteria = example.createCriteria();
                criteria.andJobUrlEqualTo(filterUrl);
                List<TbJob> tbJobs = tbJobMapper.selectByExample(example);
                Document document = null;
                if (tbJobs.size() <= 0) {
                    try {
                        document = Jsoup.parse(new URL(filterUrl),5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!StringUtils.isEmpty(document)) {
                        String title = document.title();
                        tbJob.setJobName(title);
                        tbJob.setJobUrl(filterUrl);
                        tbJob.setIsEnable("启用");
                        String htmlContent = HttpUtils.doGet(filterUrl, null, "utf-8");
                        int hashCode = htmlContent.hashCode();
                        tbJob.setHashCode(String.valueOf(hashCode));
                        tbJob.setJobId(UUID.randomUUID().toString().replace("-", ""));
                        try {
                            tbJobMapper.insertSelective(tbJob);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        FileWriter fileWriter = new FileWriter( projectPath + "/config/error1.txt",true);
                        fileWriter.write(ss + "\r\n");
                        fileWriter.flush();
                        fileWriter.close();
                    }
                }else{
                    try {
                        document = Jsoup.parse(new URL(filterUrl),5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!StringUtils.isEmpty(document)) {
                        String title = document.title();
                        tbJob.setJobName(title);
                        tbJob.setJobUrl(filterUrl);
                        tbJob.setIsEnable("启用");
                        String htmlContent = HttpUtils.doGet(filterUrl, null, "utf-8");
                        int hashCode = htmlContent.hashCode();
                        tbJob.setHashCode(String.valueOf(hashCode));
                        tbJob.setJobId(UUID.randomUUID().toString().replace("-", ""));
                        try {
                            tbJobMapper.updateByExampleSelective(tbJob,example);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        FileWriter fileWriter = new FileWriter( projectPath + "/config/error1.txt",true);
                        fileWriter.write(ss + "\r\n");
                        fileWriter.flush();
                        fileWriter.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String filterUrl(String url){
        String last = url.substring(url.length() - 1, url.length());
        String newUrl = "";
        StringBuilder builder = new StringBuilder();
        String builderUrl = "";
        try {
            if (last.equals("/") == true) {
                newUrl = url.substring(0,url.length() - 1);
            } else {
                newUrl = url;
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


    public static int doPost(String url, Map<String, String> param,String format) {
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(10000).setConnectTimeout(10000).setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return statusCode;
    }

    public static int doGet(String url, Map<String, String> param,String format) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        int statusCode = 0;
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
            httpGet.setConfig(requestConfig);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            statusCode = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statusCode;
    }

    @Test
    public void test1(){
        String s = "https://zhidao.baidu.com/question";
        String s1 = service.filterUrl(s);
        System.out.println(s1);
    }
}