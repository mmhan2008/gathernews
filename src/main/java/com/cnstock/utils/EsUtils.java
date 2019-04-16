package com.cnstock.utils;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * Created by Administrator on 2019/1/16.
 */
public class EsUtils {

    @Value(value = "${com.cnstock.esport}")
    private static int PORT;
    @Value(value = "${com.cnstock.eshost}")
    private static String HOST;

    private static TransportClient client;

    public static TransportClient getInstance(){
        if(client==null){
            try {
                client = getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public static TransportClient getConnection() throws Exception {
        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "cnstock-es-sh").put("client.transport.sniff", true).build();
        //创建client
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("sh.es.ssnews.com.cn"), 9300));//集群ip

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return client;

    }

    public static void insert(TransportClient client) throws Exception {
        Map<String,Object> resource = new HashMap<>();
        resource.put("name","mac Note");
        resource.put("price",8877);
        resource.put("description","mac Note 新款");
        IndexRequestBuilder index = client.prepareIndex("spidernews", "spidernews");

        IndexResponse insertResponse = index.setSource(resource).execute().get();

        System.out.println(insertResponse);
    }

    public static void createStudentIndex(TransportClient client, String indexName) {
        CreateIndexRequestBuilder cib = client.admin().indices().prepareCreate(indexName);
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    .startObject()//表示开始设置值
                    .startObject("properties")//设置只定义字段，不传参
                    .startObject("no") //定义字段名
                    .field("type", "text") //设置数据类型
                    .endObject()
                    .startObject("name")
                    .field("type", "text")
                    .endObject()
                    .startObject("price")
                    .field("type", "integer")
                    .endObject()
                    .startObject("description")
                    .field("type", "text")
                    .endObject()
                    .endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cib.addMapping("spidernews_test", mapping);
        cib.execute().actionGet();

    }


    public static void main(String[] args) throws Exception {
        TransportClient client = getConnection();
//        createStudentIndex(client,"spidernews_test");
//        insert(client);
//        System.out.println("client==" + client.toString());
        IndexResponse response = client.prepareIndex("spidernews_test", "spidernews", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
    }

}
