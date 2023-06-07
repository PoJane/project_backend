package com.ling.project_backend.controller;

import com.ling.project_backend.domain.Comment;
import com.ling.project_backend.service.CommentService;
import com.ling.project_backend.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/add_comment")
    public Result addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @PostMapping("/search_comment")
    public Result searchComment(@RequestBody Comment comment){
        return commentService.searchComments(comment);
    }

    @PostMapping("/search_comments/{cardId}")
    public Result searchComments(@PathVariable Integer cardId){
        return commentService.searchCommentByCardId(cardId);
    }

    @GetMapping("/get_comments")
    public Result getComments(@RequestParam Integer cardId){
        return commentService.getComments(cardId);
    }

    @GetMapping("/get_comment_count")
    public Result getCommentCount(@RequestParam Integer cardId){
        return commentService.getCommentCount(cardId);
    }


}
