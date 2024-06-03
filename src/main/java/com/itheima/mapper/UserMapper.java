package com.itheima.mapper;

import com.itheima.dto.ChangePwdDto;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where username= #{username}")
    User findByName(@Param("username") String username);

    @Insert("insert into user(username, password, create_time, update_time) values (#{username}, #{password}, now(), now())")
    void add(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer userId);

    @Update("update user set nickname = #{nickname} , email = #{email}, update_time = #{updateTime} where id = #{id}")
    void updateUserInfo(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    void updateAvatar(Integer id, String avatarUrl);

    @Update("update user set password = #{password} , update_time = now() where id = #{id}")
    void updatePwd(Integer id, String password);
}
