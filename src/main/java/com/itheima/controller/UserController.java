package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.vo.UserRegistrationRequest;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$", message = "用户名需要为5-16位非空字符") String username,@Pattern(regexp = "^\\S{5,16}$", message = "密码需要为5-16位非空字符") String password) {
        User user = userService.findByName(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success("注册成功");
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$", message = "用户名需要为5-16位非空字符") String username,@Pattern(regexp = "^\\S{5,16}$", message = "密码需要为5-16位非空字符") String password) {
        User user = userService.findByName(username);
        if (user == null) {
            return Result.error("当前用户不存在");
        }
        if (!user.getPassword().equals(Md5Util.getMD5String(password))) {
            return Result.error("用户密码错误");
        }
        Map<String, Object> claimsByUser = JwtUtil.getClaimsByUser(user);
        String jwtToken = JwtUtil.genToken(claimsByUser);
        return Result.success(jwtToken);
    }

}
