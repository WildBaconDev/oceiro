package br.com.davimgoncalves.oceiro.controller;

import br.com.davimgoncalves.oceiro.dto.LoginRequest;
import br.com.davimgoncalves.oceiro.dto.TokenResponse;
import br.com.davimgoncalves.oceiro.dto.UsuarioRequest;
import br.com.davimgoncalves.oceiro.dto.UsuarioResponse;
import br.com.davimgoncalves.oceiro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/usuario")
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

