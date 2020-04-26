package com.xdinuka.recipeio.recipeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    @ToString.Exclude
    Recipe recipe;

//    @ManyToOne
//    @JoinColumn
//    Integer ingredient;

    String quantity;

    String name;
}
