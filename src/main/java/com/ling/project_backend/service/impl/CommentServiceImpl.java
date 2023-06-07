package com.ling.project_backend.service.impl;

import com.ling.project_backend.domain.Comment;
import com.ling.project_backend.mapper.CommentMapper;
import com.ling.project_backend.service.CommentService;
import com.ling.project_backend.utils.Result;
import com.ling.project_backend.utils.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Result addComment(Comment comment) {
        comment.setCommentTime(Calendar.getInstance().getTime());
        comment.setCommentLike(0);
        int r=commentMapper.insertComment(comment);
        if(r>0){
            return ResultBuilder.successResult(comment);
        }
        return ResultBuilder.failResult("评论发布失败!");
    }

    @Override
    public Result searchComments(Comment comment) {
        List<Comment> records=commentMapper.findComment(comment);
        if(records!=null&&!records.isEmpty()){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult("评论不存在!");
    }

    @Override
    public Result searchCommentByCardId(Integer cardId) {
        List<Comment> records=commentMapper.findCommentByCardId(cardId);
        if(records!=null&&!records.isEmpty()){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult("文章或评论不存在!");
    }

    /**
     * 获得卡片的所有评论数
     * @param cardId
     * @return
     */
    @Override
    public Result getCommentCount(Integer cardId) {
        int count=commentMapper.getCommentCountById(cardId);
        return ResultBuilder.successResult(count);
    }

    /**
     * 根据cardId获得所有评论
     * @param
     * @return
     */
    @Override
    public Result getComments(Integer cardId) {
        List<Comment> records=commentMapper.findCommentByCardId(cardId);
        if (!records.isEmpty()){
            return ResultBuilder.successResult(records);
        }
        return ResultBuilder.failResult(0);
    }
}
