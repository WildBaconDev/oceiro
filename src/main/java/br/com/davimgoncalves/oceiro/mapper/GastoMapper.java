package br.com.davimgoncalves.oceiro.mapper;

import br.com.davimgoncalves.oceiro.dto.ConsultaGastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.GastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.SalvarGastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.GastoResponseDTO;
import br.com.davimgoncalves.oceiro.model.Gasto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GastoMapper {

    GastoMapper INSTANCE = Mappers.getMapper(GastoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Gasto toModel(SalvarGastoRequestDTO salvarGastoRequestDTO);

    @Mapping(target = "dataCriacao", ignore = true)
    Gasto toModel(GastoRequestDTO gastoRequestDTO);

    GastoResponseDTO toResponseDto(Gasto gasto);

    ConsultaGastoResponseDTO toConsultaGastoResponse(Gasto gasto);

    List<ConsultaGastoResponseDTO> toConsultaGastoResponse(List<Gasto> gasto);
}
