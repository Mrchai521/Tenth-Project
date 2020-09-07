package com.tensquare;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/7
 */
public class MongoTest {
    public static void main(String[] args) {
        // 连接mongo服务器
        MongoClient mongoClient = new MongoClient("121.36.25.190", 27017);

        // 获取要操作的数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("spitdb");
        //得到要操作的集合
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("spit");
       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", "9");
        map.put("content", "测试mongo的添加");
        map.put("userid", "1");
        map.put("visits", "5000");
        Document documentMap = new Document(map);
        //spit为操作的集合,相当于操作的表
        mongoCollection.insertOne(documentMap);*/
        //得到集合中的所有文档
        FindIterable<Document> findIterable = mongoCollection.find();
        //遍历
        for (Document document : findIterable) {
            System.out.println("内容为：" + document.getString("content"));
            System.out.println("用户id为：" + document.getString("userid"));
            System.out.println("访问量  :" + document.getInteger("visits"));
            System.out.println("****************************************************");
        }
        mongoClient.close();
    }
}
