package com.alura.forohub.controller;

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
            return ResponseEntity.badRequest().body(new BadRequestDTO("Warning", "Ya existe un tópico con el mismo título y mensaje."));
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
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico
                .<ResponseEntity<ResponseDTO>>map(value -> ResponseEntity.ok(new TopicoResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.badRequest().body(new BadRequestDTO("Error", "No se encontró ningún tópico con el ID " + id + ".")));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseDTO> actualizarTopico(@RequestBody @Valid TopicoUpdateRequestDTO topicoUpdateRequestDTO) {
        Optional<Topico> topico = topicoRepository.findById(topicoUpdateRequestDTO.id());

        if (topico.isPresent()){
            topico.get().actualizarDatos(topicoUpdateRequestDTO);
            return ResponseEntity.ok(new TopicoResponseDTO(topico.get()));
        }
        return ResponseEntity.badRequest().body(new BadRequestDTO("Error", "No se encontró ningún tópico con el ID " + topicoUpdateRequestDTO.id() + "."));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseDTO> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.delete(topico.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(new BadRequestDTO("Error", "No se encontró ningún tópico con el ID " + id + "."));
    }
}
