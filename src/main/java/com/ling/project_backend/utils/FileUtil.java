package com.ling.project_backend.utils;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * 获得项目图片资源路径
     * @return
     */
    public static String getSuffix(){
        ApplicationHome home=new ApplicationHome();
        //项目目录绝对路径
        String suffix=home.getDir().getAbsolutePath()+"/src/main/resources/static/images";
        return suffix;
    }

    /**
     * 创建用户目录
     * @param id
     * @return
     */
    public static File createUserImageDir(Integer id){
        String suffix=new ApplicationHome().getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        File file=new File(suffix+id);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     * 删除用户上传图片
     * @param url
     * @return
     */
    public static boolean deleteUploadImage(String url){
        //http://localhost:8081/images/100000005/1685756779394.png
        String[] names=url.split("/");
        ApplicationHome home=new ApplicationHome();
        //项目目录绝对路径
        String suffix=home.getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        String filePath=suffix+names[names.length-2]+"/"+names[names.length-1];
        boolean flag=false;
        File file=new File(filePath);
        if(file.isFile()&&file.exists()){
            flag=file.delete();
        }
        return flag;
    }

    public static String uploadImage(MultipartFile file){
        if(file.isEmpty()) return "";
        String suffix=getSuffix();
        String originalName=file.getOriginalFilename();
        String[] parts=originalName.split("\\\\");
        String simpleName=parts[parts.length-1];
        String path=suffix+simpleName;
        try {
            file.transferTo(new File(suffix));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

}
