package com.xdinuka.recipeio.apigateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    private String username;

    //    @JsonIgnore
    private String password;

//    @JsonIgnore
//    public String getPassword() {
//        return password;
//    }
//
//    @JsonProperty
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
