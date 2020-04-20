package it.com.demo.jdbc;

import java.sql.*;

/**
 * 元数据
 */
public class MetaDataTest {

    /**
     * 数据库元数据
     * @throws Exception
     */
    public void testDB() throws Exception {
        Connection conn = DBUtil.getConnection();
        // 获取数据库元数据
        DatabaseMetaData metaData = conn.getMetaData();
        String url = metaData.getURL();                          //jdbc:mysql://localhost:3306/CONTACT_WEB
        String name = metaData.getUserName();                   //root@localhost
        String pname = metaData.getDatabaseProductName();        //MySQL
    }

    /**
     * 参数元数据
     * @throws Exception
     */
    public void testParams() throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from dept where deptid=? and deptName=?";
        // Object[] values = {"tom","888"};
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 参数元数据
        ParameterMetaData p_metaDate = pstmt.getParameterMetaData();
        // 获取参数的个数, ? 的个数
        int count = p_metaDate.getParameterCount();
        System.out.println(count);
    }

    /**
     * 结果集元数据
     * @throws Exception
     */
    public void testRs() throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from contact_gp where id=? and name=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, 1);
        pstmt.setString(2, "亲友");
        ResultSet rs = pstmt.executeQuery();
        // 得到结果集元数据 (目标：通过结果集元数据, 得到列的名称)
        ResultSetMetaData rs_metaData = rs.getMetaData();
        while (rs.next()) {
            // 1. 获取列的个数
            int count = rs_metaData.getColumnCount();
            // 2. 遍历, 获取每一列的列的名称, 从1开始
            for (int i = 1; i <= count; i++) {
                // 得到列的名称
                String cName = rs_metaData.getColumnName(i);
                // 获取每一行的每一列的值
                Object cValue = rs.getObject(cName);
                System.out.print(cName + "=" + cValue + ",");
            }
            System.out.println();
        }
    }
}
