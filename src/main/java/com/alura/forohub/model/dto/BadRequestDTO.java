package com.alura.forohub.model.dto;

public record BadRequestDTO (
        String tipo,
        String mensaje
) implements ResponseDTO {
}
