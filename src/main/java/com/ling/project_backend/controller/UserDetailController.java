package com.ling.project_backend.controller;

import com.ling.project_backend.common.Url;
import com.ling.project_backend.domain.UserDetail;
import com.ling.project_backend.service.UserDetailService;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UserDetailController {

    @Resource
    UserDetailService userDetailService;

    @RequestMapping("/show_user")
    public Result showUserDetail(@RequestParam Integer id){
        return userDetailService.showUserDetail(id);
    }

    @PostMapping("/select_user")
    public Result selectUserDetail(@RequestBody UserDetail userDetail){
        return userDetailService.selectUser(userDetail);
    }

    /**
     * 添加用户详细信息（注册时自动调用）
     * @param userDetail
     * @return
     */
    @PostMapping("/add_detail")
    public Result addUserDetail(@RequestBody UserDetail userDetail){
        return userDetailService.addUserDetail(userDetail);
    }

    @PostMapping("/update_detail")
    public Result updateUserDetail(@RequestBody UserDetail userDetail){
        return userDetailService.updateUserDetail(userDetail);
    }


    /**
     * 上传头像
     * @param file
     * @return
     */
    @PostMapping("/upload_avatar")
    public Result uploadAvatar(@RequestBody MultipartFile file) throws IOException {
        try{
            if(!file.isEmpty()){
                //获取原始文件名
                String originalFileName=file.getOriginalFilename();
                String[] names=originalFileName.split("\\.");
                System.out.println(names[1]);
                //重命名--根据系统时间
                long curMillis=System.currentTimeMillis();
                //文件转存, 存到本项目static/images文件夹下
                String suffix=new ApplicationHome().getDir().getAbsolutePath()+"/src/main/resources/static/images/";
                String path=suffix+curMillis+"."+names[1];
                //文件转存
                file.transferTo(new File(path));
                return ResultBuilder.successResult(Url.STATIC_URL+curMillis+"."+names[1]);
            }else{
                System.out.println("null image");
                return ResultBuilder.failResult("上传头像为空!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultBuilder.failResult("头像上传失败!");
        }
    }

    /**
     * 删除头像
     * @param imageUrl 头像url
     * @return
     */
    @GetMapping("/delete_avatar")
    public Result deleteAvatar(@RequestParam String imageUrl){
        // http://localhost:8081/images/xxxxx.xx
        String[] names=imageUrl.split("/");
        ApplicationHome home=new ApplicationHome();
        //项目目录绝对路径
        String suffix=home.getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        String filePath=suffix+names[names.length-1];
        boolean flag=false;
        File file=new File(filePath);
        if(file.isFile()&&file.exists()){
            flag=file.delete();
        }
        if(flag){
            return ResultBuilder.successResult("已删除原头像");
        }else{
            return ResultBuilder.failResult("删除失败");
        }
    }

}
