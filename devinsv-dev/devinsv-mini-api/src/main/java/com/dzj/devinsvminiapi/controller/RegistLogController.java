package com.dzj.devinsvminiapi.controller;

import com.dzj.UserService;
import com.dzj.pojo.Users;
import com.dzj.utils.JsonResult;
import com.dzj.utils.MD5Utils;
import com.dzj.vo.UsersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

/**
 * @author Devin13 on 2018/8/15.
 * @version 1.0
 */
@RestController
public class RegistLogController extends BasicController{

    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/regist")
    public JsonResult regist(@RequestBody Users users) throws Exception {

        if(StringUtils.isEmpty(users.getUsername()) || StringUtils.isEmpty(users.getPassword())){
            return JsonResult.errorMsg("请完善信息！");
        }

        if(userService.queryUsernameIsExist(users.getUsername())){
            return JsonResult.errorMsg("用户名已被占用！");
        }

        users.setNickname(users.getUsername());
        users.setPassword(MD5Utils.getMD5(users.getPassword()));
        users.setFansCounts(0);
        users.setFollowCounts(0);
        users.setReceiveLikeCounts(0);
        userService.saveUsers(users);

        users.setPassword("");
        UsersVo usersVo = setUserRedisSessionToken(users);

        return JsonResult.ok(usersVo);
    }

    //登录
    @PostMapping("/login")
    public JsonResult login(@RequestBody Users users) throws Exception {
        String username = users.getUsername();
        String password = users.getPassword();

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return JsonResult.ok("用户名和密码不能为空！");
        }

        Users result = userService.queryUserForLogin(username,MD5Utils.getMD5(password));

        if(result != null) {
            //密码设置为空，再传给小程序设置全局对象
            result.setPassword("");
            UsersVo usersVo = setUserRedisSessionToken(result);
            return JsonResult.ok(usersVo);
        }else{
            return JsonResult.errorMsg("用户名或密码不正确，请重试！");
        }
    }

    //注销
    @PostMapping("/logout")
    public JsonResult logout(String userId){
        redis.del(USER_REDIS_SESSION + ":" + userId);
        return JsonResult.errorMsg("注销了");
    }

    //创建UsersVo
    public UsersVo setUserRedisSessionToken(Users users){
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + users.getId(),uniqueToken,1000*60*30);

        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(users,usersVo);
        usersVo.setUserToken(uniqueToken);

        return usersVo;
    }
}
