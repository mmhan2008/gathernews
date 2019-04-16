package com.cnstock.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;

/**
 * Created by Administrator on 2019/1/15.
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String doPost(String url, Map<String, String> param,String format) {
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(10000).setConnectTimeout(10000).
                setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
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
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), format);
            }
        } catch (UnsupportedCharsetException e){
                logger.info("字符编码格式不支持");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response!=null)
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doGet(String url, Map<String, String> param,String format) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
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
                    setSocketTimeout(10000).setConnectTimeout(10000).setCookieSpec(CookieSpecs.STANDARD_STRICT).build();//设置请求和传输超时时间
            httpGet.setConfig(requestConfig);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), format);
            }
        }catch (UnsupportedCharsetException e){
            logger.info("字符编码格式不支持");
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
        return resultString;
    }

    public static String dynamicCookie(String url,String format){
        // 全局请求设置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();
        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                .setDefaultCookieStore(cookieStore).build();

        CloseableHttpResponse res = null;
        String resultString = "";
        // 创建本地的HTTP内容
        try {
                // 创建一个get请求用来获取必要的Cookie，如_xsrf信息
                HttpGet get = new HttpGet(url);
                res = httpClient.execute(get);
                // 获取常用Cookie,包括_xsrf信息,放在发送请求之后
                System.out.println("访问首页后的获取的常规Cookie:===============");
                for (Cookie c : cookieStore.getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }
                res.close();
                // 构造post数据
                List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
                for (Cookie c : cookieStore.getCookies()) {
                    valuePairs.add(new BasicNameValuePair(c.getName(), c.getValue()));
                }
                valuePairs.add(new BasicNameValuePair("security_session_verify","3728207e05a673fedf8998fb615a72b4"));
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
//              entity.setContentType("application/x-www-form-urlencoded");
//              创建一个post请求
                HttpPost post = new HttpPost(url);
                // 注入post数据
                post.setEntity(entity);
                post.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
                post.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                post.setHeader("Accept-Encoding","gzip, deflate");
                post.setHeader("Accept-Language","zh-CN,zh;q=0.9");
                post.setHeader("Cache-Control","max-age=0");
                post.setHeader("Connection","keep-alive");
                post.setHeader("Referer","http://www.nhc.gov.cn/zwgk/rdts/ejlist.shtml");
                post.setHeader("Host","www.nhc.gov.cn");
                post.setHeader("Upgrade-Insecure-Requests","1");
                res = httpClient.execute(post);
                if (res.getStatusLine().getStatusCode() == 200) {
                    resultString = EntityUtils.toString(res.getEntity(), format);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (res != null) {
                    res.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static boolean isOk(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
            httpGet.setConfig(requestConfig);
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200)
                return true;
        } catch (Exception e) {
            return false;
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
        return false;
    }

    
    
    public static void main(String[] args) throws Exception {
//        String url = "http://www.chinaiol.com/comm/list.aspx?no-cache=0.07266613216624473&SiteID=0&ClassID=393263781149&LoginP=CH&ListOnly=true&TemplatePath=0iunm0Ufnqmfut0ofxt%60mjtu%2fiunm";
        String url = "http://www.sasac.gov.cn/n2588025/n2643309/index.html";
//        String url = "http://www.cnipa.gov.cn/zfgg/index.htm";
//        String url = "http://www.stats.gov.cn/tjsj/jqfbyg";
        String s = doGet(url,null,"UTF-8");
        System.out.println(s);
    }
}
