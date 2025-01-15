package com.alura.forohub.controller;

import com.alura.forohub.infra.errores.ValidacionException;
import com.alura.forohub.model.dto.*;
import com.alura.forohub.model.entity.Topico;
import com.alura.forohub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDTO> registrarTopico(@RequestBody @Valid TopicoRequestDTO topicoRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = new Topico(topicoRequestDTO);

        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }

        topico = topicoRepository.save(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(new TopicoResponseDTO(topico));
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopicos(@PageableDefault(size = 2, sort = {"titulo"}) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(TopicoResponseDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> obtenerTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicoResponseDTO(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseDTO> actualizarTopico(@RequestBody @Valid TopicoUpdateRequestDTO topicoUpdateRequestDTO) {
        Topico topico = topicoRepository.getReferenceById(topicoUpdateRequestDTO.id());
        topico.actualizarDatos(topicoUpdateRequestDTO);
        return ResponseEntity.ok(new TopicoResponseDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseDTO> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
