package it.com.demo.jdbc;

import java.sql.*;

/***
 * 数据库连接工具类
 */
public class DBUtil {

    private static final String url = "jdbc:mysql://localhost:3306/shop?user=root&password=root";

    static {        //静态代码块 只执行一次
        try {
            //通过得到字节码对象的方式, 加载静态代码块, 从而注册驱动程序.
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败");
        }
        System.out.println("驱动加载成功");
    }

    //获取连接对象
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
        }
        return conn;
    }

    //释放资源的方法, 后使用的先释放.
    public static void close(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //释放资源的方法, 后使用的先释放.
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
