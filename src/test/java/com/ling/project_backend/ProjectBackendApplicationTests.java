package com.ling.project_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

@SpringBootTest
class ProjectBackendApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void dateTest(){
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("GMT+8")).getTime());
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testPwd(){
        ApplicationHome home=new ApplicationHome();
        //项目目录绝对路径
        String suffix=home.getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        String path=suffix+"filename";
        System.out.println(suffix);
    }

    @Test
    public void redisGetAndSetTest(){
        //redis存数据
        ValueOperations<String,String> ops=redisTemplate.opsForValue();
        ops.set("role1","2B");
        //redis读数据
        Object o=ops.get("role1");
        System.out.println(o);
    }

    @Test
    public void setLike(){
        //redis存数据
        redisTemplate.opsForSet().add("card1","1","100000005");
        redisTemplate.opsForSet().add("card2","1");
        redisTemplate.opsForSet().add("card3","1");
        redisTemplate.opsForSet().add("card4","1");
        redisTemplate.opsForSet().add("card5","100000005");
        long count=redisTemplate.opsForSet().size("card1");
        System.out.println(count);
    }

    @Test
    public void testSplit(){
        //String file1="1.png";
        String file2="微信图片_374982739220.jpg";
        //String[] names1=file1.split("\\.");
        String[] names2=file2.split("\\.");
        //System.out.println(Arrays.toString(names1));
        System.out.println(Arrays.toString(names2));
    }

    /**
     * 删除文件测试
     */
    @Test
    public void testDeleteFile(){
        String imageUrl="http://localhost:8081/images/微信图片_20230411104022.jpg";
        String[] names=imageUrl.split("/");
        System.out.println(Arrays.toString(names));
        System.out.println(names[names.length-1]);
        ApplicationHome home=new ApplicationHome();
        //项目目录绝对路径
        String suffix=home.getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        String filePath=suffix+names[names.length-1];
        boolean flag=false;
        File file=new File(filePath);
        if(file.isFile()&&file.exists()){
            flag=file.delete();
        }
        System.out.println(flag);
    }

    @Test
    public void createDir(){
        String suffix=new ApplicationHome().getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        File file=new File(suffix+1);
        if(!file.exists()){
            file.mkdirs();
        }
        System.out.println(file.getName());
    }

    @Test
    public void testDeleteUserFile(){
        String url="http://localhost:8081/images/100000005/1685757011919.png";
        String[] names=url.split("/");
        ApplicationHome home=new ApplicationHome();
        //项目目录绝对路径
        String suffix=home.getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        String filePath=suffix+names[names.length-2]+"/"+names[names.length-1];
        System.out.println(filePath);
        boolean flag=false;
        File file=new File(filePath);
        if(file.isFile()&&file.exists()){
            flag=file.delete();
        }
        System.out.println(flag);
    }

}
