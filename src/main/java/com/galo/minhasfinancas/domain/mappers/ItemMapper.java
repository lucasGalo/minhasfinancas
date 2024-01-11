package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Mapper(componentModel = "spring")
public interface ItemMapper extends MAPPER<ItemDTO, Item> {

    @Mapping(source = "compra", target = "compra", qualifiedByName = "compraDTO")
    ItemDTO toDto(Item obj);

    Item toObj(ItemDTO dto);

    @Named("compraDTO")
    static CompraDTO compraDTO(Compra compra) {
        return custom(compra, CompraDTO.class, Item.class);
    }
}