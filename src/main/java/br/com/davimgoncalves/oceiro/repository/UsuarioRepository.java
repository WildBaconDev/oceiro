package br.com.davimgoncalves.oceiro.repository;

import br.com.davimgoncalves.oceiro.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> { }
