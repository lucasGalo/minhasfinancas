package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.RendaDTO;
import com.galo.minhasfinancas.domain.dto.TipoDTO;
import com.galo.minhasfinancas.domain.entity.Renda;
import com.galo.minhasfinancas.domain.entity.Tipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Mapper(componentModel = "spring")
public interface TipoMapper extends MAPPER<TipoDTO, Tipo> {

    @Mapping(source = "rendas", target = "rendas", qualifiedByName = "rendasDTO")
    TipoDTO toDto(Tipo obj);

    Tipo toObj(TipoDTO dto);

    @Named("rendasDTO")
    static List<RendaDTO> rendasDTO(List<Renda> rendas) {
        return custom(rendas, RendaDTO.class, Tipo.class);
    }
}