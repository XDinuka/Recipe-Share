package com.xdinuka.recipeio.recipeservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    Integer ingredientId;
    @ManyToOne(cascade = CascadeType.ALL)
    Recipe recipe;
    @Transient
    String name;
    @Transient
    Boolean isVegan;

}
