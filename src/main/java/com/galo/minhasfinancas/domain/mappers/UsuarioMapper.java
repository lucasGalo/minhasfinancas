package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends MAPPER<UsuarioDTO, Usuario> {
  @Mapping(source = "compras", target = "compras", qualifiedByName = "comprasDTO")
  UsuarioDTO toDto(Usuario obj);

  Usuario toObj(UsuarioDTO dto);

  @Named("comprasDTO")
  static List<CompraDTO> comprasDTO(List<Compra> compras) {
    return custom(compras, CompraDTO.class, Usuario.class);
  }
}
