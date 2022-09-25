package br.com.davimgoncalves.oceiro.mapper;

import br.com.davimgoncalves.oceiro.config.details.UsuarioDetail;
import br.com.davimgoncalves.oceiro.dto.UsuarioResponse;
import br.com.davimgoncalves.oceiro.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDetail toDetail(Usuario usuario);

    UsuarioResponse toResponse(Usuario usuario);
}
