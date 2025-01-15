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

    public Topico() {
    }

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

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, StatusEnum status, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
