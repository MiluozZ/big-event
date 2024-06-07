package com.itheima.interceptors;

import com.itheima.pojo.User;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (!StringUtils.isNullOrEmpty(token)) {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            User user = new User();
            user.setUsername((String) claims.get("username"));
            user.setId((Integer) claims.get("id"));
            ThreadLocalUtil.set(user);
            String redisToken = stringRedisTemplate.opsForValue().get(user.getId().toString());
            if (StringUtils.isNullOrEmpty(redisToken) || !token.equals(redisToken)) {
                throw new IllegalArgumentException("当前用户会话已失效，请重新登陆");
            }
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       ThreadLocalUtil.remove();
    }
}
