package com.xdinuka.recipeio.postservice.model;

import lombok.Data;

@Data
public class StringMessage {
    String value;

    public StringMessage() {
    }

    public StringMessage(String value) {
        this.value = value;
    }
}
