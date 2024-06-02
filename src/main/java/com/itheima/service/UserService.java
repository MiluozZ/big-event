package com.itheima.service;

import com.itheima.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findByName(String username);

    void register(String username, String password);

    boolean login(String username, String password);
}
