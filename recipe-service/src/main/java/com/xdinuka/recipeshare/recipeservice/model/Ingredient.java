package com.xdinuka.recipeshare.recipeservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Ingredient {

    @Id
    Integer id;
    @ManyToMany(mappedBy = "ingredients")
    List<Recipe> recipes;
    @Transient
    String name;

}
