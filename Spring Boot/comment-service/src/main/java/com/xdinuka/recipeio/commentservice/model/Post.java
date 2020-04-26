package com.xdinuka.recipeio.commentservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class Post {


    Integer id;
//    @JsonIgnore
//    Integer recipeID;
//    @JsonIgnore
//    Integer userID;
    String text;
    String image;
    User user;
    Recipe recipe;


}
