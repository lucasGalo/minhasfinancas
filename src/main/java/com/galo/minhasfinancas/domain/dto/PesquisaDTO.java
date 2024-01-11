package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Pesquisa;

public class PesquisaDTO extends DTO {
  private String ip;
  public PesquisaDTO() {}
  public PesquisaDTO(Pesquisa obj) {
    if(null != obj) {
      setId(obj.getId());
      setNome(obj.getNome());
      setDescricao(obj.getDescricao());
      setAtivo(obj.getAtivo());
      setGuid(obj.getGuid());
      setUpdateAt(obj.getUpdateAt());
      setCreateAt(obj.getCreateAt());
      this.ip = obj.getIp();
    }
  }

  public String getIp() {return ip;}
  public void setIp(String ip) {this.ip = ip;}

  @Override
  public String toString() {
    return "PesquisaDTO{" + "ip=" + ip +
            '}';
  }
}
