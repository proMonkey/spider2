package com.lmy.spider2.service;

import com.alibaba.fastjson.JSON;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public class ESQuery {

    public ResponseEntity<Object> connect(StringBuilder url, StringBuilder requestBody, RestClient client, String method, String target) {
        // 构造HTTP请求
        Request request = new Request(method, url.toString());
        // 添加json返回优化
        request.addParameter("pretty", "true");

        request.setEntity(new NStringEntity(requestBody.toString(), ContentType.APPLICATION_JSON));

        Response response = null;
        String responseBody = null;
        Object result = null;
        try {
            // 执行HTTP请求
            response = client.performRequest(request);
            // 获取返回的内容
            responseBody = EntityUtils.toString(response.getEntity());
            Map jsonObject = JSON.parseObject(responseBody);
            result = jsonObject.get(target);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("can not found the item by your keyword", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public String analyzeKeyword(String keyword) {
        String analyzeKeywordDsl = "{\n" +
                "  \"field\": \"itemname\",\n" +
                "  \"text\": [\"" + keyword + "\"]\n" +
                "}";
        return analyzeKeywordDsl;
    }

    public String normalQuery(String keyword, String mall) {
        String normalDsl = "{\n" +
                "    \"size\": 50,\n" +
                "    \"from\": 0,\n" +
                "    \"query\": {\n" +
                "        \"bool\": {\n" +
                "            \"filter\": [\n" +
                "                {\n" +
                "                    \"range\": {\n" +
                "                        \"itemprice\": {\n" +
                "                            \"gte\": 800\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"terms\": {\n" +
                "                        \"itemorigin.keyword\": [" + mall + "]\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"should\": {\n" +
                "                \"match_phrase\": {\n" +
                "                    \"itemname\": {\n" +
                "                        \"query\": \"" + keyword + "\",\n" +
                "                        \"slop\": 10\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            \"must_not\": {\n" +
                "                \"match\": {\n" +
                "                    \"itemname\": \"壳\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"_source\": [\n" +
                "        \"itemname\",\n" +
                "        \"itemid\",\n" +
                "        \"itemurl\",\n" +
                "        \"itemimg\",\n" +
                "        \"itemprice\",\n" +
                "        \"itemshop\",\n" +
                "        \"itemcommentnum\",\n" +
                "        \"itemselfshop\",\n" +
                "        \"itemreduction\",\n" +
                "        \"itemcoupon\",\n" +
                "        \"itemorigin\"\n" +
                "    ],\n" +
                "    \"sort\": [\n" +
                "        {\n" +
                "            \"_score\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemprice\": {\n" +
                "                \"order\": \"asc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemcommentnum.keyword\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "\"min_score\": 0.5"+
                "}";
        return normalDsl;
    }

    public String queryOrderByUpPrice(String keyword, String mall) {
        String orderByPriceDsl = "{\n" +
                "    \"size\": 50,\n" +
                "    \"from\": 0,\n" +
                "    \"query\": {\n" +
                "        \"bool\": {\n" +
                "            \"filter\": [\n" +
                "                {\n" +
                "                    \"range\": {\n" +
                "                        \"itemprice\": {\n" +
                "                            \"gte\": 800\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"terms\": {\n" +
                "                        \"itemorigin.keyword\": [" + mall + "]\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"should\": {\n" +
                "                \"match_phrase\": {\n" +
                "                    \"itemname\": {\n" +
                "                        \"query\": \"" + keyword + "\",\n" +
                "                        \"slop\": 10\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            \"must_not\": {\n" +
                "                \"match\": {\n" +
                "                    \"itemname\": \"壳\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"_source\": [\n" +
                "        \"itemname\",\n" +
                "        \"itemid\",\n" +
                "        \"itemurl\",\n" +
                "        \"itemimg\",\n" +
                "        \"itemprice\",\n" +
                "        \"itemshop\",\n" +
                "        \"itemcommentnum\",\n" +
                "        \"itemselfshop\",\n" +
                "        \"itemreduction\",\n" +
                "        \"itemcoupon\",\n" +
                "        \"itemorigin\"\n" +
                "    ],\n" +
                "    \"sort\": [\n" +
                "        {\n" +
                "            \"itemprice\": {\n" +
                "                \"order\": \"asc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"_score\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemcommentnum.keyword\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "\"min_score\": 0.5"+
                "}";
        return orderByPriceDsl;
    }

    public String queryOrderByDownPrice(String keyword, String mall) {
        String orderByPriceDsl = "{\n" +
                "    \"size\": 50,\n" +
                "    \"from\": 0,\n" +
                "    \"query\": {\n" +
                "        \"bool\": {\n" +
                "            \"filter\": [\n" +
                "                {\n" +
                "                    \"range\": {\n" +
                "                        \"itemprice\": {\n" +
                "                            \"gte\": 800\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"terms\": {\n" +
                "                        \"itemorigin.keyword\": [" + mall + "]\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"should\": {\n" +
                "                \"match_phrase\": {\n" +
                "                    \"itemname\": {\n" +
                "                        \"query\": \"" + keyword + "\",\n" +
                "                        \"slop\": 10\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            \"must_not\": {\n" +
                "                \"match\": {\n" +
                "                    \"itemname\": \"壳\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"_source\": [\n" +
                "        \"itemname\",\n" +
                "        \"itemid\",\n" +
                "        \"itemurl\",\n" +
                "        \"itemimg\",\n" +
                "        \"itemprice\",\n" +
                "        \"itemshop\",\n" +
                "        \"itemcommentnum\",\n" +
                "        \"itemselfshop\",\n" +
                "        \"itemreduction\",\n" +
                "        \"itemcoupon\",\n" +
                "        \"itemorigin\"\n" +
                "    ],\n" +
                "    \"sort\": [\n" +
                "        {\n" +
                "            \"itemprice\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"_score\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemcommentnum.keyword\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "\"min_score\": 0.5"+
                "}";
        return orderByPriceDsl;
    }

    public String queryOrderBySells(String keyword, String mall) {
        String orderBySellsDsl = "{\n" +
                "    \"size\": 50,\n" +
                "    \"from\": 0,\n" +
                "    \"query\": {\n" +
                "        \"bool\": {\n" +
                "            \"filter\": [\n" +
                "                {\n" +
                "                    \"range\": {\n" +
                "                        \"itemprice\": {\n" +
                "                            \"gte\": 800\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"terms\": {\n" +
                "                        \"itemorigin.keyword\": [" + mall + "]\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"should\": {\n" +
                "                \"match_phrase\": {\n" +
                "                    \"itemname\": {\n" +
                "                        \"query\": \"" + keyword + "\",\n" +
                "                        \"slop\": 10\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            \"must_not\": {\n" +
                "                \"match\": {\n" +
                "                    \"itemname\": \"壳\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"_source\": [\n" +
                "        \"itemname\",\n" +
                "        \"itemid\",\n" +
                "        \"itemurl\",\n" +
                "        \"itemimg\",\n" +
                "        \"itemprice\",\n" +
                "        \"itemshop\",\n" +
                "        \"itemcommentnum\",\n" +
                "        \"itemselfshop\",\n" +
                "        \"itemreduction\",\n" +
                "        \"itemcoupon\",\n" +
                "        \"itemorigin\"\n" +
                "    ],\n" +
                "    \"sort\": [\n" +
                "        {\n" +
                "            \"itemcommentnum.keyword\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemprice\": {\n" +
                "                \"order\": \"asc\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"_score\": {\n" +
                "                \"order\": \"desc\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "\"min_score\": 0.5"+
                "}";
        return orderBySellsDsl;
    }


}
