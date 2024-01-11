package com.galo.minhasfinancas.core.services.telemetria;

import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.domain.querys.TelemetriaQuery;

import java.util.List;

public abstract class ITelemetriaServices {
  public abstract TelemetriaDTO save(TelemetriaQuery query);
  public abstract List<TelemetriaDTO> findAllAno(String ano);
  public abstract void updateTelemetriaGuid(String session, Integer guid);

}
