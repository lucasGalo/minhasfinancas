package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.StatusCompra;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.MoedaUtil.parseDouble;

public class CompraDTO extends DTO {
  private StatusCompra statusCompra;
  private String vlr;
  private UsuarioDTO usuario;
  private List<ItemDTO> items = new ArrayList<>();
  private List<PagamentoDTO> pagamentos = new ArrayList<>();
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
  private Date date;
  private CategoriaDTO categoria;
  public CompraDTO() {}
  public CompraDTO(Compra obj, String classe) {
    if (null != obj) {
      setDefault(obj);
      this.vlr = Double.toString(obj.getVlr());
      this.statusCompra = obj.getStatusCompra();
      this.date = obj.getDate();
      if (!Objects.equals(classe, Usuario.class.getSimpleName()) && null != obj.getUsuario())
        this.usuario = custom(obj.getUsuario(), UsuarioDTO.class, Compra.class);
      if (!Objects.equals(classe, Item.class.getSimpleName()))
        this.items = custom(obj.getItems(), ItemDTO.class,  Compra.class);
      if (!Objects.equals(classe, Pagamento.class.getSimpleName()))
        this.pagamentos = custom(obj.getPagamentos(), PagamentoDTO.class,  Compra.class);
      if (!Objects.equals(classe, Categoria.class.getSimpleName()))
        this.categoria = custom(obj.getCategoria(), CategoriaDTO.class,  Compra.class);
    }
  }

  public StatusCompra getStatusCompra() {return statusCompra;}
  public void setStatusCompra(StatusCompra statusCompra) {this.statusCompra = statusCompra;}
  public double getVlr() {return parseDouble(vlr);}
  public void setVlr(String vlr) {this.vlr = vlr;}
  public UsuarioDTO getUsuario() {return usuario;}
  public void setUsuario(UsuarioDTO usuario) {this.usuario = usuario;}
  public List<ItemDTO> getItems() {return items;}
  public void setItems(List<ItemDTO> items) {this.items = items;}
  public List<PagamentoDTO> getPagamentos() {return pagamentos;}
  public void setPagamentos(List<PagamentoDTO> pagamentos) {this.pagamentos = pagamentos;}
  public Date getDate() {return date;}
  public void setDate(Date date) {this.date = date;}
  public CategoriaDTO getCategoria() {return categoria;}
  public void setCategoria(CategoriaDTO categoria) {this.categoria = categoria;}
}
