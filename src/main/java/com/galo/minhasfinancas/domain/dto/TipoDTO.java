package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Renda;
import com.galo.minhasfinancas.domain.entity.Tipo;
import com.galo.minhasfinancas.domain.enums.Icone;

import java.util.List;
import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

public class TipoDTO extends DTO {

  private Icone icone;
  private String cor;
  private List<RendaDTO> rendas;

  public TipoDTO() {}
  public TipoDTO(Tipo obj, String classe) {
    if( null != obj) {
      setDefault(obj);
      this.cor = obj.getCor();
      this.icone = obj.getIcone();
      if (!Objects.equals(classe, Renda.class.getSimpleName()))
        this.rendas = custom(obj.getRendas(), RendaDTO.class, Categoria.class);
    }
  }

  public Icone getIcone() {return icone;}
  public void setIcone(Icone icone) {this.icone = icone;}
  public String getCor() {return cor;}
  public void setCor(String cor) {this.cor = cor;}
  public List<RendaDTO> getRendas() {return rendas;}
  public void setRendas(List<RendaDTO> rendas) {this.rendas = rendas;}
}
