package com.ling.project_backend.mapper;

import com.ling.project_backend.domain.Comment;

import java.util.List;

public interface CommentMapper {

    int insertComment(Comment comment);

    List<Comment> findComment(Comment comment);

    List<Comment> findCommentByCardId(Integer cardId);

    int getCommentCountById(Integer cardId);

    List<Comment> getComments(Integer count);

}
