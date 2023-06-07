package com.ling.project_backend.mapper;

import com.ling.project_backend.domain.Card;

import java.util.List;

public interface CardMapper {

    //创建卡片
    int insertCard(Card card);

    //删除卡片
    int deleteCard(Card card);

    //更新卡片内容
    int updateCard(Card card);

    //获取卡片数目
    int getCardCount();

    //获得指定数目卡片
    List<Card> getCards(int length);

    //卡片模糊查找
    List<Card> selectCard(Card card);

    //通过id查找卡片
    Card getCard(Integer cardId);

}
