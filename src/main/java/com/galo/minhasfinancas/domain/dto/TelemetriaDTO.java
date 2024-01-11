package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Telemetria;

public class TelemetriaDTO extends DTO {
  private String session;
  private String ip;
  private double latitude;
  private double longitude;
  private String headers;
  private ClientinfoDTO clientinfo;

  public TelemetriaDTO() {
  }

  public TelemetriaDTO(Telemetria obj) {
    if (null != obj) {
      setId(obj.getId());
      setNome(obj.getNome());
      setDescricao(obj.getDescricao());
      setAtivo(obj.getAtivo());
      setGuid(obj.getGuid());
      setUpdateAt(obj.getUpdateAt());
      setCreateAt(obj.getCreateAt());
      this.session = obj.getSession();
      this.ip = obj.getIp();
      this.latitude = obj.getLatitude();
      this.longitude = obj.getLongitude();
      this.headers = obj.getHeaders();
      this.clientinfo = new ClientinfoDTO(obj.getClientinfo());
    }
  }

  public String getSession() {
    return session;
  }
  public void setSession(String session) {
    this.session = session;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public double getLatitude() {
    return latitude;
  }
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }
  public double getLongitude() {
    return longitude;
  }
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
  public String getHeaders() {
    return headers;
  }
  public void setHeaders(String headers) {
    this.headers = headers;
  }
  public ClientinfoDTO getClientinfo() {return clientinfo;}
  public void setClientinfo(ClientinfoDTO clientinfo) {this.clientinfo = clientinfo;}
  @Override
  public String toString() {
    return "TelemetriaDTO{" + "session='" + session + '\'' +
            ", ip='" + ip + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", headers='" + headers + '\'' +
            ", guid=" + getGuid() +
            ", clientInfo=" + clientinfo +
            '}';
  }
}
