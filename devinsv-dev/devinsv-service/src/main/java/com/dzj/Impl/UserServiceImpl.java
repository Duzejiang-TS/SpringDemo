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

    @Transactional
    @Override
    public Users queryUserForLogin(String username, String password) {
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        Users result = usersMapper.selectOne(users);
        return result;
    }
}
