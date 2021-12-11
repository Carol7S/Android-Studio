package com.example.mysqltest2.dao;
import android.widget.Toast;

import com.example.mysqltest2.utils.DBUtils;
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
            conn = DBUtils.getConnect();
            state = (Statement) conn.createStatement();
            String sql = "select * from " + table;
            rs = state.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));//就是输出第一列和第二列的值

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, state, rs);
        }
        return true;
    }

    //新增用户的方法
    public boolean insert(String table, String username, String password) throws Exception {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        boolean res = true;
        try {
            conn = DBUtils.getConnect();
            state = (Statement) conn.createStatement();
            String sql = "insert into " + table + " (sds_username, sds_password) values (\'" + username + "\',\'"+ password + "\');";
            System.out.println(sql);
            res = state.execute(sql); //插入成功返回false
            if (!res) {
                System.out.println("插入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, state, rs);
        }
        return true;
    }

    //删除用户
    public boolean delete(String table,String username) throws Exception {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        boolean res = true;
        try {
            conn = DBUtils.getConnect();
            state = (Statement) conn.createStatement();
            //String sql = "insert into " + table + " (sds_username, sds_password) values (\'" + username + "\',\'"+ password + "\');";
            String sql = "delete from " + table + " where sds_username=\'" + username + "\';";
            System.out.println(sql);
            res = state.execute(sql); //删除成功返回false
            if (!res) {
                System.out.println("删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, state, rs);
        }
        return true;
    }

    //修改密码
    public boolean update(String table,String username,String password) throws Exception {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        boolean res = true;
        try {
            conn = DBUtils.getConnect();
            state = (Statement) conn.createStatement();
            String sql = "update "+table+" set sds_password="+"\'"+password+"\' where sds_username="+"\'"+username+"\';";
            System.out.println(sql);
            res = state.execute(sql); //删除成功返回false
            if (!res) {
                System.out.println("修改成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, state, rs);
        }
        return true;
    }
}
