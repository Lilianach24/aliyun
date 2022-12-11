package com.example.liliana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//加法控制器
@Controller
public class AddController {
    //以post方法接受前端的响应
    @RequestMapping(value="/add", method= RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Map<String, String> map){
        //获取参数
        int a = Integer.parseInt(map.get("a"));
        int b = Integer.parseInt(map.get("b"));

        //计算
        int c = a + b;

        //结果
        return String.valueOf(c);
    }
}
