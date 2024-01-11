package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.CarteiraDTO;
import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.entity.Carteira;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import com.galo.minhasfinancas.domain.entity.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Mapper(componentModel = "spring")
public interface PagamentoMapper extends MAPPER<PagamentoDTO, Pagamento> {

  @Mapping(source = "compra", target = "compra", qualifiedByName = "compraDTO")
  @Mapping(source = "carteira", target = "carteira", qualifiedByName = "carteiraDTO")
  PagamentoDTO toDto(Pagamento obj);

  Pagamento toObj(PagamentoDTO dto);

  @Named("compraDTO")
  static CompraDTO compraDTO(Compra compra) {
    return custom(compra, CompraDTO.class, Pagamento.class);
  }

  @Named("carteiraDTO")
  static CarteiraDTO carteiraDTO(Carteira carteira) {
    return custom(carteira, CarteiraDTO.class, Pagamento.class);
  }
}