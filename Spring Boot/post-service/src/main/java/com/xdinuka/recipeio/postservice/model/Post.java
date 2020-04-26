package com.xdinuka.recipeio.postservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @JsonIgnore
    Integer recipeID;
    @JsonIgnore
    Integer userID;
    @Lob
    String text;
    @Lob
    String image;


    @Transient
    User user;

    @Transient
    Recipe recipe;


}
