package com.example.liliana.dao;

import com.example.liliana.entity.User;
import com.example.liliana.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;

//用户数据库访问功能
public class UserDao {
    //获取一个用户
    public User getUser(int id) throws Exception{
        //1. 获取连接
        Connection connection = new DBUtil().getConnection();

        //2. 执行sql语句
        ResultSet resultSet = connection.createStatement().executeQuery("select * from user where id=" + id);

        //3. 获取一个结果
        resultSet.next();

        //4. 获取数据
        String name = resultSet.getString(2);
        String sex = resultSet.getString(3);
        String password = resultSet.getString(4);

        //5. 数据封装成对象
        User user = new User(id, name, sex, password);

        //6. 返回数据到前端显示
        return user;
    }

    //按用户名查询用户
    public User getUserByName(String name) throws Exception{
        //1. 获取连接
        Connection connection = new DBUtil().getConnection();

        //2. 执行sql语句
        String sql = "select * from user where name='%s'";
        ResultSet resultSet = connection.createStatement().executeQuery(String.format(sql, name));

        //3. 获取1个结果
        if(!resultSet.next()){
            return null;
        }

        //4. 获取数据
        int id = resultSet.getInt(1);
        String sex = resultSet.getString(3);
        String password = resultSet.getString(4);

        //5. 数据封装成对象
        User user = new User(id, name, sex, password);

        //6. 返回数据到前端显示
        return user;
    }

    //增加一个用户
    public void saveUser(User user) throws Exception{
        Connection connection = new DBUtil().getConnection();
        String sql = String.format("insert into user values(null, '%s', '%s', '%s'",
                user.getName(), user.getSex(), user.getPassword());
        connection.createStatement().executeUpdate(sql);
    }

    //修改一个用户
    public void updateUser(User user) throws Exception{
        Connection connection = new DBUtil().getConnection();
        String sql = String.format("update user set name='%s', sex='%s', password='%s' where id='%d'",
                user.getName(), user.getSex(), user.getPassword(), user.getId());
        connection.createStatement().executeUpdate(sql);
    }

    //删除一个用户
    public void deleteUser(User user) throws Exception{
        Connection connection = new DBUtil().getConnection();
        String sql = String.format("delete from user where id='%d'", user.getId());
        connection.createStatement().executeUpdate(sql);
    }
}
