package com.woniuxy.service.impl;

import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.User;
import com.woniuxy.service.UserService;

public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public User findByUser(String username) {
        User user=userMapper.findByUser(username);
        return user;
    }
}
