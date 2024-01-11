package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.PesquisaDTO;
import com.galo.minhasfinancas.domain.entity.Pesquisa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PesquisaMapper extends MAPPER<PesquisaDTO, Pesquisa> {
    PesquisaDTO toDto(Pesquisa obj);
    Pesquisa toObj(PesquisaDTO dto);
}

