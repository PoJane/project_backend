package com.ling.project_backend.controller;

import com.ling.project_backend.domain.User;
import com.ling.project_backend.service.UserDetailService;
import com.ling.project_backend.service.UserService;
import com.ling.project_backend.utils.FileUtil;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/user")
    public Result toUserPage(){
        return ResultBuilder.successResult("User Page");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }


}
