package com.itheima.service;

import com.itheima.dto.ChangePwdDto;
import com.itheima.pojo.User;


public interface UserService {
    User findByName(String username);

    void register(String username, String password);

    boolean login(String username, String password);

    User findUserById(Integer userId);

    void updateUser(User user);

    void updateAvatar(Integer id, String avatarUrl);

    void updatePwd(ChangePwdDto changePwdDto);
}
