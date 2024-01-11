package com.galo.minhasfinancas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "contato")
public class Contato extends EntityBase {
  private String email;
  private String msg;
  private String resposta;
  @JsonFormat(pattern = DD_MM_YYYY)
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  @Column(name = "data_resposta")
  private Date dataResposta;
  public Contato() {}

  public Contato(Integer id, Boolean isAtivo, String nome, String email, String msg, String resposta, Date dataResposta) {
    super(id, isAtivo, nome, null);
    this.email = email;
    this.msg = msg;
    this.resposta = resposta;
    this.dataResposta = dataResposta;
  }

  public String getEmail() {return email;}
  public void setEmail(String email) {this.email = email;}
  public String getMsg() {return msg;}
  public void setMsg(String msg) {this.msg = msg;}
  public String getResposta() {return resposta;}
  public void setResposta(String resposta) {this.resposta = resposta;}
  public Date getDataResposta() {return dataResposta;}
  public void setDataResposta(Date dataResposta) {this.dataResposta = dataResposta;}
  @Override
  public String toString() {
    return "Contato{" + "email='" + email + '\'' +
            ", msg='" + msg + '\'' +
            ", resposta='" + resposta + '\'' +
            ", dataResposta=" + dataResposta +
            '}';
  }
}
