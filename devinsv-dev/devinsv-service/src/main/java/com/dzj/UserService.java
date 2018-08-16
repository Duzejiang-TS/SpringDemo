package com.dzj;

import com.dzj.pojo.Users;

/**
 * @author Devin13 on 2018/8/15.
 * @version 1.0
 */
public interface UserService {

    //判断是否有重复的用户名
    public boolean queryUsernameIsExist(String usernaem);

    //保存用户
    public void saveUsers(Users users);

    //登录
    public Users queryUserForLogin(String username,String password);
}
