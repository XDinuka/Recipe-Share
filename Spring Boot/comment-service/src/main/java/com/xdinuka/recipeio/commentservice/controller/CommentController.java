package com.xdinuka.recipeio.commentservice.controller;

import com.xdinuka.recipeio.commentservice.model.Comment;
import com.xdinuka.recipeio.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class CommentController {

    final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    Comment insertOrUpdate(@RequestBody Comment comment) {
        return commentService.saveOrUpdate(comment);
    }


    @GetMapping("/post/{id}")
    public List<Comment> searchByPost(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders) {
        return commentService.fetchByPost(id, httpHeaders);
    }


//    }
}
