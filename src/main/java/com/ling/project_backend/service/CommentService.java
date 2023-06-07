package com.ling.project_backend.service;

import com.ling.project_backend.domain.Comment;
import com.ling.project_backend.utils.Result;

public interface CommentService {

    Result addComment(Comment comment);

    Result searchComments(Comment comment);

    Result searchCommentByCardId(Integer cardId);

    Result getCommentCount(Integer cardId);

    Result getComments(Integer cardId);

}
