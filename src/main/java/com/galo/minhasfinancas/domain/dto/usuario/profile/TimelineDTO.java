package com.galo.minhasfinancas.domain.dto.usuario.profile;

import com.galo.minhasfinancas.domain.dto.DTO;

import java.util.Date;

public class TimelineDTO extends DTO {

  private String icon;
  private String breve;
  private String obs;

  public TimelineDTO() {
  }

  public TimelineDTO(Integer id, String nome, String breve, String icon, Date createAt, String obs) {
    setId(id);
    setNome(nome);
    setUpdateAt(createAt);
    setCreateAt(createAt);
    this.icon = icon;
    this.breve = breve;
    this.obs = obs;
  }

  public String getIcon() {return icon;}
  public void setIcon(String icon) {this.icon = icon;}
  public String getBreve() {return breve;}
  public void setBreve(String breve) {this.breve = breve;}
  public String getObs() {return obs;}
  public void setObs(String obs) {this.obs = obs;}
}
