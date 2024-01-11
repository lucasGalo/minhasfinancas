package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.domain.entity.Telemetria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelemetriaMapper extends MAPPER<TelemetriaDTO, Telemetria> {
    TelemetriaDTO toDto(Telemetria obj);
    Telemetria toObj(TelemetriaDTO dto);
}

