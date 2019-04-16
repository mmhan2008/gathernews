package com.cnstock.utils;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.util.Collections;

/**
 * Created by Administrator on 2019/1/16.
 */
public class RestClientUtils {

    static RestClient restClient = RestClient.builder(
            new HttpHost("61.152.201.50", 9200, "http")).build();


    public static void main(String[] args) throws Exception {


        Response response = restClient.performRequest("HEAD","/spidernews_test",Collections.<String, String>emptyMap());
        System.out.println(response.getStatusLine().getReasonPhrase().equals("OK"));

    }
}
