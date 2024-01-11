package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Clientinfo;

public class ClientinfoDTO extends DTO {
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
  public ClientinfoDTO() {}
  public ClientinfoDTO(Clientinfo obj) {
    if(null != obj) {
      setId(obj.getId());
      setNome(obj.getNome());
      setDescricao(obj.getDescricao());
      setAtivo(obj.getAtivo());
      setGuid(obj.getGuid());
      setUpdateAt(obj.getUpdateAt());
      setCreateAt(obj.getCreateAt());
      this.ip = obj.getIp();
      this.hostname = obj.getHostname();
      this.city = obj.getCity();
      this.region = obj.getRegion();
      this.country = obj.getCountry();
      this.loc = obj.getLoc();
      this.org = obj.getOrg();
      this.postal = obj.getPostal();
      this.timezone = obj.getTimezone();
      this.readme = obj.getReadme();
    }
  }

  public String getIp() {return ip;}
  public void setIp(String ip) {this.ip = ip;}
  public String getHostname() {return hostname;}
  public void setHostname(String hostname) {this.hostname = hostname;}
  public String getCity() {return city;}
  public void setCity(String city) {this.city = city;}
  public String getRegion() {return region;}
  public void setRegion(String region) {this.region = region;}
  public String getCountry() {return country;}
  public void setCountry(String country) {this.country = country;}
  public String getLoc() {return loc;}
  public void setLoc(String loc) {this.loc = loc;}
  public String getOrg() {return org;}
  public void setOrg(String org) {this.org = org;}
  public String getPostal() {return postal;}
  public void setPostal(String postal) {this.postal = postal;}
  public String getTimezone() {return timezone;}
  public void setTimezone(String timezone) {this.timezone = timezone;}
  public String getReadme() {return readme;}
  public void setReadme(String readme) {this.readme = readme;}
  @Override
  public String toString() {
    return "ClientinfoDTO{" + "ip='" + ip + '\'' +
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
