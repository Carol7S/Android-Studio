package com.example.mysqltest.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;

public class JDBCUtils {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://192.168.1.100:3306/journey_reform?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private final static String username = "root";
    private final static String password = "123456";

    Connection conn=null;
    Statement st=null;
    ResultSet rs=null;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误");
        }
    }


    //2. 获取连接
    public static Connection getConnect() throws Exception {
        return (Connection) DriverManager.getConnection(url, username, password);
    }

    //3. 释放连接资源

    public static void release(Connection conn, Statement st, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }

    }
}
