package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.ContatoDTO;
import com.galo.minhasfinancas.domain.entity.Contato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContatoMapper extends MAPPER<ContatoDTO, Contato> {
    ContatoDTO toDto(Contato obj);
    Contato toObj(ContatoDTO dto);
}

