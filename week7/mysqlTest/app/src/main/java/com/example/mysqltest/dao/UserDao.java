package com.example.mysqltest.dao;
import com.example.mysqltest.utils.JDBCUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;

public class UserDao {
    //查询用户的方法
    public boolean select(String table) throws Exception {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnect();
            state = (Statement) conn.createStatement();
            String sql = "select * from " + table;
            rs = state.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));//就是输出第一列和第二列的值
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, state, rs);
        }
        return true;
    }
}
