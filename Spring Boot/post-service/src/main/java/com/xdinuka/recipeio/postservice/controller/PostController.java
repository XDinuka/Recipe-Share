package com.xdinuka.recipeio.postservice.controller;

import com.xdinuka.recipeio.postservice.model.Post;
import com.xdinuka.recipeio.postservice.model.StringMessage;
import com.xdinuka.recipeio.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class PostController {

    final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    Post insertOrUpdate(@RequestBody Post post) {
        post.setUserID(post.getUser().getId());
        post.setRecipeID(post.getRecipe().getId());
        return postService.saveOrUpdate(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> fetch(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders) {
        return ResponseEntity.of(postService.fetchById(id, httpHeaders));
    }

    @GetMapping
    public List<Post> fetchAll(@RequestHeader HttpHeaders httpHeaders) {
        return postService.fetchAll(httpHeaders);
    }

    @GetMapping("/user/{id}")
    public List<Post> searchByUser(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders) {
        return postService.fetchByUser(id, httpHeaders);
    }

    @DeleteMapping("/delete/{id}")
    public StringMessage deleteByID(@PathVariable Integer id){
        postService.deleteById(id);
        return new StringMessage("done");
    }

    @PostMapping("/recipe/{id}")
    public List<Post> searchByRecipe(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders) {
        return postService.fetchByRecipe(id, httpHeaders);
    }


//    }
}
