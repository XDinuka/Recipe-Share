package com.xdinuka.recipeio.commentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer postID;
    Integer userID;
    String text;
    @Transient
    User user;

}
