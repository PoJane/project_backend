package com.ling.project_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;


/**
 * 发布内容
 * 卡片实体
 */
@Data
public class Card {

   private Integer cardId;
   private Integer userId;
   private String cardHead;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
   private Date cardTime;
   private String cardImage;
   private String cardText;
   private Integer cardLike;
}
