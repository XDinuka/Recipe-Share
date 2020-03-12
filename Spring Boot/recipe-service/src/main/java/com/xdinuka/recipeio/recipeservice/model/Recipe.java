package com.xdinuka.recipeio.recipeservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String name;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    List<Ingredient> ingredients;
    String instructions;
    Integer user;

    public Recipe(){}
    public Recipe(Integer id,String name,Boolean isVegan,Ingredient... ingredients){
        setId(id);
        setName(name);
        setIngredients(Arrays.asList(ingredients));
    }
    public Recipe(Integer id,String name){
        setId(id);
        setName(name);
    }

    public Recipe(Integer id,String name,Boolean isVegan,List<Ingredient> ingredients){
        setId(id);
        setName(name);
        setIngredients(ingredients);
    }

}
