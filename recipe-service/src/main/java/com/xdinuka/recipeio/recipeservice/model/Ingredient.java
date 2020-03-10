package com.xdinuka.recipeio.recipeservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Ingredient implements Serializable {

    @Id
    @Column(nullable = false)
    Integer ingredientId;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    Recipe recipe;
    @Transient
    String name;
    @Transient
    Boolean isVegan;

}
