package com.xdinuka.recipeio.commentservice.service;

import com.xdinuka.recipeio.commentservice.model.Comment;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CommentService {

    Comment saveOrUpdate(Comment comment);

    List<Comment> fetchByPost(Integer id, HttpHeaders httpHeaders);

}
