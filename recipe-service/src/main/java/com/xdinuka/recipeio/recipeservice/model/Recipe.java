package com.xdinuka.recipeio.recipeservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "recipe_ingredients",
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id",referencedColumnName = "id")},
            joinColumns  = {@JoinColumn(name = "recipe_id",referencedColumnName = "id")}
    )
    List<Ingredient> ingredients;
    @Transient
    Boolean isVegan;

}
