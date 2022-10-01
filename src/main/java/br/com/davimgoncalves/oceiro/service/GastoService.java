package br.com.davimgoncalves.oceiro.service;

import br.com.davimgoncalves.oceiro.dto.ConsultaGastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.GastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.GastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.SalvarGastoRequestDTO;
import br.com.davimgoncalves.oceiro.model.Gasto;

import java.time.LocalDate;
import java.util.List;

public interface GastoService {

    GastoResponseDTO cadastrarGasto(SalvarGastoRequestDTO salvarGastoRequestDTO);
    
    GastoResponseDTO atualizarGasto(GastoRequestDTO gastoRequestDTO);

    Gasto salvarGasto(Gasto gasto);

    void removerGasto(String id);

    ConsultaGastoResponseDTO consultarGasto(String id);

    List<ConsultaGastoResponseDTO> consultarGastosPorDia(LocalDate data);

    List<ConsultaGastoResponseDTO> consultarGastosPorMes(LocalDate data);
}
