package com.itheima.service.Impl;

import com.itheima.dto.ChangePwdDto;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUserInfo(user);
    }

    @Override
    public void updateAvatar(Integer id, String avatarUrl) {
        userMapper.updateAvatar(id, avatarUrl);
    }

    @Override
    public void updatePwd(ChangePwdDto changePwdDto) {
        //比较旧密码
        String md5OldPwd = Md5Util.getMD5String(changePwdDto.getOld_pwd());
        User user = userMapper.findUserById(changePwdDto.getId());
        if (!md5OldPwd.equals(user.getPassword())) {
            throw new IllegalArgumentException("原密码不正确");
        }
        //核对新密码
        if (!changePwdDto.getNew_pwd().equals(changePwdDto.getRe_pwd())) {
           throw new IllegalArgumentException("两次输入的新密码不相同");
        }
        //对新密码MD5加密
        String md5Pwd = Md5Util.getMD5String(changePwdDto.getNew_pwd());
        userMapper.updatePwd(changePwdDto.getId(), md5Pwd);
    }
}
