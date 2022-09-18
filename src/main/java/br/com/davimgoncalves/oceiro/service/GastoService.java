package br.com.davimgoncalves.oceiro.service;

import br.com.davimgoncalves.oceiro.annotation.LogExecutionTime;
import br.com.davimgoncalves.oceiro.dto.ConsultaGastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.GastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.GastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.SalvarGastoRequestDTO;
import br.com.davimgoncalves.oceiro.exception.NotFoundException;
import br.com.davimgoncalves.oceiro.mapper.GastoMapper;
import br.com.davimgoncalves.oceiro.model.Gasto;
import br.com.davimgoncalves.oceiro.repository.GastoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class GastoService {

    private static final String METHOD_ID = "method={}; id={};";

    @Autowired
    private GastoRepository gastoRepository;

    @LogExecutionTime
    @Transactional
    public GastoResponseDTO cadastrarGasto(SalvarGastoRequestDTO salvarGastoRequestDTO) {
        log.info("method={};", "cadastrarGasto");
        var gasto = GastoMapper.INSTANCE.toModel(salvarGastoRequestDTO);
        gasto.setDataCriacao(LocalDateTime.now());
        return GastoMapper.INSTANCE.toResponseDto(salvarGasto(gasto));
    }

    @LogExecutionTime
    @Transactional
    public GastoResponseDTO atualizarGasto(GastoRequestDTO gastoRequestDTO) {
        log.info("method={};", "atualizarGasto");
        var gasto = GastoMapper.INSTANCE.toModel(gastoRequestDTO);
        var gastoFound = findGastoById(gastoRequestDTO.id());
        gasto.setDataCriacao(gastoFound.getDataCriacao());
        return GastoMapper.INSTANCE.toResponseDto(salvarGasto(gasto));
    }

    @LogExecutionTime
    @Transactional
    public Gasto salvarGasto(Gasto gasto) {
        log.info("method=salvarGasto; id={}", gasto.getId());
        return gastoRepository.save(gasto);
    }

    @LogExecutionTime
    @Transactional
    public void removerGasto(String id) {
        log.info(METHOD_ID, "removerGasto", id);
        var gasto = findGastoById(id);
        gastoRepository.delete(gasto);
    }

    @LogExecutionTime
    public ConsultaGastoResponseDTO consultarGasto(String id) {
        log.info(METHOD_ID, "consultarGasto", id);
        return GastoMapper.INSTANCE.toConsultaGastoResponse(findGastoById(id));
    }

    @LogExecutionTime
    private Gasto findGastoById(String id) {
        log.info(METHOD_ID, "findGastoById", id);
        return gastoRepository.findById(id).orElseThrow(() -> {
            log.error("method={}; id={}; message={}", "findGastoById", id, "gasto not found");
            throw new NotFoundException("Gasto com id %s não foi encontrado.".formatted(id));
        });
    }

    @LogExecutionTime
    public List<ConsultaGastoResponseDTO> consultarGastosPorDia(LocalDate data) {
        var dataInicio = data.atStartOfDay();
        var dataFim = data.atTime(LocalTime.MAX);
        log.info("method={}; dataRequerida={}; dataInicio={}; dataFim={}", "consultarGastosPorDia", data, dataInicio, dataFim);
        return GastoMapper.INSTANCE.toConsultaGastoResponse(gastoRepository.findByDataBetween(dataInicio, dataFim));
    }

    @LogExecutionTime
    public List<ConsultaGastoResponseDTO> consultarGastosPorMes(LocalDate data) {
        var dataInicio = data.withDayOfMonth(1).atStartOfDay();
        var dataFim = data.withDayOfMonth(data.getMonth().length(data.isLeapYear())).atTime(LocalTime.MAX);
        log.info("method={}; dataRequerida={}; dataInicio={}; dataFim={}", "consultarGastosPorMes", data, dataInicio, dataFim);
        return GastoMapper.INSTANCE.toConsultaGastoResponse(gastoRepository.findByDataBetween(dataInicio, dataFim));
    }
}
