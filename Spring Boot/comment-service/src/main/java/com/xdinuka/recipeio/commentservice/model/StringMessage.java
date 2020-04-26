package com.xdinuka.recipeio.commentservice.model;

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
