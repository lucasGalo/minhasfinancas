package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Carteira;
import com.galo.minhasfinancas.domain.entity.Pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

public class CarteiraDTO extends DTO {

  private List<PagamentoDTO> pagamentos =  new ArrayList<>();

  public CarteiraDTO() {}
  public CarteiraDTO(Carteira obj, String classe) {
    if(null != obj) {
      setDefault(obj);

      if (!Objects.equals(classe, Pagamento.class.getSimpleName()))
        this.pagamentos = custom(obj.getPagamentos(), PagamentoDTO.class, Carteira.class);
    }
  }

  public List<PagamentoDTO> getPagamentos() {return pagamentos;}
  public void setPagamentos(List<PagamentoDTO> pagamentos) {this.pagamentos = pagamentos;}

}
