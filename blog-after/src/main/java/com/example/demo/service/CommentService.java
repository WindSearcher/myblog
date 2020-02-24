package com.example.demo.service;

import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> listCommentByBlogId(Long blogId);

    public void saveComment(Comment comment);
}
