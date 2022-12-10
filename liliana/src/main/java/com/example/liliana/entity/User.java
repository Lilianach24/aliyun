package com.example.liliana.entity;

//用户实体类（映射到数据库的用户表）
public class User {
    //编号
    int id;
    //名字
    String name;
    //性别
    String sex;
    //密码
    String password;

    public User() {
    }

    public User(int id, String name, String sex, String password) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
