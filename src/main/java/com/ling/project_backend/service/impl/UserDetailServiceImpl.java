package com.ling.project_backend.service.impl;

import com.ling.project_backend.domain.UserDetail;
import com.ling.project_backend.mapper.UserDetailMapper;
import com.ling.project_backend.service.UserDetailService;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Resource
    UserDetailMapper userDetailMapper;

    @Override
    public Result showUserDetail(Integer id) {
        if(userDetailMapper.findUserDetailById(id)!=null){
            return ResultBuilder.successResult(userDetailMapper.findUserDetailById(id));
        }
        return ResultBuilder.failResult("用户不存在...");
    }

    //用户模糊查询
    @Override
    public Result selectUser(UserDetail user) {
        List<UserDetail> records=userDetailMapper.findUserDetail(user);
        if(records!=null&&!records.isEmpty()){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult("用户不存在...");
    }

    //添加用户详情信息
    @Override
    public Result addUserDetail(UserDetail userDetail) {
        int r=userDetailMapper.insertUserDetail(userDetail);
        if(r>0){
            return ResultBuilder.successResult(r);
        }
        return ResultBuilder.failResult("信息提交失败!");
    }

    //选择性更改用户信息
    @Override
    public Result updateUserDetail(UserDetail userDetail) {
        int r=userDetailMapper.updateUserDetail(userDetail);
        if(r>0){
            return ResultBuilder.successResult(r);
        }
        return ResultBuilder.failResult("信息修改失败!");
    }
}
