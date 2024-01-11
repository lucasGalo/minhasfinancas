package com.galo.minhasfinancas.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galo.minhasfinancas.domain.entity.Carteira;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.MoedaUtil.parseDouble;

public class PagamentoDTO extends DTO {
  private String vlr;
  @JsonFormat(pattern = DD_MM_YYYY)
  @DateTimeFormat(pattern = YYYY_MM_DD, iso = DateTimeFormat.ISO.TIME)
  private Date dtPagamento;
  private CompraDTO compra;
  private CarteiraDTO carteira;

  public PagamentoDTO() {}
  public PagamentoDTO(Pagamento obj, String classe) {
    if(null != obj) {
      setDefault(obj);
      this.vlr = Double.toString(obj.getVlr());
      this.dtPagamento = obj.getDtPagamento();
      if(!Objects.equals(classe, Compra.class.getSimpleName()))
        this.compra = (CompraDTO) custom(obj.getCompra(), CompraDTO.class, Pagamento.class);
      if(!Objects.equals(classe, Carteira.class.getSimpleName()))
        this.carteira = (CarteiraDTO) custom(obj.getCarteira(), CarteiraDTO.class, Pagamento.class);
    }
  }

  public double getVlr() {return parseDouble(vlr);}
  public void setVlr(String vlr) {
    this.vlr = vlr;
  }
  public Date getDtPagamento() {
    return dtPagamento;
  }
  public void setDtPagamento(Date dtPagamento) {
    this.dtPagamento = dtPagamento;
  }
  public CompraDTO getCompra() {return compra;}
  public void setCompra(CompraDTO compra) {this.compra = compra;}
  public CarteiraDTO getCarteira() {return carteira;}
  public void setCarteira(CarteiraDTO carteira) {this.carteira = carteira;}
}
