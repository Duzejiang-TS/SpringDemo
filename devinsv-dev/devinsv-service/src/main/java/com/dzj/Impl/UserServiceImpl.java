package com.dzj.Impl;

import com.dzj.UserService;
import com.dzj.mapper.UsersMapper;
import com.dzj.pojo.Users;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Devin13 on 2018/8/15.
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users users = new Users();
        users.setUsername(username);
        Users result = usersMapper.selectOne(users);
        if(result == null)
            return false;
        else
            return true;
    }

    @Transactional
    @Override
    public void saveUsers(Users users) {
        String userId = sid.nextShort();
        users.setId(userId);
        usersMapper.insert(users);
    }

    @Override
    public Users queryUserForLogin(String username, String password) {
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        Users result = usersMapper.selectOne(users);
        return result;
    }

    @Transactional
    @Override
    public void updateUserInfo(Users users) {
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public Users queryUserInfo(String userId) {
        Users users =  usersMapper.selectByPrimaryKey(userId);
        return users;
    }
}
