package br.com.davimgoncalves.oceiro.service;

import br.com.davimgoncalves.oceiro.annotation.LogExecutionTime;
import br.com.davimgoncalves.oceiro.config.details.UsuarioDetail;
import br.com.davimgoncalves.oceiro.dto.LoginRequest;
import br.com.davimgoncalves.oceiro.dto.TokenResponse;
import br.com.davimgoncalves.oceiro.dto.UsuarioRequest;
import br.com.davimgoncalves.oceiro.dto.UsuarioResponse;
import br.com.davimgoncalves.oceiro.exception.UsuarioJaExistenteException;
import br.com.davimgoncalves.oceiro.mapper.UsuarioMapper;
import br.com.davimgoncalves.oceiro.model.Authority;
import br.com.davimgoncalves.oceiro.model.Usuario;
import br.com.davimgoncalves.oceiro.repository.UsuarioRepository;
import br.com.davimgoncalves.oceiro.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Override
    @Transactional
    @LogExecutionTime
    public UsuarioResponse salvar(UsuarioRequest usuarioRequest) {
        log.info("method={}; email={};", "salvar", usuarioRequest.email());

        try {
            getUsuario(usuarioRequest.email());
            throw new UsuarioJaExistenteException();
        } catch (UsernameNotFoundException ignored) { }

        var usuario = new Usuario(usuarioRequest.email(), usuarioRequest.username(),
                new BCryptPasswordEncoder().encode(usuarioRequest.password()), List.of(new Authority("USER")), true);

        return UsuarioMapper.INSTANCE.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @LogExecutionTime
    public TokenResponse autenticar(LoginRequest loginRequest) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        var authentication = authManager.authenticate(usernamePasswordAuthenticationToken);
        var token = jwtUtil.generateToken((UsuarioDetail) authentication.getPrincipal());
        return new TokenResponse(token);
    }

    @Override
    @LogExecutionTime
    public Usuario consultarUsuario(String email) {
        return getUsuario(email);
    }

    private Usuario getUsuario(String email) {
        return usuarioRepository.findById(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuario não encontrado.");
        });
    }
}
