package it.com.demo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用 Statement 执行静态的 SQL 语句
 */
public class StatementTest {

    //创建数据表
    public static void createTable() {
        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        try {
            // 1.创建Statement对象
            stmt = conn.createStatement();
            // 2.准备需要执行的SQL语句, 可以先在MySQL中执行成功再复制进来.
            String sql = "CREATE TABLE tb1 (id INT PRIMARY KEY AUTO_INCREMENT, name varchar(20), gender varchar(20))";
            int count = stmt.executeUpdate(sql); // 3.执行SQL语句
            System.out.println(count + "行被影响.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);  // 4.释放资源
        }
    }

    // 插入数据
    public static void insertData() {
        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO tb1 values(1,'张三','男')";
            int count = stmt.executeUpdate(sql);
            System.out.println(count + "行被影响.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // 更新数据
    public static void updateData() {
        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE tb1 SET name = '张老三' WHERE id = 1";
            int count = stmt.executeUpdate(sql);
            System.out.println(count + "行被影响.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // 删除数据
    public static void deleteData() {
        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "DELETE FROM tb1 WHERE id = 1";
            int count = stmt.executeUpdate(sql);
            System.out.println(count + "行被影响.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    //查询数据
    public static void queryData() {
        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tb1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(id + ":" + name + ":" + gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, stmt);
    }
}
