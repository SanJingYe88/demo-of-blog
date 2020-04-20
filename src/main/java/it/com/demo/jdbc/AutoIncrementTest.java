package it.com.demo.jdbc;

import java.sql.*;

/**
 * 插入数据, 获取自增长值
 */
public class AutoIncrementTest {

    /**
     * 使用Statement获取自增长值
     */
    public static void insertDataAndGetKey1() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO tb1(name,gender) values('程心','女')";
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);    // 执行 sql 语句, 同时指定返回自增长标志
            rs = stmt.getGeneratedKeys();        // 获取自增长的主键
            if (rs.next()) {
                int id = rs.getInt(1);                    // 获取自增长值
                System.out.println("插入一条记录,自增长值:" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, stmt, rs);
    }

    /**
     * 使用PrepareStatement获取自增长值
     */
    public static void insertDataAndGetKey2() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO tb1(name,gender) values(?,?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //执行 sql 预编译语句, 同时指定返回自增长标记
            pstmt.setString(1, "艾AA");                     //设置参数值
            pstmt.setString(2, "女");
            pstmt.executeUpdate();                          //发送参数, 执行SQL语句
            rs = pstmt.getGeneratedKeys();                  //获取自增长的主键
            if (rs.next()) {
                //int id = rs.getInt("id");               //报错: Column 'id' not found.
                //String id = rs.getString("id");         //报错: Column 'id' not found.
                int id = rs.getInt(1);                          // 获取自增长值
                System.out.println("插入一条记录,自增长值:" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, pstmt, rs);
    }
}
