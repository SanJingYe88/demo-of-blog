package it.com.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

@Slf4j
public class MongoUpdateTest {

    @Autowired
    MongoTemplate mongoTemplate;

    // 删除
    public void update() {
        Query query = new Query(Criteria.where("_id").is("1073458096153694209"));
        Update update = new Update().set("publishdate",new Date());
        //修改 _id=1073458096153694209 的记录的 publishdate 属性.
        mongoTemplate.updateFirst(query,update,"comment");
    }
}
