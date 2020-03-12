package com.xdinuka.recipeio.ingredientservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    Boolean isVegan;

}
