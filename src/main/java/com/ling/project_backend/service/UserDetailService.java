package com.ling.project_backend.service;

import com.ling.project_backend.domain.UserDetail;
import com.ling.project_backend.utils.Result;

public interface UserDetailService {

    Result showUserDetail(Integer id);

    //用户模糊查询
    Result selectUser(UserDetail userDetail);

    Result addUserDetail(UserDetail userDetail);

    Result updateUserDetail(UserDetail userDetail);

}
