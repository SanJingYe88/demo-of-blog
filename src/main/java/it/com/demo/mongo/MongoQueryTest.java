package it.com.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Slf4j
public class MongoQueryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    //根据 _id 属性, 查询文档.
    public void findById(){
        //根据 _id 属性, 查询文档.
        Comment comment = mongoTemplate.findById("1073458096153694209",Comment.class);
        log.info("{}", comment);
    }
    //查询所有文档.
    public void findAll(){
        List<Comment> list = mongoTemplate.findAll(Comment.class);
        list.forEach(x -> log.info("{}", x));
    }
    //返回满足指定查询条件的文档.
    public void find(){
        // name="李四"
        Criteria criteria1 = Criteria.where("name").is("李四");
        Query query1 = new Query(criteria1);
        List<Comment> list = mongoTemplate.find(query1,Comment.class);
        list.forEach(x -> log.info("{}", x));

        // name="李四" and age = 10
        Criteria criteria2 = Criteria.where("name").is("李四")
                .and("age").is(10);
        Query query2 = new Query(criteria2);
        List<Comment> list2 = mongoTemplate.find(query2,Comment.class);
        list2.forEach(x -> log.info("{}", x));

        // name="李四" or dept="开发部"
        Query query3 = new Query(new Criteria().orOperator(
                Criteria.where("name").is("李四"),
                Criteria.where("dept").is("开发部")));


        //跳过1条,查询1条
        Query query4 = new Query().limit(1).skip(1L);
    }
    // 分页查询
    public void pageQuery() {
        //分页查询,并按照时间排序
        int page = 1;
        int size = 5;
        //Pageable 分页对象.类似于JPA中的用法
        Pageable pageable = PageRequest.of(page - 1, size);
        Query query = new Query();
        //with() 方法可以传入 Pageable 分页对象或者传入 Sort 排序对象.
        query.with(pageable);
        //Sort 排序对象.类似于JPA中的用法
        Sort sort = new Sort(Sort.Direction.DESC, "publishdate");
        query.with(sort);
    }
    //投影查询(查询指定字段)
    public void basicQuery(){
        // BasicQuery 类的 put(key,value) 方法可以指定返回哪些字段
        // key 是字段名.
        // value：控制是否返回.  value=1 或者 true 表示返回字段, value=0 或者 false表示不返回该字段.
    }
}
