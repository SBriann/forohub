package com.alura.forohub.controller;

import com.alura.forohub.infra.security.DatoJWTToken;
import com.alura.forohub.infra.security.TokenService;
import com.alura.forohub.model.dto.AutenticacionUsuarioDTO;
import com.alura.forohub.model.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionUsuarioDTO.login(), autenticacionUsuarioDTO.clave());
        var usuaroAutenticado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generarToken((Usuario) usuaroAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatoJWTToken(JWTToken));
    }
}
