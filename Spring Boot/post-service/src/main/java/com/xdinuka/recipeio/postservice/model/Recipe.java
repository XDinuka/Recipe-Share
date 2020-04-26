package com.xdinuka.recipeio.postservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class Recipe implements Serializable {

    Integer id;

    String name;

    List<Ingredient> ingredients;

    String instructions;

    Boolean vegan;

    @JsonIgnore
    Integer userID;

    User user;

}
