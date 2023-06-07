package com.ling.project_backend.controller;

import com.ling.project_backend.common.Url;
import com.ling.project_backend.domain.Card;
import com.ling.project_backend.service.CardService;
import com.ling.project_backend.utils.FileUtil;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class CardController {

    @Resource
    private CardService cardService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/create_card")
    public Result createCard(@RequestBody Card card){
        return cardService.createCard(card);
    }

    @PostMapping("/delete_card")
    public Result deleteCard(@RequestBody Card card){
        return cardService.deleteCard(card);
    }

    @PostMapping("/change_card")
    public Result changeCard(@RequestBody Card card){
        return cardService.changeCard(card);
    }

    @PostMapping("/search_card")
    public Result searchCards(@RequestBody Card card){
        return cardService.searchCards(card);
    }

    /**
     * 获取表中卡片数目
     * @return
     */
    @GetMapping("/get_count")
    public Result getCardCount(){
        return cardService.getCardCount();
    }

    @PostMapping("/upload_file")
    public Result uploadFile(@RequestBody MultipartFile file){
        String path= FileUtil.uploadImage(file);
        if(path.equals("")){
            return ResultBuilder.failResult("图片上传失败!");
        }
        return ResultBuilder.successResult(path);
    }

    @RequestMapping("/get_card")
    public Result getCard(@RequestParam Integer cardId){
        return cardService.getCard(cardId);
    }

    @RequestMapping("/get_cards")
    public Result getCards(@RequestParam Integer length){
        return cardService.getCards(length);
    }

    //实现点赞功能--通过redis
    @GetMapping("/like")
    public Result addLike(@RequestParam Integer cardId,@RequestParam Integer userId){
        String key="card"+cardId;
        String value=userId+"";
        //判断卡片-用户是否存在，存在则已点赞
        boolean isLike=redisTemplate.opsForSet().isMember(key,value);
        long count;
        //已点赞，再点击取消点赞
        if(isLike){
            //移除集合中的用户
            redisTemplate.opsForSet().remove(key,value);
            //获取点赞数
            count=redisTemplate.opsForSet().size(key);
            return ResultBuilder.successResult("取消点赞",count);
        }else {
            redisTemplate.opsForSet().add(key, value);
            count = redisTemplate.opsForSet().size(key);
            return ResultBuilder.successResult("成功点赞", count);
        }
    }

    //获取点赞数
    @GetMapping("/like_count")
    public long getLikeCount(@RequestParam Integer cardId){
        String key="card"+cardId;
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 获得卡片编辑的图片, 转存到服务端资源文件夹
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload_image")
    public Result uploadImage(@RequestBody MultipartFile file,Integer userId){
        try{
            if(!file.isEmpty()){
                //创建用户目录
                File dir= FileUtil.createUserImageDir(userId);
                String dirName= dir.getName();
                //获取原始文件名
                String originalFileName=file.getOriginalFilename();
                String[] names=originalFileName.split("\\.");
                System.out.println(names[1]);
                //重命名--根据系统时间
                long curMillis=System.currentTimeMillis();
                //文件转存, 存到本项目static/images/用户目录文件夹下
                String suffix=new ApplicationHome().getDir().getAbsolutePath()+"/src/main/resources/static/images/"+dirName+"/";
                String path=suffix+curMillis+"."+names[1];
                //文件转存
                file.transferTo(new File(path));
                return ResultBuilder.successResult(Url.STATIC_URL+dirName+"/"+curMillis+"."+names[1]);
            }else{
                System.out.println("null image");
                return ResultBuilder.failResult("上传图片为空!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultBuilder.failResult("图片大小不能超过20MB!");
        }
    }

    @GetMapping("/delete_image")
    public Result deleteImage(@RequestParam String imageUrl){
        boolean flag=FileUtil.deleteUploadImage(imageUrl);
        if(flag){
            return ResultBuilder.successResult("图片已删除");
        }
        return ResultBuilder.failResult("图片未删除");
    }

}
