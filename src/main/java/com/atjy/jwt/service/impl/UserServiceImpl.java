package com.atjy.jwt.service.impl;

import com.atjy.jwt.entity.User;
import com.atjy.jwt.mapper.UserMapper;
import com.atjy.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User login = userMapper.login(user);
        if (null != user) {
            return user;
        }
        throw new RuntimeException("登录失败");
    }
}
