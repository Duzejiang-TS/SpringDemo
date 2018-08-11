package com.example.demojpa.controller;

import com.example.demojpa.entiy.User;
import com.example.demojpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.server.InactiveGroupException;

/**
 * @author Devin13 on 2018/8/11.
 * @version 1.0
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")Integer id){
        User user = userRepository.getOne(id);
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }
}
