package br.com.davimgoncalves.oceiro.service;

import br.com.davimgoncalves.oceiro.dto.LoginRequest;
import br.com.davimgoncalves.oceiro.dto.TokenResponse;
import br.com.davimgoncalves.oceiro.dto.UsuarioRequest;
import br.com.davimgoncalves.oceiro.dto.UsuarioResponse;
import br.com.davimgoncalves.oceiro.model.Usuario;

public interface UsuarioService {

    UsuarioResponse salvar(UsuarioRequest usuarioRequest);

    TokenResponse autenticar(LoginRequest loginRequest);

    Usuario consultarUsuario(String email);
}
