package com.xdinuka.recipeio.commentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
public class Ingredient {

    Integer id;

    @JsonIgnore
    @ToString.Exclude
    Recipe recipe;

    String quantity;

    String name;
}
