package br.com.davimgoncalves.oceiro.service;

import br.com.davimgoncalves.oceiro.dto.ConsultaGastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.GastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.SalvarGastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.GastoResponseDTO;
import br.com.davimgoncalves.oceiro.exception.NotFoundException;
import br.com.davimgoncalves.oceiro.mapper.GastoMapper;
import br.com.davimgoncalves.oceiro.model.Gasto;
import br.com.davimgoncalves.oceiro.repository.GastoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class GastoService {

    private static final String METHOD_ID = "method={}; id={};";

    @Autowired
    private GastoRepository gastoRepository;

    @Transactional
    public GastoResponseDTO cadastrarGasto(SalvarGastoRequestDTO salvarGastoRequestDTO) {
        log.info("method=cadastrarGasto;");
        var gasto = GastoMapper.INSTANCE.toModel(salvarGastoRequestDTO);
        gasto.setId(UUID.randomUUID().toString());
        gasto.setDataCriacao(LocalDateTime.now());
        return GastoMapper.INSTANCE.toResponseDto(salvarGasto(gasto));
    }

    @Transactional
    public GastoResponseDTO atualizarGasto(GastoRequestDTO gastoRequestDTO) {
        log.info("method=cadastrarGasto;");
        var gasto = GastoMapper.INSTANCE.toModel(gastoRequestDTO);
        var gastoFound = findGastoById(gastoRequestDTO.id());
        gasto.setDataCriacao(gastoFound.getDataCriacao());
        return GastoMapper.INSTANCE.toResponseDto(salvarGasto(gasto));
    }

    @Transactional
    public Gasto salvarGasto(Gasto gasto) {
        log.info("method=salvarGasto; id={}", gasto.getId());
        return gastoRepository.save(gasto);
    }

    @Transactional
    public void removerGasto(String id) {
        log.info(METHOD_ID, "removerGasto", id);
        var gasto = findGastoById(id);
        gastoRepository.delete(gasto);
    }

    public ConsultaGastoResponseDTO consultarGasto(String id) {
        log.info(METHOD_ID, "consultarGasto", id);
        return GastoMapper.INSTANCE.toConsultaGastoResponse(findGastoById(id));
    }

    private Gasto findGastoById(String id) {
        log.info(METHOD_ID, "findGastoById", id);
        return gastoRepository.findById(id).orElseThrow(() -> {
            log.error("method={}; id={}; message={}", "findGastoById", id, "gasto not found");
            throw new NotFoundException("Gasto com id %s não foi encontrado.".formatted(id));
        });
    }
}
