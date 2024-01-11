package com.galo.minhasfinancas.framework.api.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.galo.minhasfinancas.core.services.telemetria.ITelemetriaServices;
import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.domain.enums.BaseEnum;
import com.galo.minhasfinancas.domain.querys.TelemetriaQuery;

public enum TelemetriaAction implements BaseEnum, ActionEnum<TelemetriaQuery, ITelemetriaServices> {

  @JsonProperty("location_telemetria")
  LOCATION_TELEMETRIA(1, "location_telemetria") { @Override public TelemetriaDTO execute(TelemetriaQuery query, ITelemetriaServices services) {return services.save(query);}}
  ;

  private final int cod;
  private final String value;

  TelemetriaAction(int cod, String value) {
    this.cod = cod;
    this.value = value;
  }
  public int getCod() {return cod;}
  public String getValue() {return value;}
}
