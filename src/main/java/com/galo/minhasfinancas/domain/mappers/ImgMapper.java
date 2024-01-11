package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.ImgDTO;
import com.galo.minhasfinancas.domain.entity.Img;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImgMapper extends MAPPER<ImgDTO, Img> {
    ImgDTO toDto(Img obj);
    Img toObj(ImgDTO dto);
}