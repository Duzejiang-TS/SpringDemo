package com.dzj.devinsvminiapi.controller;

import com.dzj.UserService;
import com.dzj.pojo.Users;
import com.dzj.utils.JsonResult;
import com.dzj.utils.MD5Utils;
import com.dzj.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

/**
 * @author Devin13 on 2018/8/15.
 * @version 1.0
 */
@RestController
public class BasicController {

    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION = "user-redis-session";
}
