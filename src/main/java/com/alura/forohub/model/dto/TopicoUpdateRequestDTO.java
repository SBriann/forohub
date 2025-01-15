package com.alura.forohub.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoUpdateRequestDTO (
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String curso
) {
}
