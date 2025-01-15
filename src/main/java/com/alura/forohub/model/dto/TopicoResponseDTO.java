package com.alura.forohub.model.dto;

import com.alura.forohub.model.entity.Topico;

import java.time.LocalDateTime;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
) implements ResponseDTO {

    public TopicoResponseDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
    }
}
