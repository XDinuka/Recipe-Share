package com.xdinuka.recipeio.commentservice.repository;

import com.xdinuka.recipeio.commentservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostIDOrderById(Integer postID);
}
