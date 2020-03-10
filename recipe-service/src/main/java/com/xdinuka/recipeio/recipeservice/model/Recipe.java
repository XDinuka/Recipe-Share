package com.xdinuka.recipeio.recipeservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
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
    @OneToMany(mappedBy = "recipe")
    List<Ingredient> ingredients;
    @Transient
    Boolean isVegan;

    public Recipe(){}
    public Recipe(Integer id,String name,Boolean isVegan,Ingredient... ingredients){
        setId(id);
        setName(name);
        setIsVegan(isVegan);
        setIngredients(Arrays.asList(ingredients));
    }public Recipe(Integer id,String name,Boolean isVegan,List<Ingredient> ingredients){
        setId(id);
        setName(name);
        setIsVegan(isVegan);
        setIngredients(ingredients);
    }

}
