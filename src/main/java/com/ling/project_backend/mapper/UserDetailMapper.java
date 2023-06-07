package com.ling.project_backend.mapper;

import com.ling.project_backend.domain.UserDetail;
import com.ling.project_backend.service.UserDetailService;

import java.util.List;

/**
 * 用户详细信息操作
 */
public interface UserDetailMapper {

    UserDetail findUserDetailById(Integer id);

    List<UserDetail> findUserDetail(UserDetail userDetail);

    //添加用户信息
    int insertUserDetail(UserDetail userDetail);

    //更新用户信息
    int updateUserDetail(UserDetail userDetail);

}
