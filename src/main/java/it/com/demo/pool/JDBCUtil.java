package it.com.demo.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * C3P0 连接池的工具类
 */
public class JDBCUtil {

    // 1.初始化C3P0连接池
    private static ComboPooledDataSource dataSource;

    static{
        dataSource = new ComboPooledDataSource();
    }

    // 2.创建 DbUtils 核心工具类对象
    public static QueryRunner getQueryRunner(){
        // 创建 QueryRunner 对象, 传入连接池对象
        // 在创建 QueryRunner 对象的时候, 如果传入了数据源对象；
        // 那么在使用 QueryRunner 对象方法的时候, 就不需要传入连接对象；
        // 会自动从数据源中获取连接(不用关闭连接)
        QueryRunner qr = new QueryRunner(dataSource);
        return qr;
    }
}
