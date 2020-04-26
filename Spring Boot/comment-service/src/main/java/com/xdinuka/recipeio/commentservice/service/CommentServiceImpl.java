package com.xdinuka.recipeio.commentservice.service;

import com.xdinuka.recipeio.commentservice.hystrix.UserCommand;
import com.xdinuka.recipeio.commentservice.model.Comment;
import com.xdinuka.recipeio.commentservice.model.User;
import com.xdinuka.recipeio.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    final CommentRepository commentRepository;
    final RestTemplate restTemplate;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, RestTemplate restTemplate) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;

    }

    @Override
    public Comment saveOrUpdate(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> fetchByPost(Integer id, HttpHeaders httpHeaders) {
        List<Comment> allByUserIDOrOrderByIdAsc = commentRepository.findAllByPostIDOrderById(id);

        allByUserIDOrOrderByIdAsc.parallelStream().forEach(comment -> {
            comment.setUser(new UserCommand(comment.getUserID(), httpHeaders, restTemplate).execute());
        });

        return allByUserIDOrOrderByIdAsc;
    }

//    @Override
//    public List<Post> fetchAll(HttpHeaders httpHeaders) {
//
//
//        List<Post> all = postRepository.findAll();
//        all.parallelStream().forEach(post -> {
//            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
//            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
//        });
//        return all;
//    }
//
//
//    @Override
//    public Post saveOrUpdate(Post post) {
//        return postRepository.save(post);
//    }
//
//    @Override
//    public Optional<Post> fetchById(Integer id, HttpHeaders httpHeaders) {
//        Optional<Post> optionalPost = postRepository.findById(id);
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
//            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
//            optionalPost = Optional.of(post);
//        }
//        return optionalPost;
//    }
//
//    @Override
//    public List<Post> fetchByRecipe(Integer id, HttpHeaders httpHeaders) {
//        List<Post> allByRecipeID = postRepository.findAllByRecipeID(id);
//        allByRecipeID.parallelStream().forEach(post -> {
//            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
//            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
//        });
//        return allByRecipeID;
//    }
//
//    @Override
//    public List<Post> fetchByUser(Integer id, HttpHeaders httpHeaders) {
//        List<Post> allByRecipeID = postRepository.findAllByUserID(id);
//        allByRecipeID.parallelStream().forEach(post -> {
//            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
//            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
//        });
//        return allByRecipeID;
//    }


}
