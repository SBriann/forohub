package com.alura.forohub.infra.errores;

import com.alura.forohub.model.dto.BadRequestDTO;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(new BadRequestDTO("Error", e.getMessage()));
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}

