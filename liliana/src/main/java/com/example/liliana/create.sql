# 查看数据库
show databases;

# 删除数据库
drop database test1;

 # 创建数据库
create database test1;

 # 使用数据库
use test1;

 # 创建用户表
create table user(
    id int primary key auto_increment,
    name varchar(12),
    sex varchar(2),
    password varchar(16)
);
# 查看表
show tables;

 # 增加
insert into user values(1,'xiaoming','男','123456');
insert into user values(2,'xiaohong','女','654321');

# 查看数据
select * from user;
