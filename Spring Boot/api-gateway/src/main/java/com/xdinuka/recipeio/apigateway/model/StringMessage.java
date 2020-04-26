package com.xdinuka.recipeio.apigateway.model;

import lombok.Data;

@Data
public class StringMessage {
    String value;

    public StringMessage(String value) {
        this.value = value;
    }

    public StringMessage() {
    }
}
