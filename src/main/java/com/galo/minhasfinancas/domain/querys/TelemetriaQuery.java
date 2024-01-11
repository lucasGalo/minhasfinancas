package com.galo.minhasfinancas.domain.querys;

public class TelemetriaQuery extends QUERY {

  private String action;
  private String session;
  private String ip;
  private double latitude;
  private double longitude;
  private String headers;

  public TelemetriaQuery() {
  }

  public String getAction() {return action;}
  public void setAction(String action) {this.action = action;}
  public String getSession() {return session;}
  public void setSession(String session) {this.session = session;}
  public String getIp() {return ip;}
  public void setIp(String ip) {this.ip = ip;}
  public double getLatitude() {return latitude;}
  public void setLatitude(double latitude) {this.latitude = latitude;}
  public double getLongitude() {return longitude;}
  public void setLongitude(double longitude) {this.longitude = longitude;}
  public String getHeaders() {return headers;}
  public void setHeaders(String headers) {this.headers = headers;}
}
