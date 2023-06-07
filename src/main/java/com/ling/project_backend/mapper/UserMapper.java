package com.ling.project_backend.mapper;

import com.ling.project_backend.domain.User;

import java.util.List;

public interface UserMapper {
    //用于登录/注册
    User findUserByEmailAndPassword(User user);

    //根据邮箱查找用户
    User findUserByEmail(User user);

    //用户模糊查询
    List<User> findUser(User user);

    int addUser(User user);

}
