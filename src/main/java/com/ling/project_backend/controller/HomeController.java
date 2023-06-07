package com.ling.project_backend.controller;

import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public Result home(){
        return ResultBuilder.successResult("Hello!");
    }

}
