package com.ling.project_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 评论实体类
 */
@Data
public class Comment {

    private Integer commentId;
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date commentTime;
    private Integer commentLike;
    private Integer cardId;
    private String commentText;

}
