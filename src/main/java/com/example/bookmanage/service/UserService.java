package com.example.bookmanage.service;

import com.example.bookmanage.pojo.entity.User;
import com.example.bookmanage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }


    public User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    public void updateUserByAccount(String account, User newUser) {
        userMapper.updateUserByAccount(account,newUser);
    }


    public User login(User user) {
        return userMapper.login(user);
    }

}
