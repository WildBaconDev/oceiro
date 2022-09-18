package br.com.davimgoncalves.oceiro.repository;

import br.com.davimgoncalves.oceiro.model.Gasto;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GastoRepository extends CrudRepository<Gasto, String> {

    List<Gasto> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
