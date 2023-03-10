package com.example.liliana.controller;

import com.example.liliana.dao.UserDao;
import com.example.liliana.entity.User;
import com.example.liliana.util.DBUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//用户控制器
@Controller
public class UserController {

    //用户查询对象
    UserDao userDao = new UserDao();
    //用户注册
    @RequestMapping("/registration")
    @ResponseBody
    public String registration(HttpServletRequest request) throws Exception{
        //1. 获取参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String password_copy = request.getParameter("password_copy");
        String sex = request.getParameter("sex");

        //2. 注册操作
        if(name.trim().length() == 0){
            return "用户名不能为空";
        }
        if(password.trim().length() <= 6){
            return "密码长度必须大于6";
        }
        if(!password.equals(password_copy)){
            return "密码不一致";
        }

        //3. 返回结果
        if(userDao.getUserByName(name) != null){
            return "用户已经存在";
        }

        //4. 保存用户
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setPassword(password);
        userDao.saveUser(user);

        //5. 返回结果给前端
        return "注册成功！";
    }

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

    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Map<String, String> map) throws Exception{
        //获取用户名和密码
        String name = map.get("name");
        String password = map.get("password");
        //核对密码
        User user = userDao.getUserByName(name);
        if(user == null){
            return "没有这个用户";
        }
        if(!user.getPassword().equals(password)){
            return "密码错误!";
        }
        return "登陆成功";
    }
}


