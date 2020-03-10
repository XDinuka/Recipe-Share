package com.xdinuka.recipeio.recipeservice.model;

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
    @OneToMany(mappedBy = "recipe")
    List<Ingredient> ingredients;
    @Transient
    Boolean isVegan;

}
