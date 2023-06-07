package com.ling.project_backend.service;

import com.ling.project_backend.domain.User;
import com.ling.project_backend.utils.Result;

public interface UserService {

    Result login(User user);

    Result register(User user);

    Result findUserSelectively(User user);

}
