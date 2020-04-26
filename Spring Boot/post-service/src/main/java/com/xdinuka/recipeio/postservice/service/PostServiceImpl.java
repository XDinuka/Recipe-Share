package com.xdinuka.recipeio.postservice.service;

import com.xdinuka.recipeio.postservice.hystrix.RecipeCommand;
import com.xdinuka.recipeio.postservice.hystrix.UserCommand;
import com.xdinuka.recipeio.postservice.model.Post;
import com.xdinuka.recipeio.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;
    final RestTemplate restTemplate;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;

    }

    @Override
    public List<Post> fetchAll(HttpHeaders httpHeaders) {


        List<Post> all = postRepository.findAll();
        all.parallelStream().forEach(post -> {
            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
        });
        return all;
    }


    @Override
    public Post saveOrUpdate(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> fetchById(Integer id, HttpHeaders httpHeaders) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
            optionalPost = Optional.of(post);
        }
        return optionalPost;
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> fetchByRecipe(Integer id, HttpHeaders httpHeaders) {
        List<Post> allByRecipeID = postRepository.findAllByRecipeID(id);
        allByRecipeID.parallelStream().forEach(post -> {
            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
        });
        return allByRecipeID;
    }

    @Override
    public List<Post> fetchByUser(Integer id, HttpHeaders httpHeaders) {
        List<Post> allByRecipeID = postRepository.findAllByUserID(id);
        allByRecipeID.parallelStream().forEach(post -> {
            post.setUser(new UserCommand(post.getUserID(), httpHeaders, restTemplate).execute());
            post.setRecipe(new RecipeCommand(post.getRecipeID(), httpHeaders, restTemplate).execute());
        });
        return allByRecipeID;
    }


}
