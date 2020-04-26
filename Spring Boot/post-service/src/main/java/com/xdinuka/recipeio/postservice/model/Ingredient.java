package com.xdinuka.recipeio.postservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
public class Ingredient {

    Integer id;

    @JsonIgnore
    @ToString.Exclude
    Recipe recipe;

    String quantity;

    String name;
}
