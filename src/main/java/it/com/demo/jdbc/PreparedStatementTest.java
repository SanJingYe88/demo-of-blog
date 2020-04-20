package it.com.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用 PreparedStatement 执行预编译 SQL 语句
 */
public class PreparedStatementTest {

    //插入数据
    public static void insertData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            // 1.准备SQL语句.
            String sql = "INSERT INTO tb1(id,name,gender) values (?,?,?)";
            stmt = conn.prepareStatement(sql);   // 2.执行预编译SQL语句, 检查语法是否正确
            stmt.setInt(1, 1);                   // 3.设置参数值, 表示第一个?是int型的1
            stmt.setString(2, "王五");            // 表示第2个?是String型的 ’王五’
            stmt.setString(3, "男");              // 表示第3个?是String型的 ‘男’
            int num = stmt.executeUpdate();      // 4.发送参数, 执行SQL语句
            System.out.println(num + "行被影响");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, stmt, rs);              //释放资源
    }

    //修改数据
    public static void updateData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE tb1 SET name = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "李老四");
            stmt.setInt(2, 1);
            int num = stmt.executeUpdate();
            System.out.println(num + "行被影响");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, stmt, rs);
    }

    //删除数据
    public static void deleteData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM tb1 WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 2);
            int num = stmt.executeUpdate();
            System.out.println(num + "行被影响");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, stmt, rs);
    }

    //查询数据
    public static void queryData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM tb1 WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 2);                    // 设置占位符?的值为 2
            rs = stmt.executeQuery();
            while (rs.next()) {                               //循环输出
                rs.getInt("id");
                rs.getString("name");
                rs.getString("gender");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, stmt, rs);
    }
}
