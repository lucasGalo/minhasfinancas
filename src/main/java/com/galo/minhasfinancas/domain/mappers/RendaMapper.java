package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.domain.dto.TipoDTO;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Renda;
import com.galo.minhasfinancas.domain.entity.Tipo;
import com.galo.minhasfinancas.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.DateUtil.DD_MM_YYYY;
import static com.galo.minhasfinancas.sources.DateUtil.toDate;

@Mapper(componentModel = "spring")
public interface RendaMapper extends MAPPER<RendaDTO, Renda> {
  @Mapping(source = "usuario", target = "usuario", qualifiedByName = "usuarioDTO")
  @Mapping(source = "tipo", target = "tipo", qualifiedByName = "tipoDTO")
  RendaDTO toDto(Renda obj);

  @Mapping(source = "usuario.nascimento", target = "usuario.nascimento", qualifiedByName = "nascimento")
  Renda toObj(RendaDTO dto);

  @Named("nascimento")
  static Date nascimento(String nascimento) {
    if (nascimento.isEmpty()) new Date();
    return toDate(nascimento.replace("/", "-"), DD_MM_YYYY);
  }
  @Named("usuarioDTO") static UsuarioDTO usuarioDTO(Usuario usuario) {
    return custom(usuario, UsuarioDTO.class, Renda.class);
  }
  @Named("tipoDTO") static TipoDTO tipoDTO(Tipo tipo) {
    return custom(tipo, TipoDTO.class, Renda.class);
  }
}