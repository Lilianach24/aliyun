package com.example.liliana.controller;

import com.example.liliana.entity.User;
import com.example.liliana.util.DBUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//用户控制器
@Controller
public class UserController {
    //后台功能地址： 获取所有用户
    //http://127.0.0.1:8080/getUserList
    @RequestMapping("/getUserList")
    @ResponseBody
    public List<User> getUserList(HttpServletRequest request) throws Exception{
        //1. 获取链接
        Connection connection = new DBUtil().getConnection();
        //2. 执行sql语句
        ResultSet resultSet = connection.createStatement().executeQuery("select * from user");
        //3. 获取结果
        List<User> userList = new ArrayList<>();
        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            String password = resultSet.getString(4);
            User user = new User(id, name, sex, password);
            userList.add(user);
        }
        //4. 关闭数据库链接
        connection.close();
        //5. 返回结果给前端
        return userList;
    }

    //后台功能地址：通过用户的id， 获取用户信息
    //http://127.0.0.1:8080/getUserById?id=1
    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserTypeById(HttpServletRequest request) throws Exception{
        //从前端获取参数
        int id = Integer.parseInt(request.getParameter("id"));
        //1.获取链接
        Connection connection = new DBUtil().getConnection();
        //2. 执行sql语句
        ResultSet resultSet = connection.createStatement().executeQuery("select * from user where id=" + id);
        //3. 获取1个结果
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
}


