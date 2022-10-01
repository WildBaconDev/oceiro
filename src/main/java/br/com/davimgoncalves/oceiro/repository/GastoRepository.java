package br.com.davimgoncalves.oceiro.repository;

import br.com.davimgoncalves.oceiro.model.Gasto;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GastoRepository extends CrudRepository<Gasto, String> {

    List<Gasto> findByUsuarioEmailAndDataBetween(String email, LocalDateTime dataInicio, LocalDateTime dataFim);

    Optional<Gasto> findByIdAndUsuarioEmail(String id, String email);

}
