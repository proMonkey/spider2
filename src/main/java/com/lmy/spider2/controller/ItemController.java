package com.lmy.spider2.controller;

import com.alibaba.fastjson.JSON;
import com.lmy.spider2.service.ESQuery;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


/**
 * @author lmy
 * @date 2019-12-01 11:48:02
 * @description 使用ES的查询
 */
@RestController
@RequestMapping("/shopping")
public class ItemController {
    @Autowired
    private RestClient client;


    /**
     * 获取ES对象列表
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/searchItems")
    public ResponseEntity<Object> searchItems(@RequestParam("keyword") String keyword,@RequestParam("mall") String mall) {
        StringBuilder sortUrl = new StringBuilder("phones/_analyze");
        StringBuilder sortRequestBody = new StringBuilder();
        ESQuery sortESQuery = new ESQuery();
        String sortDsl = sortESQuery.analyzeKeyword(keyword);
        sortRequestBody.append(sortDsl);

        String result = sortESQuery.connect(sortUrl, sortRequestBody, client, "GET", "tokens").toString();
        String sort;
        if (result.contains("手机")) {
            sort = "phones/_search";
        } else if (result.contains("平板")) {
            sort = "tablets/_search";
        } else if (result.contains("相机")) {
            sort = "cameras/_search";
        } else if (result.contains("电视")) {
            sort = "televisions/_search";
        } else {
            sort = "_search";
        }

        StringBuilder url = new StringBuilder(sort);

        StringBuilder requestBody = new StringBuilder();
        ESQuery esQuery = new ESQuery();
        String dsl = esQuery.normalQuery(keyword, mall);
        requestBody.append(dsl);

        return esQuery.connect(url, requestBody, client, "POST", "hits");
    }

    @GetMapping("/searchItemsOrderByUpPrice")
    public ResponseEntity<Object> searchItemsOrderByUpPrice(@RequestParam("keyword") String keyword,@RequestParam("mall") String mall) {
        StringBuilder sortUrl = new StringBuilder("phones/_analyze");
        StringBuilder sortRequestBody = new StringBuilder();
        ESQuery sortESQuery = new ESQuery();
        String sortDsl = sortESQuery.analyzeKeyword(keyword);
        sortRequestBody.append(sortDsl);

        String result = sortESQuery.connect(sortUrl, sortRequestBody, client, "GET", "tokens").toString();
        String sort;
        if (result.contains("手机")) {
            sort = "phones/_search";
        } else if (result.contains("平板")) {
            sort = "tablets/_search";
        } else if (result.contains("相机")) {
            sort = "cameras/_search";
        } else if (result.contains("电视")) {
            sort = "televisions/_search";
        } else {
            sort = "_search";
        }

        StringBuilder url = new StringBuilder(sort);

        StringBuilder requestBody = new StringBuilder();
        ESQuery esQuery = new ESQuery();
        String dsl = esQuery.queryOrderByUpPrice(keyword, mall);
        requestBody.append(dsl);

        return esQuery.connect(url, requestBody, client, "POST", "hits");
    }

    @GetMapping("/searchItemsOrderByDownPrice")
    public ResponseEntity<Object> searchItemsOrderByDownPrice(@RequestParam("keyword") String keyword, @RequestParam("mall") String mall) {
        StringBuilder sortUrl = new StringBuilder("phones/_analyze");
        StringBuilder sortRequestBody = new StringBuilder();
        ESQuery sortESQuery = new ESQuery();
        String sortDsl = sortESQuery.analyzeKeyword(keyword);
        sortRequestBody.append(sortDsl);

        String result = sortESQuery.connect(sortUrl, sortRequestBody, client, "GET", "tokens").toString();
        String sort;
        if (result.contains("手机")) {
            sort = "phones/_search";
        } else if (result.contains("平板")) {
            sort = "tablets/_search";
        } else if (result.contains("相机")) {
            sort = "cameras/_search";
        } else if (result.contains("电视")) {
            sort = "televisions/_search";
        } else {
            sort = "_search";
        }

        StringBuilder url = new StringBuilder(sort);

        StringBuilder requestBody = new StringBuilder();
        ESQuery esQuery = new ESQuery();
        String dsl = esQuery.queryOrderByDownPrice(keyword, mall);
        requestBody.append(dsl);

        return esQuery.connect(url, requestBody, client, "POST", "hits");
    }

    @GetMapping("/searchItemsOrderBySells")
    public ResponseEntity<Object> searchItemsOrderBySells(@RequestParam("keyword") String keyword,@RequestParam("mall") String mall) {
        StringBuilder sortUrl = new StringBuilder("phones/_analyze");
        StringBuilder sortRequestBody = new StringBuilder();
        ESQuery sortESQuery = new ESQuery();
        String sortDsl = sortESQuery.analyzeKeyword(keyword);
        sortRequestBody.append(sortDsl);

        String result = sortESQuery.connect(sortUrl, sortRequestBody, client, "GET", "tokens").toString();
        String sort;
        if (result.contains("手机")) {
            sort = "phones/_search";
        } else if (result.contains("平板")) {
            sort = "tablets/_search";
        } else if (result.contains("相机")) {
            sort = "cameras/_search";
        } else if (result.contains("电视")) {
            sort = "televisions/_search";
        } else {
            sort = "_search";
        }

        StringBuilder url = new StringBuilder(sort);

        StringBuilder requestBody = new StringBuilder();
        ESQuery esQuery = new ESQuery();
        String dsl = esQuery.queryOrderBySells(keyword, mall);
        requestBody.append(dsl);

        return esQuery.connect(url, requestBody, client, "POST", "hits");
    }


}
