package com.example.demojdbc.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Devin13 on 2018/8/9.
 * @version 1.0
 */
@Controller
public class HelloController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    @ResponseBody
    public Map<String,Object> map() {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from department");
        return list.get(0);
    }
 }
