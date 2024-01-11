package com.galo.minhasfinancas.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galo.minhasfinancas.domain.entity.Contato;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ContatoDTO extends DTO {
  private String email;
  private String msg;
  private String resposta;
  @JsonFormat(pattern = DD_MM_YYYY)
  @DateTimeFormat(pattern = YYYY_MM_DD, iso = DateTimeFormat.ISO.TIME)
  private Date dataResposta;
  public ContatoDTO() {}

  public ContatoDTO(Contato obj) {
    if(null != obj) {
      setId(obj.getId());
      setNome(obj.getNome());
      setDescricao(obj.getDescricao());
      setAtivo(obj.getAtivo());
      setGuid(obj.getGuid());
      setUpdateAt(obj.getUpdateAt());
      setCreateAt(obj.getCreateAt());
      this.email = obj.getEmail();
      this.msg = obj.getMsg();
      this.resposta = obj.getResposta();
      this.dataResposta = obj.getDataResposta();
    }
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
    return "ContatoDTO{" + "email='" + email + '\'' +
            ", msg='" + msg + '\'' +
            ", resposta='" + resposta + '\'' +
            ", dataResposta=" + dataResposta +
            '}';
  }
}
