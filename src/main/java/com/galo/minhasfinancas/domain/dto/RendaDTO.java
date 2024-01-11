package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Renda;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.StatusRenda;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.MoedaUtil.parseDouble;

public class RendaDTO extends DTO {
  private StatusRenda statusRenda;
  private String vlr;
  private UsuarioDTO usuario;
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
  private Date date;
  private TipoDTO tipo;
  public RendaDTO() {}
  public RendaDTO(Renda obj, String classe) {
    if (null != obj) {
      setDefault(obj);
      this.vlr = Double.toString(obj.getVlr());
      this.statusRenda = obj.getStatusRenda();
      this.date = obj.getDate();
      if (!Objects.equals(classe, Usuario.class.getSimpleName()) && null != obj.getUsuario())
        this.usuario = custom(obj.getUsuario(), UsuarioDTO.class, Renda.class);
      if (!Objects.equals(classe, Categoria.class.getSimpleName()))
        this.tipo = custom(obj.getTipo(), TipoDTO.class,  Renda.class);
    }
  }

  public StatusRenda getStatusRenda() {return statusRenda;}
  public void setStatusRenda(StatusRenda statusRenda) {this.statusRenda = statusRenda;}
  public double getVlr() {return parseDouble(vlr);}
  public void setVlr(String vlr) {this.vlr = vlr;}
  public UsuarioDTO getUsuario() {return usuario;}
  public void setUsuario(UsuarioDTO usuario) {this.usuario = usuario;}
  public Date getDate() {return date;}
  public void setDate(Date date) {this.date = date;}
  public TipoDTO getTipo() {return tipo;}
  public void setTipo(TipoDTO tipo) {this.tipo = tipo;}
}
