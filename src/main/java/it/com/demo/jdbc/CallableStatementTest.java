package it.com.demo.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用 CallableStatement 执行存储过程
 */
public class CallableStatementTest {

/*

====创建存储过程如下:

    CREATE PROCEDURE pro_findAll()
    BEGIN
        SELECT * FROM tb1;
    END

==== 在 MySql 中调用:

    CALL pro_findAll();

 */

    /**
     * 调用无参数的存储过程
     */
    public static void wuCanshu(){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "CALL pro_findAll()";   // SQL调用存储过程的语句.
            cstmt = conn.prepareCall(sql);       // 预编译.
            rs = cstmt.executeQuery();           // 执行存储过程
            while(rs.next()){                          // 遍历结果集
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(id + ":" + name + ":" + gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, cstmt, rs);             //释放资源
    }


/*

====创建存储过程如下:

    CREATE PROCEDURE pro_findById(IN eid INT)
    BEGIN
        SELECT * FROM tb1 WHERE id = eid;
    END

====在 MySql 中调用:

    CALL pro_findById(1);

 */

    /**
     * 调用带有输入参数的存储过程
     */
    public static void shuRuCanShu(){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "CALL pro_findById(?);";     // SQL调用存储过程
            cstmt = conn.prepareCall(sql);            // 预编译
            cstmt.setInt(1, 1);                       // 设置参数, 设置输入参数.
            rs = cstmt.executeQuery();                // 执行存储过程
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(id + ":" + name + ":" + gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, cstmt, rs);
    }


/*

====创建存储过程:

    CREATE PROCEDURE pro_findName(Out str VARCHAR(20))
    BEGIN
        SELECT name into str FROM tb1 WHERE id = 1;
    END

====在 MySql 中调用:

    CALL pro_findName(@NAME);
    SELECT @NAME;

 */

    /**
     * 调用带有输出参数的存储过程
     */
    public static void shuChuCanShu(){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "CALL pro_findName(?);";     //SQL调用存储过程
            cstmt = conn.prepareCall(sql);             //预编译
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);   //设置输出参数
            //第一个参数为索引值, 即预编译 sql 中的输出参数 ? 所在的位置.
            //第二个参数为 存储过程中的输出参数的 jdbc 类型.
            //存储过程中          jdbc用
            // varchar   java.sql.Types.VARCHAR
            // nvarchar  java.sql.Types.NVARCHAR
            // double    java.sql.Types.DOUBLE
            // int       java.sql.Types.INTEGER
            cstmt.executeQuery();      // 发送参数,
            // 注意: 执行结果不是返回到结果集中, 而是返回到输出参数中
            String result = cstmt.getString(1);        //得到输出参数的值
            //getXXX方法专门用于获取存储过程中的输出参数
            //参数为索引值, 即预编译sql中的输出参数?所在的位置.
            System.out.println("result = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, cstmt, rs);       //释放资源
    }


/*

====创建存储过程:

    CREATE PROCEDURE pro_findNameById(IN eid INT, OUT str VARCHAR(20))
    BEGIN
        SELECT name INTO str from tb1 WHERE id = eid;
    END

====调用:

    CALL pro_findNameById(1,@NAME);
    SELECT @NAME;

 */

    /**
     * 调用带有输入和输出参数的存储过程
     */
    public static void shuChuAndShuRu(){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "CALL pro_findNameById(?,?);";           //SQL调用存储过程
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, 1);                                   //设置输入参数
            cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);   //设置输出参数
            cstmt.executeQuery();                          //发送参数
            String result = cstmt.getString(2);             //得到输出参数的值
            System.out.println("result = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.close(conn, cstmt, rs);
    }
}
