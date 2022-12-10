package com.example.liliana.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//利用JDBC技术连接到数据库工具类
public class DBUtil {
    //Mysql服务地址
    public static String url="jdbc:mysql://127.0.0.1:3306/test1";
    //Mysql账号
    public static String username = "root";

    //Mysql密码
    public static String password = "375520cl";

    //辅助方法：获取链接
    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    //测试方法
    public static void main(String[] args) throws Exception {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //链接到数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("连接到数据库成功!");
            //发送sql语句
            ResultSet resultSet = connection.createStatement().executeQuery("select * from user");
            //获取结果
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String sex = resultSet.getString(3);
                String password = resultSet.getString(4);
                System.out.println(id + " " + name + " " + sex + " " + password);
            }
            //关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
