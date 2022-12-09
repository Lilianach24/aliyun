package com.example.liliana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//主页控制器
@Controller
public class IndexController {
    //定义网站的后端地址
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        //定义后台的输出数据
        return "hello getee!";
    }
}