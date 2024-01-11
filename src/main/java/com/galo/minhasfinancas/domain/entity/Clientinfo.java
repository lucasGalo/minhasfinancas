package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientinfo")
public class Clientinfo extends EntityBase {

  private String ip;
  private String hostname;
  private String city;
  private String region;
  private String country;
  private String loc;
  private String org;
  private String postal;
  private String timezone;
  private String readme;
  @OneToOne
  private Telemetria telemetria;

  public Clientinfo() {}

  public Clientinfo(Integer id, Boolean isAtivo, String nome, String ip, String hostname, String city, String region, String country, String loc, String org, String postal, String timezone, String readme) {
    super(id, isAtivo, nome, null);
    this.ip = ip;
    this.hostname = hostname;
    this.city = city;
    this.region = region;
    this.country = country;
    this.loc = loc;
    this.org = org;
    this.postal = postal;
    this.timezone = timezone;
    this.readme = readme;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getHostname() {
    return hostname;
  }
  public void setHostname(String hostname) {this.hostname = hostname;}
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public String getLoc() {
    return loc;
  }
  public void setLoc(String loc) {
    this.loc = loc;
  }
  public String getOrg() {
    return org;
  }
  public void setOrg(String org) {
    this.org = org;
  }
  public String getPostal() {
    return postal;
  }
  public void setPostal(String postal) {
    this.postal = postal;
  }
  public String getTimezone() {
    return timezone;
  }
  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }
  public String getReadme() {
    return readme;
  }
  public void setReadme(String readme) {
    this.readme = readme;
  }
  public Telemetria getTelemetria() {return telemetria;}
  public void setTelemetria(Telemetria telemetria) {this.telemetria = telemetria;}

  @Override
  public String toString() {
    return "Clientinfo{" + "ip='" + ip + '\'' +
            ", hostname='" + hostname + '\'' +
            ", city='" + city + '\'' +
            ", region='" + region + '\'' +
            ", country='" + country + '\'' +
            ", loc='" + loc + '\'' +
            ", org='" + org + '\'' +
            ", postal='" + postal + '\'' +
            ", timezone='" + timezone + '\'' +
            ", readme='" + readme + '\'' +
            '}';
  }
}
