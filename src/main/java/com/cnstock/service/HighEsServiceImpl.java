package com.cnstock.service;

import com.cnstock.entity.Es;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/18.
 */
@Service
public class HighEsServiceImpl {
    public static void init(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(

                RestClient.builder(new HttpHost("sh.es.ssnews.com.cn",9200,"http"))
        );

        SearchRequest request = new SearchRequest("spidernews_index");
        request.types("spidernews_type");

        List<Es> esList = new ArrayList<>();
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = response.getHits();
        for (SearchHit hit:hits) {
            Map tempSource = hit.getSourceAsMap();
            Es es = new Es();
            es.setId(tempSource.get("id").toString());
            es.setName(tempSource.get("name").toString());
            es.setTitle(tempSource.get("title").toString());
            es.setLink(tempSource.get("link").toString());
            esList.add(es);
        }
        System.out.print(esList.size());
    }

    public static void main(String[] args) throws Exception {
//        init();
    }
}
