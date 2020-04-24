package it.com.demo.mongo;

import it.com.demo.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class MongoSaveTest {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    IdWorker idWorker;

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

    // 保存
    public void save(){
        Comment comment = new Comment().set_id(idWorker.nextId() + "").setArticleid("xxxxx");
        mongoTemplate.save(comment); //不指定集合,将对象插入到要保存的对象的实体类型的集合中.

        String collectionName = "comment";
        mongoTemplate.save(comment,collectionName); //将对象插入指定的集合中. 集合可以不存在, Mongo 自动创建.
    }
    // 批量保存
    public void insert(){
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment().set_id(idWorker.nextId() + "").setArticleid("文章1").setPublishdate(new Date()));
        comments.add(new Comment().set_id(idWorker.nextId() + "").setArticleid("文章2").setPublishdate(new Date()));
        comments.add(new Comment().set_id(idWorker.nextId() + "").setArticleid("文章3").setPublishdate(new Date()));
        String collectionName = "comment";
        //插入多个. 参数1:集合. 参数2:集合的名字/实体的类型.class
        mongoTemplate.insert(comments, collectionName);
    }
}
