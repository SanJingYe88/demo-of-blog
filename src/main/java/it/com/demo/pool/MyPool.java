package it.com.demo.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 自定义连接池
 */
public class MyPool {
    private final int init_count = 3;    //初始化连接数
    private final int max_count = 6;    //最大连接数
    private int current_count = 0;    //当前与数据库的连接数, 这个数应该 <= 最大连接数.
    private LinkedList<Connection> poolList = new LinkedList<Connection>();    //模拟连接池, 存放所有的初始化连接

    //1.构造函数中,初始化 init_count 个连接,初始化连接放入连接池
    public MyPool() {
        for (int i = 0; i < init_count; i++) {
            current_count++;
            Connection conn = this.createConnection();
            poolList.addLast(conn);
        }
    }

    //2.创建一个新的连接的方法
    private Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/shop";
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //3.获取连接的方法
    public Connection getConnection() {
        //判断连接池中是否有连接,连接池中有连接, 就从连接池中取出
        if (poolList.size() > 0) {
            return poolList.removeFirst();
        }
        //连接池中没有连接
        //判断当前与数据库的连接是否达到最大连接数.
        if (current_count < max_count) {
            current_count++;
            return createConnection();    //创建连接
        } else {        //如果当前已经达到最大连接数,抛出异常
            throw new RuntimeException("当前连接已经达到最大连接数目 ！");
        }
    }

    //4.释放连接的方法
    public void realeaseConnection(Connection con) {
        // 判断,连接池中连接的数目如果小于初始化连接, 就把这个要释放的连接放入连接池中
        if (poolList.size() < init_count) {
            poolList.addLast(con);
        } else {        //否则,关闭
            try {
                current_count--;
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
