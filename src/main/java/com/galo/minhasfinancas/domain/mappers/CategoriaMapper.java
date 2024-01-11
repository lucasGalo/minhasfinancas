package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends MAPPER<CategoriaDTO, Categoria> {

    @Mapping(source = "compras", target = "compras", qualifiedByName = "comprasDTO")
    CategoriaDTO toDto(Categoria obj);

    Categoria toObj(CategoriaDTO dto);

    @Named("comprasDTO")
    static List<CompraDTO> comprasDTO(List<Compra> compras) {
        return custom(compras, CompraDTO.class, Categoria.class);
    }
}