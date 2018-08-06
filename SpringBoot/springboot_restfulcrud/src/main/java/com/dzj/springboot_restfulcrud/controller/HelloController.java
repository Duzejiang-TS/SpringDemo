package com.dzj.springboot_restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @author Devin13 on 2018/8/6.
 * @version 1.0
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("success")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        return "success";
    }
}
