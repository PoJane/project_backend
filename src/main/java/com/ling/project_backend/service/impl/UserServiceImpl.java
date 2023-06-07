package com.ling.project_backend.service.impl;

import com.ling.project_backend.domain.User;
import com.ling.project_backend.domain.UserDetail;
import com.ling.project_backend.mapper.UserDetailMapper;
import com.ling.project_backend.mapper.UserMapper;
import com.ling.project_backend.service.UserService;
import com.ling.project_backend.utils.MD5;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import com.ling.project_backend.utils.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDetailMapper userDetailMapper;

    /**
     * 登录业务
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        //密码加密
        user.setPassword(MD5.MD5Encode(user.getPassword(),"UTF-8"));
        User record=userMapper.findUserByEmailAndPassword(user);
        //用户存在，即可登录
        if(record!=null){
            //生成token
            String token= TokenUtil.sign(record);
            //登录成功，返回code、user、token信息
            return ResultBuilder.successResult(token,record);
        }
        //用户不存在，登录失败
        return ResultBuilder.failResult("用户名或密码错误!");
    }

    /**
     * 注册业务
     * @param user
     * @return
     */
    @Override
    public Result register(User user) {
        if(userMapper.findUserByEmail(user)!=null){
            return ResultBuilder.failResult("邮箱已被注册!");
        }
        //可注册账户，密码加密，添加用户
        user.setPassword(MD5.MD5Encode(user.getPassword(),"UTF-8"));
        userMapper.addUser(user);
        //获取用户信息
        User record=userMapper.findUserByEmail(user);
        //设置用户详细信息
        UserDetail detail=new UserDetail(record.getUserId());
        //插入id信息
        userDetailMapper.insertUserDetail(detail);
        return ResultBuilder.successResult(record);
    }

    /**
     * 用户个人中心
     * @param user
     * @return
     */
    public Result personal(User user){
        user.setPassword(MD5.MD5Encode(user.getPassword(),"UTF-8"));
        User record=userMapper.findUserByEmailAndPassword(user);
        List<User> list=new ArrayList<>();
        list.add(record);
        return ResultBuilder.successResult(list);
    }

    /**
     * 模糊查询用户
     * @param user
     * @return
     */
    @Override
    public Result findUserSelectively(User user) {
        List<User> records=userMapper.findUser(user);
        if(records!=null){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult("用户不存在");
    }
}
