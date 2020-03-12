package com.xdinuka.recipeio.recipeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    Recipe recipe;
    @Transient
    String name;
    @Transient
    Boolean isVegan;
    String quantity;

}
