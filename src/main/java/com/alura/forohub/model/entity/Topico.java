package com.alura.forohub.model.entity;

import com.alura.forohub.model.StatusEnum;
import com.alura.forohub.model.dto.TopicoRequestDTO;
import com.alura.forohub.model.dto.TopicoUpdateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String curso;

    public Topico(TopicoRequestDTO topicoRequestDTO) {
        this.titulo = topicoRequestDTO.titulo();
        this.mensaje = topicoRequestDTO.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusEnum.ABIERTO;
        this.curso = topicoRequestDTO.curso();
    }

    public void actualizarDatos(@Valid TopicoUpdateRequestDTO topicoUpdateRequestDTO) {
        this.titulo = topicoUpdateRequestDTO.titulo() == null ? this.titulo : topicoUpdateRequestDTO.titulo();
        this.mensaje = topicoUpdateRequestDTO.mensaje() == null ? this.mensaje : topicoUpdateRequestDTO.mensaje();
        this.curso = topicoUpdateRequestDTO.curso()== null ? this.curso : topicoUpdateRequestDTO.curso();
    }
}
