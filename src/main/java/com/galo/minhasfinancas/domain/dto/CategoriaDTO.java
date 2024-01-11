package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.enums.Icone;

import java.util.List;
import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;

public class CategoriaDTO extends DTO {

  private Icone icone;
  private String cor;
  private List<CompraDTO> compras;

  public CategoriaDTO() {}
  public CategoriaDTO(Categoria obj, String classe) {
    if( null != obj) {
      setDefault(obj);
      this.cor = obj.getCor();
      this.icone = obj.getIcone();
      if (!Objects.equals(classe, Compra.class.getSimpleName()))
        this.compras = custom(obj.getCompras(), CompraDTO.class, Categoria.class);
    }
  }

  public Icone getIcone() {return icone;}
  public void setIcone(Icone icone) {this.icone = icone;}
  public String getCor() {return cor;}
  public void setCor(String cor) {this.cor = cor;}
  public List<CompraDTO> getCompras() {return compras;}
  public void setCompras(List<CompraDTO> compras) {this.compras = compras;}
}
