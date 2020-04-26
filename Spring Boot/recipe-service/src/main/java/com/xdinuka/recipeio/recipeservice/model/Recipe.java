package com.xdinuka.recipeio.recipeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    List<Ingredient> ingredients;

    @Lob
    String instructions;

    Boolean vegan;

    @JsonIgnore
    Integer userID;

    @Transient
    User user;

//    public Recipe() {
//    }
//
//    public Recipe(Integer id, String name, Boolean isVegan, IngredientOld... ingredientOlds) {
//        setId(id);
//        setName(name);
//        setIngredientOlds(Arrays.asList(ingredientOlds));
//    }
//
//    public Recipe(Integer id, String name) {
//        setId(id);
//        setName(name);
//    }
//
//    public Recipe(Integer id, String name, Boolean isVegan, List<IngredientOld> ingredientOlds) {
//        setId(id);
//        setName(name);
//        setIngredientOlds(ingredientOlds);
//    }

}
