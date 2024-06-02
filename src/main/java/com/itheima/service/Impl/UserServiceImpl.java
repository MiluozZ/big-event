package com.itheima.service.Impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByName(String username) {

        return userMapper.findByName(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5Pwd = Md5Util.getMD5String(password);
        userMapper.add(username, md5Pwd);
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}
