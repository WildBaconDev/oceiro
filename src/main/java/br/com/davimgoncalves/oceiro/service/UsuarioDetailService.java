package br.com.davimgoncalves.oceiro.service;

import br.com.davimgoncalves.oceiro.annotation.LogExecutionTime;
import br.com.davimgoncalves.oceiro.mapper.UsuarioMapper;
import br.com.davimgoncalves.oceiro.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @LogExecutionTime
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("method={}; email={};", "loadUserByUsername", email);
        var usuario = usuarioRepository.findById(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuario não encontrado.");
        });
        log.info("method={}; email={}; mensagem={}", "loadUserByUsername", email, "Usuario encontrado");
        return UsuarioMapper.INSTANCE.toDetail(usuario);
    }
}
