package com.galo.minhasfinancas.core.services.telemetria;

import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.core.execution.TelemetriaImpl;
import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.domain.entity.Clientinfo;
import com.galo.minhasfinancas.domain.entity.Telemetria;
import com.galo.minhasfinancas.domain.querys.TelemetriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.galo.minhasfinancas.sources.StringUtil.getUUID;

@Service
public class TelemetriaServices extends ITelemetriaServices {
  private final TelemetriaImpl impl;

  public TelemetriaServices(TelemetriaImpl impl) {
    this.impl = impl;
  }

  @Override
  public TelemetriaDTO save(TelemetriaQuery query) {

    String session = impl.request.getSession();
    if (session.isEmpty()) return new TelemetriaDTO();

    Telemetria telemetria = impl.repo.findTelemetria(session);
    String ip = impl.request.getIp();
    if (telemetria == null) {

      Clientinfo clientInfo = new Clientinfo();
      clientInfo.setAtivo(true);
      clientInfo.setNome("");

      if (query.getLatitude() == 0.00 && verificarIp(ip)) {
          clientInfo = getClientInfo(ip);
          if (clientInfo.getLoc() != null) {
            String[] split = clientInfo.getLoc().split(",");
            query.setLatitude(Double.parseDouble(split[0]));
            query.setLongitude(Double.parseDouble(split[1]));
          }
      }

      impl.clientinfoRepository.save(clientInfo);
      telemetria = new Telemetria(null, true, "", session, ip, query.getLatitude(), query.getLongitude(), impl.request.headers(), 0, clientInfo);
      telemetria = impl.repo.save(telemetria);
      clientInfo.setTelemetria(telemetria);
      impl.clientinfoRepository.save(clientInfo);

    }else{
      if(telemetria.getLatitude() == 0.00 && verificarIp(ip)){
        Clientinfo clientInfo = getClientInfo(ip);
        if (clientInfo.getLoc() != null) {
          Clientinfo byId = impl.clientinfoRepository.findClientinfo(telemetria.getId());

          byId.setCity(clientInfo.getCity());
          byId.setCountry(clientInfo.getCountry());
          byId.setHostname(clientInfo.getHostname());
          byId.setOrg(clientInfo.getOrg());
          byId.setPostal(clientInfo.getPostal());
          byId.setReadme(clientInfo.getReadme());
          byId.setRegion(clientInfo.getRegion());
          byId.setTimezone(clientInfo.getTimezone());
          impl.clientinfoRepository.save(byId);

          String[] split = clientInfo.getLoc().split(",");
          telemetria.setLatitude(Double.parseDouble(split[0]));
          telemetria.setLongitude(Double.parseDouble(split[1]));
          impl.repo.save(telemetria);
        }
      }
    }
    return impl.toDTO(telemetria);
  }

  boolean verificarIp(String ip){
    return ip != null && !ip.equals("0:0:0:0:0:0:0:1") && !ip.startsWith("192.168");
  }

  private Clientinfo getClientInfo(String ip){
    try (LogProcessInterface log = impl.logs.get("[KEY] " + getUUID())) {
      Clientinfo clientInfo = impl.ipinfo.getClientInfo(ip, log);
      clientInfo.setAtivo(true);
      clientInfo.setNome("");
      return clientInfo;
    }
  }
  public List<TelemetriaDTO> findAllAno(String ano) {
    return impl.repo.findAllAno(ano).stream().map(TelemetriaDTO::new).collect(Collectors.toList());
  }

  @Override
  public void updateTelemetriaGuid(String session, Integer guid) {
    try (LogProcessInterface log = impl.logs.get("[KEY] " + getUUID())) {
      try {
        Telemetria telemetria = impl.getTelemetria(session);
        if (telemetria != null) {
          telemetria.setGuid(guid);
          impl.updateNotGuid(telemetria);
        }
      } catch (Exception ex) {
        log.error(impl.request.format("[getToken] [Exception] erro ao salvar telemetria no login erro: %s: ", ex.getMessage()));
      }
    }
  }
}