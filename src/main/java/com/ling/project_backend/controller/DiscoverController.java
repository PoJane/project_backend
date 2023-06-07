package com.ling.project_backend.controller;

import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容浏览相关页面和操作
 */
@RestController
public class DiscoverController {

    @RequestMapping("/discover")
    public Result toDiscover(){
        return ResultBuilder.successResult("Discover Page!");
    }


}
