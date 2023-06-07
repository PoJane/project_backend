package com.ling.project_backend.service.impl;

import com.ling.project_backend.common.Url;
import com.ling.project_backend.domain.Card;
import com.ling.project_backend.mapper.CardMapper;
import com.ling.project_backend.service.CardService;
import com.ling.project_backend.utils.FileUtil;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Resource
    private CardMapper cardMapper;

    @Override
    public Result createCard(Card card) {
        card.setCardTime(Calendar.getInstance().getTime());
        int r=cardMapper.insertCard(card);
        if(r>0){
            return ResultBuilder.successResult(r);
        }
        return ResultBuilder.failResult("创建失败!");
    }

    @Override
    public Result deleteCard(Card card) {
        int r=cardMapper.deleteCard(card);
        if(r>0){
            return ResultBuilder.successResult(r);
        }
        return ResultBuilder.failResult("删除失败!");
    }

    @Override
    public Result changeCard(Card card) {
        card.setCardTime(Calendar.getInstance().getTime());
        int r=cardMapper.updateCard(card);
        if(r>0){
            return ResultBuilder.successResult(r);
        }
        return ResultBuilder.failResult("修改失败!");
    }

    @Override
    public Result searchCards(Card card) {
        List<Card> records=cardMapper.selectCard(card);
        if(records!=null&&!records.isEmpty()){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult("没有找到相关内容...");
    }

    @Override
    public Result getCard(Integer id) {
        Card record=cardMapper.getCard(id);
        if(record!=null){
            return ResultBuilder.successResult(record);
        }
        return ResultBuilder.failResult("卡片不存在!");
    }

    @Override
    public Result getCardCount() {
        int count=cardMapper.getCardCount();
        if(count>0){
            return ResultBuilder.successResult(count);
        }
        return ResultBuilder.failResult(-1);
    }

    @Override
    public Result getCards(int length) {
        List<Card> records=cardMapper.getCards(length);
        if(!records.isEmpty()){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult("获取内容失败!");
    }


}
