package br.com.davimgoncalves.oceiro.controller;

import br.com.davimgoncalves.oceiro.config.details.UsuarioDetail;
import br.com.davimgoncalves.oceiro.dto.LoginRequest;
import br.com.davimgoncalves.oceiro.dto.TokenResponse;
import br.com.davimgoncalves.oceiro.dto.UsuarioRequest;
import br.com.davimgoncalves.oceiro.dto.UsuarioResponse;
import br.com.davimgoncalves.oceiro.model.Usuario;
import br.com.davimgoncalves.oceiro.security.JWTUtil;
import br.com.davimgoncalves.oceiro.service.UsuarioService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/v1/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<UsuarioResponse> salvar(@RequestBody UsuarioRequest usuarioRequest) {
        var usuarioSalvo = usuarioService.salvar(usuarioRequest);
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/v1/usuario/").toUriString());
        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(usuarioService.autenticar(loginRequest));
    }

}

