package com.cnstock.utils;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/7.
 */
public class HtmlUtil {
    private static Logger logger = Logger.getLogger(HtmlUtil.class);


    /**
     * 解析html
     * @param content  html内容
     */
    public static List ScanHtmlA(String content, String url) throws UnsupportedEncodingException {
        Document doc = Jsoup.parse(content);
        Elements links = doc.getElementsByTag("a");
        List resultList = new ArrayList();
        for (Element link : links) {
            StringBuffer html = new StringBuffer("");
            String linkText = link.attr("title");
            String linkHref = link.attr("href");
            if(linkText==null||linkText.equals(""))
                linkText = link.text();
            String absUrl=getAbsoluteURL(url,linkHref);
            if(absUrl.equals("")){
                absUrl=linkHref;
            }
            html.append(absUrl.equals("") ? "" : absUrl).append("###").append(linkText.equals("") ? "" : linkText).append("####");
            resultList.add(html);
        }
        return resultList;
    }

    public static String getAbsoluteURL(String curl, String file){

        URL url = null;
        String q = "";
        try {
            url = new   URL(new   URL(curl),file);
            q = url.toExternalForm();
        } catch (MalformedURLException e) {
            logger.info("当前href为无效连接==="+curl + file);
        }
        if(q.indexOf("#")!=-1)q = q.replaceAll("^(. ?)#.*?$", "$1");

        return q;
    }


    /**
     * 过滤链接
     * @param href  原链接
     * @param suffix 需要包含的后缀
     * @param interiorChain 需要包含的链接
     * @return  返回新链接
     */
    public static String filtrationHtml(String href,String suffix,String interiorChain){
        if(suffix==null&&interiorChain==null){
            return "";
        }
        if(href.contains(suffix)&&href.contains(interiorChain))
            return href;
        else
            return "";
    }
}
