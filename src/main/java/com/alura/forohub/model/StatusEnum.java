package com.alura.forohub.model;

public enum StatusEnum {
    ABIERTO("Abierto"),
    CERRADO("Cerrado");
    private String value;

    StatusEnum(String value) {
        this.value = value;
    }
}
