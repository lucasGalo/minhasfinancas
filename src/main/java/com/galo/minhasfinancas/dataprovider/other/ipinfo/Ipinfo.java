package com.galo.minhasfinancas.dataprovider.other.ipinfo;

import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.domain.entity.Clientinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class Ipinfo {

  @Autowired private RestTemplate restTemplate;

  public Clientinfo getClientInfo(String ip, LogProcessInterface log) {
    log.info(String.format("[getClientInfo] ip: %s ", ip));

    try {
      ResponseEntity<Clientinfo> response = restTemplate.getForEntity("https://ipinfo.io/"+ip+"/json",  Clientinfo.class);

      log.info(String.format("[getClientInfo] [RESPONSE] code: %s clientInfo: %s", response.getStatusCode(), response.getBody()));

      return response.getBody();

    } catch (Exception ex) {
      log.error(String.format("[getClientInfo] [Exception] erro clientInfo : %s", ex.getMessage()));
      return null;
    }
  }
}