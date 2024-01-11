package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telemetria")
public class Telemetria extends EntityBase {
  private String session;
  private String ip;
  private double latitude;
  private double longitude;
  @Column(name = "headers", length = 1000)
  private String headers;
  @OneToOne(mappedBy = "telemetria")
  private Clientinfo clientinfo;
  public Telemetria() {}
  public Telemetria(Integer id, Boolean isAtivo, String nome, String session, String ip, double latitude, double longitude, String headers, int usuarioId, Clientinfo clientinfo) {
    super(id, isAtivo, nome, null);
    setGuid(usuarioId);
    this.session = session;
    this.ip = ip;
    this.latitude = latitude;
    this.longitude = longitude;
    this.headers = headers;
    this.clientinfo = clientinfo;
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
  public Clientinfo getClientinfo() {return clientinfo;}
  public void setClientinfo(Clientinfo clientinfo) {this.clientinfo = clientinfo;}
  @Override
  public String toString() {
    return "Telemetria{" + "session='" + session + '\'' +
            ", ip='" + ip + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", headers='" + headers + '\'' +
            ", Guid=" + getGuid() +
            ", Clientinfo=" + clientinfo +
            '}';
  }
}