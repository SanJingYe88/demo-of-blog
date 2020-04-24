package it.com.demo.mongo;

import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
public class MongoDeleteTest {

    @Autowired
    MongoTemplate mongoTemplate;

    // 删除
    public void remove() {
        Comment comment = new Comment().set_id("1073458096153694208");
        DeleteResult deleteResult = mongoTemplate.remove(comment);
        //删除的条数
        log.info("{}", deleteResult.getDeletedCount()); //1
        //如果写入已确认,则返回true. 也就是只要这条语句执行了,就会返回true.
        log.info("{}", deleteResult.wasAcknowledged()); //true
    }
}
