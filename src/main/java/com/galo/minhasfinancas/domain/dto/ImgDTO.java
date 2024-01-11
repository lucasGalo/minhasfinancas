package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Img;

public class ImgDTO extends DTO {
  private String url;
  public ImgDTO() {}

  public ImgDTO(Img obj) {
    if(null != obj) {
      setId(obj.getId());
      setNome(obj.getNome());
      setDescricao(obj.getDescricao());
      setAtivo(obj.getAtivo());
      setGuid(obj.getGuid());
      setUpdateAt(obj.getUpdateAt());
      setCreateAt(obj.getCreateAt());
      url = obj.getUrl();
    }
  }
  public String getUrl() {return url;}
  public void setUrl(String url) {this.url = url;}
  @Override
  public String toString() {
    return "ImgDTO{" + "url='" + url + '\'' +
            '}';
  }
}
