package com.bplow.todo.sample.dmo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
	 public static void main(String[] args) {
	        String driver = "com.mysql.jdbc.Driver";
	        String dbName = "newcms";
	        String passwrod = "123456";
	        String userName = "root";
	        String url = "jdbc:mysql://192.168.0.100:3306/test";
	        String sql = "select * from user";
	        Connection conn =null;
	        try {
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url, userName,
	                    passwrod);
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                System.out.println("id : " + rs.getInt(1) + " name : "
	                        + rs.getString(2) + " password : " + rs.getString(3));
	            }
	 
	            // 关闭记录集
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	 
	            // 关闭声明
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	 
	            // 关闭链接对象
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	        	try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	    }
	 

}
