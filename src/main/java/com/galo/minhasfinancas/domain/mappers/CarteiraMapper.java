package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.CarteiraDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.entity.Carteira;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

@Mapper(componentModel = "spring")
public interface CarteiraMapper extends MAPPER<CarteiraDTO, Carteira> {

    @Mapping(source = "pagamentos", target = "pagamentos", qualifiedByName = "pagamentosDTO")
    CarteiraDTO toDto(Carteira obj);
    Carteira toObj(CarteiraDTO dto);

    @Named("pagamentosDTO")
    static List<PagamentoDTO> pagamentosDTO(List<Pagamento> pagamentos) {
        return custom(pagamentos, PagamentoDTO.class, Carteira.class);
    }
}

